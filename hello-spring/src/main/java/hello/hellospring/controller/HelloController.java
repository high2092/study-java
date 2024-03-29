package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // URL
    public  String hello(Model model) {
        model.addAttribute("data", "hhyuw");
        return "hello"; // 템플릿 파일 이름
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // @RequestParam 의 매개변수에는 URL Parameter 이름을 적는다.
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        return new Hello(name); // 객체 반환 시 JSON 문자열로 자동 변환
    }

    static class Hello {
        private String name;

        public Hello(String name) {
            this.name = name;
        }
    }
}
