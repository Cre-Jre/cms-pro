package com.cms.service.impl;

import com.cms.contex.utils.UtilsServletContext;
import com.cms.core.foundation.Page;
import com.cms.service.api.CmsContentListenerService;
import com.cms.service.dto.CmsContentDto;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CmsLuceneListenerServiceImpl implements CmsContentListenerService {
    /**
     * lucene索引目录
     */
    public static final String PATH_LUCENE = "/WEB-INF/lucene";

    @Autowired
    private UtilsServletContext utilsServletContext;

    @Override
    public synchronized void add(CmsContentDto cmsContentDto) {
        try(FSDirectory fsDirectory = buildFSDirectory();IndexWriter indexWriter = buildIndexWriter(fsDirectory);){
            indexWriter.addDocument(buildDocument(cmsContentDto));
            indexWriter.commit();
        }catch(IOException e){
            log.error("添加内容创建lucene索引失败=[{}]",e.getMessage());
        }
    }

    @Override
    public FSDirectory buildFSDirectory() {
        String luceneDirectory = utilsServletContext.getRealPath(PATH_LUCENE);
        try {
            return FSDirectory.open(new File(luceneDirectory));
        } catch (IOException e) {
            log.error("打开lucene索引目录失败=[{}]",e.getMessage());
        }
        return null;
    }

    @Override
    public IndexWriter buildIndexWriter(FSDirectory fsDirectory) {
        Analyzer ikAnalyzer = new IKAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_46,ikAnalyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        try {
            return new IndexWriter(fsDirectory,indexWriterConfig);
        } catch (IOException e) {
            log.error("创建indexWriter失败=[{}]",e.getMessage());
        }
        return null;
    }

    @Override
    public Document buildDocument(CmsContentDto cmsContentDto) {
        Document document = new Document();
        //id
        FieldType idFieldType = new FieldType();
        idFieldType.setStored(true);
        //标题
        FieldType titleFieldType = new FieldType();
        titleFieldType.setStored(true);
        titleFieldType.setTokenized(true);
        titleFieldType.setIndexed(true);
        //内容
        FieldType contentFieldType = new FieldType();
        contentFieldType.setTokenized(true);
        contentFieldType.setStored(true);
        contentFieldType.setIndexed(true);

        document.add(new Field("id",String.valueOf(cmsContentDto.getId()),idFieldType));
        document.add(new Field("title",cmsContentDto.getTitle(),titleFieldType));
        document.add(new Field("content",cmsContentDto.getContent(),contentFieldType));
        return document;
    }

    @Override
    public Page<CmsContentDto> query(String content, int pageCurrent) throws Exception {
        FSDirectory fsDirectory = FSDirectory.open(new File(utilsServletContext.getRealPath(PATH_LUCENE)));
        DirectoryReader directoryReader = DirectoryReader.open(fsDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
        String[] fields = {"title","content"};
        IKAnalyzer ikAnalyzer = new IKAnalyzer();
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_46, fields, ikAnalyzer);
        Query query = multiFieldQueryParser.parse(content);
        int pageSize = 2;
        ScoreDoc lastDoc = null;
        TopDocs topDocs = null;
        List<CmsContentDto> list = Lists.newArrayList();
        while(pageCurrent>0){
            topDocs = indexSearcher.searchAfter(lastDoc, query, pageSize);
            if(topDocs.scoreDocs.length==0){
                break;
            }
            lastDoc = topDocs.scoreDocs[topDocs.scoreDocs.length-1];
            --pageCurrent;
        }
        for(ScoreDoc doc:topDocs.scoreDocs){
            int docId = doc.doc;
            Document document = indexSearcher.doc(docId);
            CmsContentDto cmsContentDto = new CmsContentDto();
            cmsContentDto.setTitle(document.get("title"));
            cmsContentDto.setContent(document.get("content"));
            list.add(cmsContentDto);
        }
        //总条数   第5页的数据   那么会依次查  第1 第2 第3 第4
        int totalHits = topDocs.totalHits;
        Page<CmsContentDto> page = new Page<>((long) totalHits, (totalHits > 0) ? (totalHits - 1) / pageSize + 1 : 0, list);
        directoryReader.close();
        fsDirectory.close();
        return page;
    }

    public static void main(String[] args) throws IOException, ParseException {
        FSDirectory fsDirectory = FSDirectory.open(new File("D:\\project\\cms-pro\\out\\WEB-INF\\lucene"));
        DirectoryReader directoryReader = DirectoryReader.open(fsDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
        String[] fields = {"title","content"};
        IKAnalyzer ikAnalyzer = new IKAnalyzer();
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_46, fields, ikAnalyzer);
        Query query = multiFieldQueryParser.parse("中秋");
//        TopDocs topDocs = indexSearcher.search(query, 10);
        int pageSize = 2;
        int pageCurrent = 2;
        ScoreDoc lastDoc = null;
        TopDocs topDocs = null;
        while(pageCurrent>0){
            topDocs = indexSearcher.searchAfter(lastDoc, query, pageSize);
            if(topDocs.scoreDocs.length==0){
                break;
            }
            lastDoc = topDocs.scoreDocs[topDocs.scoreDocs.length-1];
            --pageCurrent;
        }
        for(ScoreDoc doc:topDocs.scoreDocs){
            int docId = doc.doc;
            Document document = indexSearcher.doc(docId);
            System.out.println(document.get("title"));
            System.out.println(document.get("content"));
        }
        //总条数   第5页的数据   那么会依次查  第1 第2 第3 第4
        int totalHits = topDocs.totalHits;
        directoryReader.close();
        fsDirectory.close();
    }
}
