package com.wrdao.springboot.file.service;

import com.wrdao.springboot.file.dao.FileDao;
import com.wrdao.springboot.file.vo.FileVo;
import com.wrdao.springboot.util.ControllerResult;
import com.wrdao.springboot.util.UUIDTool;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Service
public class FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${spring.servlet.multipart.location}")
    private String partLocation;

    @Autowired
    private FileDao fileDao;

    public ControllerResult upload(MultipartFile file, String ipAbbr) {
        FileVo fileVo = new FileVo();
        // 获取文件名
        String fileName = file.getOriginalFilename();

        logger.debug("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        if (fileName.indexOf('.') == -1) {
            fileVo.setFileName(fileName);
            fileVo.setExtension(null);
        } else {
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileVo.setExtension(fileName.substring(fileName.lastIndexOf(".")));
            int num = suffixName.length();//得到后缀名长度
            String fileOtherName = fileName.substring(0, fileName.length() - num);
            fileVo.setFileName(fileOtherName);
        }

        fileVo.setContentType(file.getContentType());
        fileVo.setDownloadNum(0);
        fileVo.setFilePath(partLocation);
        fileVo.setSize(file.getSize());
        fileVo.setDisplayIndex(0);
        fileVo.setIpAddr(ipAbbr);
        fileVo.setFileId(UUIDTool.getUUID());
        fileVo.initCreator();

        File dest = new File(partLocation + fileVo.getFileId());
        // 检测是否存在目录

        if (!dest.getParentFile().exists()) {
            if (!dest.getParentFile().mkdirs()) {
                return ControllerResult.error("上传失败,存储路径不存在", null);
            }
        }
        try {
            file.transferTo(dest);
            fileDao.insert(fileVo);
            return ControllerResult.success("上传成功", fileVo);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        return ControllerResult.error("上传失败", null);
    }

    public ControllerResult downloadFile(String fileId, HttpServletRequest request, HttpServletResponse response) throws IOException {

        FileVo fileVo = fileDao.get(fileId);

        if (fileVo == null) {
            return ControllerResult.error("文件不存在", fileId);
        }
        File file = new File(fileVo.getFilePath(), fileId);
        if (file.exists()) {

            String agent = request.getHeader("User-Agent");
            // 根据不同的客户端进行不同的编码
            String filenameEncoder = "";
            if (agent.contains("MSIE")) {
                // IE浏览器
                filenameEncoder = URLEncoder.encode(fileVo.getFileName(), "utf-8");
                filenameEncoder = filenameEncoder.replace("+", " ");
            } else if (agent.contains("Firefox")) {
                // 火狐浏览器
                BASE64Encoder base64Encoder = new BASE64Encoder();
                filenameEncoder = "=?utf-8?B?" + base64Encoder.encode(fileVo.getFileName().getBytes("utf-8")) + "?=";
            } else {
                // 其它浏览器
                filenameEncoder = URLEncoder.encode(fileVo.getFileName(), "utf-8");
            }

            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + filenameEncoder + (fileVo.getExtension() != null ? fileVo.getExtension() : ""));// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;

            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }

            bis.close();
            fis.close();

            fileDao.downloadSuccess(fileId);

        } else {
            return ControllerResult.error("文件获取失败", null);
        }
        return null;
    }
}
