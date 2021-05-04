package com.changgou.controller;

import com.changgou.file.FastDFSFile;
import com.changgou.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.csource.common.MyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.ResultSet;

/**
 * @program: changgou
 * @description:
 * @author: YW
 * @create: 2021-04-12 23:13
 **/
@RestController
@RequestMapping(value = "/file")
@CrossOrigin
public class FileController {

    @PostMapping(value = "/upload")
    public Result uploadFile(@RequestParam("file")MultipartFile file) throws IOException, MyException {
        final FastDFSFile fastDFSFile = new FastDFSFile(file.getOriginalFilename(), file.getBytes(), StringUtils.getFilenameExtension(file.getOriginalFilename()));

        final String[] upload = FastDFSUtil.upload(fastDFSFile);
        if(upload != null) {
            final StringBuilder stringBuilder = new StringBuilder("http://192.168.56.129:8888/");
            stringBuilder.append(upload[0] + "/" + upload[1]);
            return new Result(true, StatusCode.OK, "文件上传成功", stringBuilder.toString());
        }
        return new Result(true, StatusCode.ERROR, "文件上传失败！");
    }

}
