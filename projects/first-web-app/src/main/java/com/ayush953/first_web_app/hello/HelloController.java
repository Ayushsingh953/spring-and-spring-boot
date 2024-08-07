package com.ayush953.first_web_app.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello World";
    }

    @RequestMapping("/hello-html")
    @ResponseBody
    public String helloHtml(){
        StringBuffer sb = new StringBuffer();
        sb.append("<h1>Hello World</h1>");
        return sb.toString();
    }

    @RequestMapping("/hello-jsp")
    public String helloJsp(){
        return "sayHello";
    }
}
