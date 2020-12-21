package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class) //
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
                // WebMvcTest는 @ControllerAdvice, @Controller를 읽음. 즉 @Repository, @Service, @Component는 스캔 대상이 아님
                // 그래서 시큐리티가 적용되면 classes = SecurityConfig.class 써줘야 한다.
        }
)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc; // web API를 테스트 할 때 사용. HTTP GET, POST등에 대한 API 테스트 가능


    @Test
    @WithMockUser(roles = "USER")
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // HTTP GET 요청
                .andExpect(status().isOk()) // HTTP header status 검증
                .andExpect(content().string(hello)); // controller return 검증
    }

    @Test
    @WithMockUser(roles = "USER")
    public void helloDto() throws Exception{
        String name = "test";
        int amount = 1000;

        mvc.perform(get("/hello/dto")// HTTP GET 요청
                .param("name", name) // API 테스트할 때 사용될 요청 파라미터 값은 String만 허용
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk()) // HTTP header status 검증
                .andExpect(jsonPath("$.name", is(name))) // JSON 응답 값을 필드별로 검증하는 메소드
                .andExpect(jsonPath("$.amount", is(amount)));

    }

}