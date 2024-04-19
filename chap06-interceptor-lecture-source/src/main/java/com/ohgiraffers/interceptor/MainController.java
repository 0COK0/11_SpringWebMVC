package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class MainController {

    // 슬러시 요청이 들어오면 메인페이지로 포워딩
    @RequestMapping("/")
    public String defaultLocation() {
        return "main";
    }

    //
    @RequestMapping("/main")
    public void main() {}



}
