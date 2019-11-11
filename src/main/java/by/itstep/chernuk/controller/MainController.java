package by.itstep.chernuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String mainPage(){
        return  "main";
    }

    @GetMapping("/")
    public String standarts(){
        return "main";
    }
}
