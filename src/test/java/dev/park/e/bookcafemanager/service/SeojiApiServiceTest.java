package dev.park.e.bookcafemanager.service;

import dev.park.e.bookcafemanager.dto.SeojiInfoDto;
import dev.park.e.bookcafemanager.properties.SeojiProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(SeojiApiService.class)
@AutoConfigureWebClient(registerRestTemplate = true)
@TestPropertySource("classpath:application.properties")
class SeojiApiServiceTest {

    @Autowired
    MockRestServiceServer mockRestServiceServer;

    @Autowired
    SeojiApiService seojiApiService;

    @Autowired
    SeojiProperties properties;

    @Test
    void isbn으로_서지정보_조회() {
        //given
        String isbn = "9791100000000";
        String response = "/seoji-api-response.json";
        URI uri = UriComponentsBuilder.fromUriString("http://seoji.nl.go.kr/landingPage/SearchApi.do")
                .queryParam("cert_key", properties.getCertKey())
                .queryParam("result_style", properties.getResultStyle())
                .queryParam("page_no", properties.getPageNo())
                .queryParam("page_size", properties.getPageSize())
                .queryParam("isbn", isbn)
                .build().toUri();

        mockRestServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(new ClassPathResource(response), MediaType.APPLICATION_JSON));

        //when
        SeojiInfoDto seojiInfoDto = seojiApiService.getSeojiInfoByIsbn(isbn);

        //then
        assertThat(seojiInfoDto.getIsbn()).isEqualTo(isbn);
        assertThat(seojiInfoDto.getAuthor()).isEqualTo("작가");
        assertThat(seojiInfoDto.getPublisher()).isEqualTo("출판사");
        assertThat(seojiInfoDto.getTitle()).isEqualTo("제목");
        assertThat(seojiInfoDto.getVolume()).isEqualTo(1);

    }

}