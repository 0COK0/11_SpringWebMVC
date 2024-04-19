package com.ohgiraffers.file;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {

    @PostMapping("/single-file")
    public String singleFileUpload(@RequestParam String singleFileDescription,
                                   @RequestParam MultipartFile singleFile,
                                   Model model) {
        System.out.println("singleFileDescription = " + singleFileDescription);
        System.out.println("singleFile = " + singleFile);

        /* 서버로 전달 된 File을 서버에서 설정하는 경로에 저장한다. */
        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);

        // 뜻이 존재하다 인데 !붙여서 아니면
        if (!dir.exists()) {
            dir.mkdirs();
        }

        /* 파일명 변경 처리 (동일한 이름의 파일이름을 갖게 하면 나중에 )
        * */
        String originFileName = singleFile.getOriginalFilename();
        System.out.println("originFileName = " + originFileName);
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        System.out.println("ext = " + ext);

        // 고유한 ID를 랜덤으로 만들어줌
        String saveName = UUID.randomUUID() + ext;
        System.out.println("saveName = " + saveName);

        /* 파일 저장 */
        try {
            System.out.println("나나나나나");
            singleFile.transferTo(new File(filePath + "/" + saveName));
            model.addAttribute("message", "파일 업로드 완료!");
        } catch (IOException e) {
            model.addAttribute("message", "파일 업로드 실패!");
            // 저장하려는 파일 경로가 다르면 적용이 안될수 있음
            e.printStackTrace();
        }
        return "result";
    }

    @PostMapping("/multi-file")
    public String multiFileUpload(@RequestParam String multiFileDescription,
                                  @RequestParam List<MultipartFile> multiFile,
                                  Model model) {
        System.out.println("multiFileDescription = " + multiFileDescription);
        System.out.println("multiFile = " + multiFile);

        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);

        if(!dir.exists()) {
            dir.mkdirs();
        }

        List<FileDTO> files = new ArrayList<>();
        try {
        for(MultipartFile file : multiFile) {
            String originFileName = file.getOriginalFilename();
            System.out.println("originFileName = " + originFileName);

            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            System.out.println("ext = " + ext);

            String saveName = UUID.randomUUID() + ext;
            System.out.println("saveName = " + saveName);

            /* 파일에 관한 정보 추출 후 보관 */
            files.add(new FileDTO(originFileName, saveName, filePath, multiFileDescription));

            /* 파일 저장 */
            file.transferTo(new File(filePath + "/" + saveName));
        }
            /* 서버에 정해진 경로로 파일 저장이 완료 되면 List<FileDTO> 타입의 객체에 저장된 정보를 DB에 insert 한다. */
            model.addAttribute("message", "파일 업로드 성공!");
        } catch (IOException e) {
            /* 파일 저장 중간에 실패 시 이전에 저장된 파일 삭제*/
            for(FileDTO file : files) {
                new File(filePath + "/" + file.getSavedName()).delete();
            }
            model.addAttribute("message", "파일 업로드 실패!");

        }
        return "result";
        }

}


