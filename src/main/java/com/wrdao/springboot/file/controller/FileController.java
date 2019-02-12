package com.wrdao.springboot.file.controller;

import com.wrdao.springboot.file.service.FileService;
import com.wrdao.springboot.util.ControllerResult;
import com.wrdao.springboot.util.IpUtil;
import net.minidev.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping("/file/test")
    public String file() {
        return "file/file";
    }

    //文件上传相关代码
    @RequestMapping("/file/upload")
    @ResponseBody
    public ControllerResult upload(HttpServletRequest request, MultipartFile file) {
        if (file.isEmpty()) {
            return ControllerResult.error("文件为空", null);
        }
        String ipAbbr = IpUtil.getIpAddr(request);

        return fileService.upload(file, ipAbbr);
    }

    //文件下载相关代码
    @ResponseBody
    @RequestMapping("/file/download")
    public ControllerResult downloadFile(String fileId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //String fileName = "FileUploadTests.java";
        if (fileId != null) {

            return fileService.downloadFile(fileId, request, response);

            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
            //String realPath = request.getServletContext().getRealPath("//WEB-INF//");
        }
        return ControllerResult.error("文件ID不能为空", null);
    }

    //文件下载相关代码
    @ResponseBody
    @RequestMapping("/file/delete")
    public ControllerResult deleteFile(String fileId, HttpServletResponse response) throws IOException {

        if (fileId != null) {

            Integer i = fileService.deleteFile(fileId);
            JSONObject jsonO = new JSONObject();
            jsonO.put(fileId, i.intValue() > 0);
            response.setContentType("application/json");
            response.getWriter().write(jsonO.toString());
            response.getWriter().flush();
            response.getWriter().close();

        }
        return ControllerResult.error("文件ID不能为空", null);
    }

    //多文件上传
    @ResponseBody
    @RequestMapping("/file/batch/upload")
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();

                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " => " + e.getMessage();
                }
            } else {
                return "You failed to upload " + i + " because the file was empty.";
            }
        }
        return "upload successful";
    }

}
