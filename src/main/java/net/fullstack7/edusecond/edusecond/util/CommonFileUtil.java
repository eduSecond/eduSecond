package net.fullstack7.edusecond.edusecond.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommonFileUtil {

    // webapp 폴더까지의 절대 경로를 얻기 위한 상수
    private static final String WEBAPP_PATH = "src/main/webapp/";
    // 이미지가 저장될 상대 경로
    private static final String UPLOAD_PATH = "resources/images/product/";
    
    // 다중 파일 업로드 메서드
    public static List<String> uploadFiles(List<MultipartFile> files) throws IOException {
        List<String> uploadedFilePaths = new ArrayList<>();
        String projectPath = System.getProperty("user.dir");
        File uploadDir = new File(projectPath, WEBAPP_PATH + UPLOAD_PATH);
        
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
                // DB에는 웹에서 접근 가능한 경로로 저장
                uploadedFilePaths.add("/" + UPLOAD_PATH + uniqueFileName);
            }
        }
        return uploadedFilePaths;
    }

    // 파일 삭제 메서드
    public static boolean deleteFile(String filePath) {
        if (filePath == null || filePath.isEmpty()) return false;
        
        // 웹 경로를 실제 파일 시스템 경로로 변환
        String projectPath = System.getProperty("user.dir");
        // filePath에서 앞의 "/" 제거
        String cleanPath = filePath.startsWith("/") ? filePath.substring(1) : filePath;
        String realPath = projectPath + File.separator + WEBAPP_PATH + cleanPath;
        
        File file = new File(realPath);
        return file.exists() && file.delete();
    }

    // 파일명 변경 메서드
    public static String renameFile(String oldFileName, String newFileName) throws IOException {
        String projectPath = System.getProperty("user.dir");
        
        // 웹 경로를 실제 경로로 변환
        String oldPath = oldFileName.startsWith("/") ? oldFileName.substring(1) : oldFileName;
        String oldFullPath = projectPath + File.separator + WEBAPP_PATH + oldPath;
        
        File oldFile = new File(oldFullPath);
        
        // 새 파일명에 경로 추가
        String uniqueNewFileName = generateUniqueFileName(newFileName);
        String newPath = UPLOAD_PATH + uniqueNewFileName;
        String newFullPath = projectPath + File.separator + WEBAPP_PATH + newPath;
        File newFile = new File(newFullPath);

        // 새 파일 경로의 디렉토리가 없다면 생성
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }

        if (oldFile.exists() && oldFile.renameTo(newFile)) {
            return "/" + newPath; // 웹에서 접근 가능한 경로 반환
        } else {
            throw new IOException("파일명을 변경할 수 없습니다.");
        }
    }

    // 고유 파일명 생성 메서드
    private static String generateUniqueFileName(String originalFileName) {
        String extension = "";
        int index = originalFileName.lastIndexOf(".");
        if (index > 0) {
            extension = originalFileName.substring(index);
        }
        return UUID.randomUUID().toString() + extension;
    }
}
