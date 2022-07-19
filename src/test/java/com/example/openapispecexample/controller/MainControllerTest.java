package com.example.openapispecexample.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.example.openapispecexample.OpenApiSpecExampleApplication;
import com.example.openapispecexample.dto.MainRequest;
import com.example.openapispecexample.dto.MainRequest.Patch;
import com.example.openapispecexample.dto.MainRequest.Post;
import com.example.openapispecexample.dto.MainRequest.Put;
import com.example.openapispecexample.dto.MainResponse;
import com.example.openapispecexample.dto.MainResponse.Get;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(MainController.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Get 테스트")
    void getTest() throws Exception {
        MainResponse.Get response = new Get("get test success");
        mockMvc.perform(
                RestDocumentationRequestBuilders.get("/user")
            )
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document("test-get",
                ResourceSnippetParameters.builder()
                    .tag("테스트")
                    .summary("Get 테스트")
                    .description("Get 테스트")
                    .responseSchema(Schema.schema("MainResponse.Get"))
                ,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("message").type(JsonFieldType.STRING).description("메세지")
                )
            ))
            .andExpect(jsonPath("$.message").value(response.message()));

    }

    @Test
    @DisplayName("Post 테스트")
    void postTest() throws Exception {
        MainRequest.Post request = new Post("post request");
        MainResponse.Post response = new MainResponse.Post(1L);
        mockMvc.perform(
                RestDocumentationRequestBuilders.post("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request))
            )
            .andExpect(status().isCreated())
            .andDo(MockMvcRestDocumentationWrapper.document("test-post",
                ResourceSnippetParameters.builder()
                    .tag("테스트")
                    .summary("Post 테스트")
                    .description("Post 테스트")
                    .requestSchema(Schema.schema("MainRequest.Post"))
                    .responseSchema(Schema.schema("MainResponse.Post"))
                ,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("message").type(JsonFieldType.STRING).description("요청 메시지")
                ),
                responseFields(
                    fieldWithPath("id").type(JsonFieldType.NUMBER).description("생성 ID")
                )))
            .andExpect(jsonPath("$.id").value(response.id()));
    }

    @Test
    @DisplayName("Put 테스트")
    void putTest() throws Exception {
        Long requestId = 1L;
        MainRequest.Put requestBody = new Put("put request");
        MainResponse.Put response = new MainResponse.Put("put test success");

        mockMvc.perform(
                RestDocumentationRequestBuilders.put("/user/{id}", requestId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(requestBody))
            )
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document("test-put",
                ResourceSnippetParameters.builder()
                    .tag("테스트")
                    .summary("Put 테스트")
                    .description("Put 테스트")
                    .requestSchema(Schema.schema("MainRequest.Put"))
                    .responseSchema(Schema.schema("MainResponse.Put"))
                ,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("id").description("수정 ID")
                ),
                requestFields(
                    fieldWithPath("message").type(JsonFieldType.STRING).description("요청 메세지")
                ),
                responseFields(
                    fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                )
            ))
            .andExpect(jsonPath("$.message").value(response.message()));
    }

    @Test
    @DisplayName("Patch 테스트")
    void patchTest() throws Exception {
        Long requestId = 1L;
        MainRequest.Patch requestBody = new Patch("patch request");
        MainResponse.Patch response = new MainResponse.Patch("patch test success");

        mockMvc.perform(
                RestDocumentationRequestBuilders.patch("/user/{id}", requestId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(requestBody))
            )
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document("test-patch",
                ResourceSnippetParameters.builder()
                    .tag("테스트")
                    .summary("Patch 테스트")
                    .description("Patch 테스트")
                    .requestSchema(Schema.schema("MainRequest.Patch"))
                    .responseSchema(Schema.schema("MainResponse.Post"))
                ,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("id").description("수정 ID")
                ),
                requestFields(
                    fieldWithPath("message").type(JsonFieldType.STRING).description("요청 메세지")
                ),
                responseFields(
                    fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                )
            ))
            .andExpect(jsonPath("$.message").value(response.message()));
    }

    @Test
    @DisplayName("Delete 테스트")
    void deleteTest() throws Exception {
        Long request = 1L;

        mockMvc.perform(
                RestDocumentationRequestBuilders.delete("/user/{id}", request)
            )
            .andExpect(status().isNoContent())
            .andDo(MockMvcRestDocumentationWrapper.document("test-delete",
                ResourceSnippetParameters.builder()
                    .tag("테스트")
                    .summary("Delete 테스트")
                    .description("Delete 테스트")
                ,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("id").description("삭제 ID")
                )
            ));
    }


}

