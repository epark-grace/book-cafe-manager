package dev.park.e.bookcafemanager.service;

import dev.park.e.bookcafemanager.dto.SeojiInfoDto;
import dev.park.e.bookcafemanager.properties.SeojiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class SeojiApiService {

    private final RestTemplate restTemplate;
    private final SeojiProperties properties;

    public SeojiInfoDto getSeojiInfoByIsbn(String isbn) {
        URI uri = UriComponentsBuilder.fromUriString("http://seoji.nl.go.kr/landingPage/SearchApi.do")
                .queryParam("cert_key", properties.getCertKey())
                .queryParam("result_style", properties.getResultStyle())
                .queryParam("page_no", properties.getPageNo())
                .queryParam("page_size", properties.getPageSize())
                .queryParam("isbn", isbn)
                .build().toUri();
        return restTemplate.getForObject(uri, SeojiInfoDto.class);
    }
}
