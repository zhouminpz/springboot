package com.zm.controller;

import com.zm.model.User;
import com.zm.oss_upload_file.OssUploadUtil;
import com.zm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;

/**
 * @Description:
 * @Author: zhoumin
 * @Date: created in 2017-12-19 14:45
 **/
@Controller
public class HelloWorldController {
    @Resource
    private UserService userService;

    @RequestMapping("index")
    @ResponseBody
    public String index(HttpServletRequest request, Model model) {

        User user = new User();
        user.setPassword("123456");
        user.setPhone("17612174073");
        user.setUserName("李四");
//        Integer id = userService.addUser(user);
//        return "hello world " + id;
        return "hello world ";
    }

    @RequestMapping(value="/test")
    public String test() {
        return "HelloJsp";
    }

    @RequestMapping(value = "mutipartupload")
    @ResponseBody
    public String mutiPartUpload(MultipartHttpServletRequest multiRequest){
        Iterator<String> t = multiRequest.getFileNames();
        MultipartFile fileDetail = multiRequest.getFile(t.next());
        String fileName = fileDetail.getOriginalFilename();
        return new OssUploadUtil().mutipartupload(fileDetail,fileName);
    }

    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(MultipartHttpServletRequest multiRequest){
        Iterator<String> t = multiRequest.getFileNames();
        MultipartFile fileDetail = multiRequest.getFile(t.next());
        String fileName = fileDetail.getOriginalFilename();
        try {
            return new OssUploadUtil().upload(fileDetail.getInputStream(),fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败！";
    }
}
