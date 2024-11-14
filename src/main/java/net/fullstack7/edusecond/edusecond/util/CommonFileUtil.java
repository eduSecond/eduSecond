package net.fullstack7.edusecond.edusecond.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommonFileUtil {

    private static final String UPLOAD_DIRECTORY = "D:\\project\\spring\\eduSecond\\src\\main\\webapp\\resources\\images\\product\\"; // 저장할 디렉토리 경로

    // 다중 파일 업로드 메서드
    public static List<String> uploadFiles(List<MultipartFile> files) throws IOException {
        List<String> uploadedFilePaths = new ArrayList<>();
        for (MultipartFile file : files) {
            String originalFileName = file.getOriginalFilename();
            if (originalFileName != null && !originalFileName.isEmpty()) {
                String uniqueFileName = generateUniqueFileName(originalFileName);
                String fullPath = UPLOAD_DIRECTORY + uniqueFileName;
                File destinationFile = new File(fullPath);
                file.transferTo(destinationFile);
                uploadedFilePaths.add(fullPath); // 경로를 포함한 파일명을 추가
            }
        }
        return uploadedFilePaths;
    }

    // 파일 삭제 메서드
    public static boolean deleteFile(String fileName) {
        File file = new File(UPLOAD_DIRECTORY + fileName);
        return file.exists() && file.delete();
    }

    // 파일명 변경 메서드
    public static String renameFile(String oldFileName, String newFileName) throws IOException {
        File oldFile = new File(UPLOAD_DIRECTORY + oldFileName);
        String newFilePath = UPLOAD_DIRECTORY + newFileName;
        File newFile = new File(newFilePath);

        // 파일이 이미 존재하는지 확인
        if (newFile.exists()) {
            newFileName = generateUniqueFileName(newFileName);
            newFilePath = UPLOAD_DIRECTORY + newFileName;
            newFile = new File(newFilePath);
        }

        if (oldFile.exists() && oldFile.renameTo(newFile)) {
            return newFilePath; // 전체 경로를 반환
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
