package com.example.no0001.Controller;

import com.example.no0001.Core.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@RestController
@RequestMapping("/User")
public class UserController {
    //前端检测无token用户注册
    public Response Register(){
        //向数据库添加用户
        //保存完了后进行无感登录,返回token
        return new Response("200","");
    }
    /**
     * 登录
     */
    public Response login(String json){

        return new Response("200","");
    }
}
