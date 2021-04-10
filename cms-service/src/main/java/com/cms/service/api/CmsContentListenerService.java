package com.cms.service.api;

import com.cms.core.foundation.Page;
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

    /**
     * 搜索分页查询
     * @param content
     * @param pageCurrent
     * @return
     * @throws Exception
     */
    Page<CmsContentDto> query(String content, int pageCurrent) throws Exception;
}
