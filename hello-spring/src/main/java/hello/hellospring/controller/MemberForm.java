package hello.hellospring.controller;

public class MemberForm {
    private String name; // form 태그의 "name" attribute

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
