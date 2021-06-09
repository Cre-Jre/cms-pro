package com.cms.contex.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UtilsServletContext implements ServletContextAware {

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * 获取servlet容器中的真实路径
     *
     * @param path 传入的路径
     * @return 容器路径
     */
    public String getRealPath(String path) {
        return servletContext.getRealPath(StringUtils.isBlank(path) ? "/" : path);
    }

    /**
     * 获取模板相对位置  返回相对于/webapp/WEB-INF/后的路径
     * 1.   /front/default/index/index.html
     * 2.  /front/default/index/index_1.html
     * @param tplDirName                模板目录名称
     * @param tplPrefix                 模板前缀名称
     * @return                          list
     */
    public List<String> getTplRelativePath(String tplDirName,String tplPrefix){
        String dirPath="/WEB-INF/front/default/"+tplDirName;
        File file = new File(getRealPath(dirPath));
        File webInfoFile = new File(getRealPath("/WEB-INF"));
        Collection<File> fileList = FileUtils.listFiles(file, FileFilterUtils.prefixFileFilter(tplPrefix), null);
        return fileList.stream().map(x->StringUtils.substring(StringUtils.replace(x.getAbsolutePath(),File.separator,"/"),webInfoFile.getAbsolutePath().length())
        ).collect(Collectors.toList());
    }

    /**
     * 获取项目虚拟路径
     * @return          虚拟路径
     */
    public String getContextPath(){
        return servletContext.getContextPath();
    }

    /**
     * 文件目录是否存在
     * @param dir          文件目录
     * @return              布尔值
     */
    public static boolean dirExist(String dir){
        File file = new File(dir);
        if(file.isDirectory()){
            return true;
        }
        return false;
    }
}
