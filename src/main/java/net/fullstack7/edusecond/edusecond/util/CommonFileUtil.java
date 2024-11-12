package net.fullstack7.edusecond.edusecond.util;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@Log4j2
public class CommonFileUtil {
    public CommonFileUtil() {}

    // 파일 업로드
    public static String fileUpload(HttpServletRequest req, String saveDir, String inFileTagName)
            throws ServletException, IOException {
        log.info("CommonFileUtil >> UploadFile Start");

        //Part 객체를 사용
        Part part = req.getPart(inFileTagName);

        //헤더값에서 파일 객체 부분 읽어 오기
        String partHeader = part.getHeader("content-disposition");
        //파일객체부분 : form-data; name="attachFile"; filename="파일명"
        log.info("partHeader : "+ partHeader);

        String[] arrPartHeader = partHeader.split("filename=");
        String orgFileName = arrPartHeader[1].trim().replace("\"", "");

        if (!orgFileName.isEmpty()) {
            log.info("orgFileName : "+ orgFileName);

            // 파일을 업로드하는 경로
            //D:\java7\JSP\chap13\src\main\webapp\Uploads
            part.write(saveDir + File.separator + orgFileName);
        }

        log.info("CommonFileUtil >> UploadFile End");

        return orgFileName;
    }

    // 업로드된 파일명 변경
    public static Map<String, String> fileRename(String saveDir, String fileName) {
        if ( saveDir == null || saveDir.isEmpty()
                || fileName == null || fileName.isEmpty() ) {
            return null;
        }

        log.info("CommonFileUtil >> fileRename Start");
        // 파일 확장자 추출
        String fileExt = fileName.substring(fileName.lastIndexOf("."));

        // 임시 파일명 생성
        String tmpName = new SimpleDateFormat("yyyyMMdd_HHmmssS").format(new Date());

        // 새로운 파일명 조합
        String newFileName = tmpName + fileExt;

        //기존 파일명 --> 새로운 파일명으로 변경
        File oldFile = new File(saveDir + File.separator + fileName);
        File newFile = new File(saveDir + File.separator + newFileName);
        oldFile.renameTo(newFile);

        Map<String, String> fmap = new HashMap<String, String>();
        fmap.put("filePath", saveDir);
        fmap.put("newFileName", newFileName);


        log.info("CommonFileUtil >> fileRename End");

        return fmap;
    }

    // 파일 다운로드
    public static void fileDownload(
            HttpServletRequest req,
            HttpServletResponse res,
            String dir, String orgFileName, String outFileName
    ) {
        // dir : //D:\java7\JSP\chap13\src\main\webapp\Uploads
        // dir : Uploads
        String realPath = req.getServletContext().getRealPath(dir);
        try {
            File file = new File(realPath, orgFileName);
            InputStream inputStream = new FileInputStream(file);

            // 클라이언트의 브라우저 체크
            String userAgent = req.getHeader("User-Agent");
            if (userAgent.indexOf("WOW64") == -1) {
                outFileName = new String(outFileName.getBytes("UTF-8"), "ISO-8859-1");
            } else {
                outFileName = new String(outFileName.getBytes("KSC5601"), "ISO-8859-1");
            }

            // 다운로드할 파일의 응답 헤더 설정
            res.reset();
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition",  "attatch; filename=\""+ outFileName +"\"");
            res.setHeader("Content-Length", ""+file.length());
            //out.clear();

            // response 내장 객체를 이용하여 출력 스트림 생성
            OutputStream outStream = res.getOutputStream();

            // 출력 트림에 파일 내용을 출력시킴
            byte b[] = new byte[(int)file.length()];
            int readBuffer = 0;
            while ( (readBuffer = inputStream.read(b)) > 0 ) {
                outStream.write(b, 0, readBuffer);
            }

            // 입/출력 스트림 닫음
            inputStream.close();
            outStream.close();
        }

        catch (FileNotFoundException e) {
            log.info("파일을 찾을 수 없습니다. : {}", e.getMessage());
        }
        catch (Exception e){
            log.info("예외가 발생하였습니다. : {}", e.getMessage());
        }
    }

    // 파일 삭제
    public static void fileDelete(HttpServletRequest req, String dir, String fileName) {
        log.info("CommonFileUtil >> fileDelete Start");
        try {
            //파일이 존재할 경우 삭제
            File file = new File(dir + File.separator + fileName);
            if ( file.exists() ) {
                file.delete();

                log.info(dir + File.separator + fileName);
            }
        } catch (Exception e) {
            log.info("{}{}{}", dir, File.separator, fileName);
            log.info("파일 삭제시 에러가 발생하였습니다. : {}", e.getMessage());

            e.printStackTrace();
        }
        log.info("CommonFileUtil >> fileDelete End");
        log.info("================================================");
    }

    // 파일 업로드
    public static List<String> multiFileUpload(HttpServletRequest req, String saveDir, String inFileTagName)
            throws ServletException, IOException {
        
        log.info("CommonFileUtil >> multiFileUpload START");

        //파일명 처리 리스트
        List<String> fileNameList = new ArrayList<String>();

        //Part 객체 --> 여러개 처리
        Collection<Part> parts = req.getParts();
        for(Part part : parts) {
            if (!part.getName().equals(inFileTagName)) {
                log.info("parts >> part >> part.getName() : {}", part.getName());
                continue;
            }

            //헤더값에서 파일 객체 부분 읽어 오기
            String partHeader = part.getHeader("content-disposition");
            //파일객체부분 : form-data; name="attachFile"; filename="파일명"
            log.info("parts >> part >> partHeader : "+ partHeader);

            String[] arrPartHeader = partHeader.split("filename=");
            String orgFileName = arrPartHeader[1].trim().replace("\"", "");

            if (!orgFileName.isEmpty()) {
                log.info("orgFileName : "+ orgFileName);

                // 파일을 업로드하는 경로
                //D:\java7\JSP\chap13\src\main\webapp\Uploads
                part.write(saveDir + File.separator + orgFileName);

                fileNameList.add(orgFileName);
            }
        }

        log.info("CommonFileUtil >> multiFileUpload END");

        return fileNameList;
    }
}
