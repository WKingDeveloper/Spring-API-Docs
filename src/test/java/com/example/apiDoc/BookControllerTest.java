package com.example.apiDoc;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.hamcrest.Matchers.notNullValue;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookController.class)
@AutoConfigureRestDocs
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getBook() throws Exception {

        mockMvc.perform(RestDocumentationRequestBuilders.get("/book/{id}", 1l)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id", (notNullValue()))) // (5)
                .andExpect(jsonPath("$.title", (notNullValue())))
                .andExpect(jsonPath("$.author", (notNullValue())))

                .andDo(document("book", // (3)
                        pathParameters(
                                parameterWithName("id").description("book unique id") // (4)
                        ),
                        responseFields(
                                fieldWithPath("id").description("book unique id"),
                                fieldWithPath("title").description("title"),
                                fieldWithPath("author").description("author")
                        )
                ))
                .andDo(document("book",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("책 Read")
                                        .summary("책 정보")
                                        .pathParameters(
                                                parameterWithName("id").description("book unique id")
                                        )
                                        .responseFields(
                                                fieldWithPath("id").description("book unique id"),
                                                fieldWithPath("title").description("title"),
                                                fieldWithPath("author").description("author")
                                        ).build()
                        )
                ));
        ;

    }

}
