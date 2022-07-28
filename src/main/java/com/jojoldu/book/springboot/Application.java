package com.jojoldu.book.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        //내장 WAS란 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS 실행
        //언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있음.
        //톰캣을 설치할 필요 없음.
    }
}
