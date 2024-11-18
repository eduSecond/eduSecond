package net.fullstack7.edusecond.edusecond.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.BeansException;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommonFileUtil implements ApplicationContextAware {

    private static String UPLOAD_PATH = "resources/images/product/";
    private static ServletContext servletContext;
    private static final Logger log = LogManager.getLogger(CommonFileUtil.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            servletContext = applicationContext.getBean(ServletContext.class);
            log.info("ServletContext 초기화 성공");
        } catch (BeansException e) {
            log.error("ServletContext 초기화 실패: ", e);
            throw new RuntimeException("파일 업로드 유틸 초기화 실패", e);
        }
    }

    public static List<String> uploadFiles(List<MultipartFile> files) throws IOException {
        if (servletContext == null) {
            throw new RuntimeException("ServletContext가 초기화되지 않았습니다.");
        }

        String realPath = servletContext.getRealPath("/");
        List<String> uploadedFilePaths = new ArrayList<>();
        
        File uploadDir = new File(realPath + UPLOAD_PATH);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        for (MultipartFile file : files) {
            String originalFileName = file.getOriginalFilename();
            if (originalFileName != null && !originalFileName.isEmpty()) {
                String uniqueFileName = generateUniqueFileName(originalFileName);
                String fullPath = uploadDir.getPath() + File.separator + uniqueFileName;
                File destinationFile = new File(fullPath);
                file.transferTo(destinationFile);
                uploadedFilePaths.add("/" + UPLOAD_PATH + uniqueFileName);
            }
        }
        return uploadedFilePaths;
    }

    public static boolean deleteFile(String filePath) {
        if (servletContext == null) {
            throw new RuntimeException("ServletContext가 초기화되지 않았습니다.");
        }

        if (filePath == null || filePath.isEmpty()) {
            return false;
        }

        String realPath = servletContext.getRealPath("/");
        String cleanPath = filePath.startsWith("/") ? filePath.substring(1) : filePath;
        String fullPath = realPath + cleanPath;
        
        File file = new File(fullPath);
        return file.exists() && file.delete();
    }

    public static String renameFile(String oldFileName, String newFileName) throws IOException {
        if (servletContext == null) {
            throw new RuntimeException("ServletContext가 초기화되지 않았습니다.");
        }

        String realPath = servletContext.getRealPath("/");
        String oldPath = oldFileName.startsWith("/") ? oldFileName.substring(1) : oldFileName;
        String oldFullPath = realPath + oldPath;
        
        File oldFile = new File(oldFullPath);
        
        String uniqueNewFileName = generateUniqueFileName(newFileName);
        String newPath = UPLOAD_PATH + uniqueNewFileName;
        String newFullPath = realPath + newPath;
        File newFile = new File(newFullPath);

        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }

        if (oldFile.exists() && oldFile.renameTo(newFile)) {
            return "/" + newPath;
        } else {
            throw new IOException("파일명을 변경할 수 없습니다.");
        }
    }

    private static String generateUniqueFileName(String originalFileName) {
        String extension = "";
        int index = originalFileName.lastIndexOf(".");
        if (index > 0) {
            extension = originalFileName.substring(index);
        }
        return UUID.randomUUID().toString() + extension;
    }
}
