package com.cms.service.impl;

import com.cms.contex.utils.UtilsServletContext;
import com.cms.service.api.CmsContentListenerService;
import com.cms.service.dto.CmsContentDto;
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

    public static void main(String[] args) throws IOException, ParseException {
        FSDirectory fsDirectory = FSDirectory.open(new File("D:\\workSpace\\cms-pro\\cms-portal\\target\\cms-portal\\WEB-INF\\lucene"));
        DirectoryReader directoryReader = DirectoryReader.open(fsDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
        String[] fields = {"title","content"};
        IKAnalyzer ikAnalyzer = new IKAnalyzer();
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_46, fields, ikAnalyzer);
        Query query = multiFieldQueryParser.parse("中秋");
        TopDocs topDocs = indexSearcher.search(query, 10);
        for(ScoreDoc doc:topDocs.scoreDocs){
            int docId = doc.doc;
            Document document = indexSearcher.doc(docId);
            System.out.println(document.get("title"));
            System.out.println(document.get("content"));
        }
        directoryReader.close();
        fsDirectory.close();
    }
}
