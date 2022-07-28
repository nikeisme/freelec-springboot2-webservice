package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
// 스프링 부트 테스트와 JUnit 사이에 연결자 역할,
// SpringRunner라 실행자 역할
// 테스트를 진행할 때, JUni는 스프링에 내장된 실행자 외에 다른 실행자 실행시킨다.
@WebMvcTest(controllers = HelloController.class)
// 여러 스프링 테스트 어노테이션 중, Web(Spring mvc)에 집중할 수 있는 어노테이션
// 선언할 경우, @Controller/@ControllerAdivce 등 사용 가능
// @Service, @Component, @Repository등 사용 불가.
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈(Bean)을 주입 받는다.
    private MockMvc mvc; // 웹 API를 테스트할 때 사용 , 스프링 MVC 테스트의 시작점, 이 클래스를 통해 HTTP GET,POST 등에 대한 API 테스트 가능


    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 HTTP GET 요청한다. , 체이닝 지원되어 여러 검증 기능 이어서 선언 가능
                .andExpect(status().isOk()) // Mvc.perform의 결과 검증/ HTTP Header의 status 검증/ (200,404,500 등등), Ok는 200인지 아닌지 검증
                .andExpect(content().string(hello)); // mvc.perform의 결과 검증 / 응답 본문의 내용 검증 / Controller에서 "hello"를 리턴 값이 맞는지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name",name)
                        .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
// jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메소드
// $를 기준으로 필드를 명시
// name과 amount를 검증하니 $.name, $.amount로 검증
}
