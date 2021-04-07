package dev.park.e.bookcafemanager.controller;

import dev.park.e.bookcafemanager.dto.HttpResponseBody;
import dev.park.e.bookcafemanager.dto.SeojiInfoDto;
import dev.park.e.bookcafemanager.service.SeojiApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SeojiApiController {

    private final SeojiApiService seojiApiService;

    @GetMapping(value = "api/seoji-info/{isbn}", produces = "application/json; charset=utf-8")
    public ResponseEntity<HttpResponseBody> getBookByIsbn(@PathVariable("isbn") String isbn) {
        SeojiInfoDto seojiInfoDto = seojiApiService.getSeojiInfoByIsbn(isbn);
        HttpResponseBody body = new HttpResponseBody("조회되었습니다.", seojiInfoDto);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
