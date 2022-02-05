package com.example.apiDoc;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@AutoConfigureRestDocs
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getBook() throws Exception {

        // given
        Long id = 1l;
        // when
        ResultActions result = this.mockMvc.perform(get("/book/" + id)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON));
        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andDo(document("book")); // (3)
    }
}
