package com.wrdao.springboot.file.service;

import com.wrdao.springboot.file.dao.FileDao;
import com.wrdao.springboot.file.vo.FileVo;
import com.wrdao.springboot.util.ControllerResult;
import com.wrdao.springboot.util.UUIDTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
            fileVo.setExtension(fileName.substring(fileName.lastIndexOf(".") + 1));
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
            if(!dest.getParentFile().mkdirs()){
                return ControllerResult.error("上传失败,存储路径不存在", null);
            }
        }
        try {
            file.transferTo(dest);
            fileDao.insert(fileVo);
            return ControllerResult.success("上传成功", null);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        return ControllerResult.error("上传失败", null);
    }

}
