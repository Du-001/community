package com.community.controller;

import com.alibaba.fastjson.JSON;
import com.community.entity.Result;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class UploadController {

    @ResponseBody
    @PostMapping("/file/upload")
    public Object uploadFile(HttpServletRequest request) {

        final String URL = "http://localhost:8887/file/";

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("editormd-image-file");
        //返回URL路径list
        List<String> urlList = new ArrayList<>(files.size());

        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                Result.error("上传第" + (i++) + "个文件失败");
            }
            //获取项目classes/static的地址
            String path = "E:\\community";
            String fileName = file.getOriginalFilename();  //获取文件名
            //文件后缀
            String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            //新的文件名
            fileName = System.currentTimeMillis()+"_"+new Random().nextInt(1000) + fileSuffix;

            String savePath = path+File.separator+fileName;  //图片保存路径
            File saveFile = new File(savePath);
            //父文件夹不存在
            if (!saveFile.getParentFile().exists()){
                saveFile.getParentFile().mkdir();
            }
            try {
                file.transferTo(saveFile);  //将临时存储的文件移动到真实存储路径下

            } catch (IOException e) {
                e.printStackTrace();
            }
            //返回图片访问地址
            String ReQUESTURL =URL+fileName;
            urlList.add(ReQUESTURL);
        }
        String urls =String.join(",",urlList);
        String json = "{\"success\":1,\"message \":\"上传成功\",\"url\":\""+urls+"\"}";
        Object toJSON = JSON.toJSON(json);
        System.out.println(toJSON);
        return toJSON;
    }
}
