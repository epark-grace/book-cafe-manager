package dev.park.e.bookcafemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.park.e.bookcafemanager.dto.HttpResponseBody;
import dev.park.e.bookcafemanager.dto.SeojiInfoDto;
import dev.park.e.bookcafemanager.service.SeojiApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.file.Paths;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SeojiApiController.class)
class SeojiApiControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SeojiApiService seojiApiService;

    @Test
    void isbn으로_서지정보_조회() throws Exception {
        //given
        String uri = "/api/seoji-info/{isbn}";
        String isbn = "9791100000000";
        SeojiInfoDto seojiInfoDto = objectMapper.readValue(Paths.get("src/test/resources/seoji-api-response.json").toFile(), SeojiInfoDto.class);
        given(seojiApiService.getSeojiInfoByIsbn(isbn)).willReturn(seojiInfoDto);
        HttpResponseBody body = new HttpResponseBody("조회되었습니다.", seojiInfoDto);

        //when
        ResultActions resultActions = mockMvc.perform(get(uri, isbn));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(body)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
    }
}