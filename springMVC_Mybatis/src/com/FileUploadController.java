package com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 *需要 commmons-fileupload.jar  和  commons-io.jar(可能不需要)
 */
@Controller
@RequestMapping("upload")
public class FileUploadController {

    @RequestMapping("/doUploadSingle")
    public String doUploadSingle(@RequestParam("file") CommonsMultipartFile file , HttpServletRequest request) throws IOException {
        file.getOriginalFilename();  //获取文件名
//        String path = request.getRealPath("file");
        InputStream is = file.getInputStream();
        OutputStream os = new FileOutputStream(new File("d:/",file.getOriginalFilename()));
        int len = 0;
        byte[] buffer = new byte[400];
        while((len=is.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        os.close();
        is.close();
        System.out.println("上传文件");
        return "redirect:http://localhost:8080/";
    }

    @RequestMapping("/doUploadMultipart")
    public String doUploadMultipart(@RequestParam("files") CommonsMultipartFile files[] , HttpServletRequest request) throws IOException {
        for (CommonsMultipartFile file : files) {
            file.getOriginalFilename();  //获取文件名
//        String path = request.getRealPath("file");
            InputStream is = file.getInputStream();
            OutputStream os = new FileOutputStream(new File("d:/",file.getOriginalFilename()));
            int len = 0;
            byte[] buffer = new byte[400];
            while((len=is.read(buffer)) != -1){
                os.write(buffer,0,len);
            }
            os.close();
            is.close();
        }

        System.out.println("上传文件");
        return "redirect:http://localhost:8080/";
    }


}
