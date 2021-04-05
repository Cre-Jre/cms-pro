package com.cms.service.api;

import com.cms.service.dto.CmsContentDto;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;

public interface CmsContentListenerService {
    /**
     * 添加
     * @param cmsContentDto
     */
    void add(CmsContentDto cmsContentDto);

    /**
     * 构建索引目录
     * @return
     */
    FSDirectory buildFSDirectory();

    /**
     * 构建IndexWriter
     * @param fsDirectory
     * @return
     */
    IndexWriter buildIndexWriter(FSDirectory fsDirectory);

    /**
     * 构建document
     * @param cmsContentDto
     * @return
     */
    Document buildDocument(CmsContentDto cmsContentDto);
}
