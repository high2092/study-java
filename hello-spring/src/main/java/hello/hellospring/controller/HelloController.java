package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // URL
    public  String hello(Model model) {
        model.addAttribute("data", "hhyuw");
        return "hello"; // 템플릿 파일 이름
    }
}
