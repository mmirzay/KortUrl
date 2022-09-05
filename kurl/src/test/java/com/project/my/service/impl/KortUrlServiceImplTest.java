package com.project.my.service.impl;

import com.project.my.exception.InternalException;
import com.project.my.exception.NotFoundException;
import com.project.my.in.CreateShortUrlInDto;
import com.project.my.service.interfaces.KortUrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class KortUrlServiceImplTest {

    @Autowired
    private KortUrlService urlService;

    @Test
    void givenALongUrl_whenCreatingShortUrl_thenEverythingMustBeOk() {
        CreateShortUrlInDto sampleUrlInDto = createSampleUrlInDto();

        String shortUrl = urlService.createShortUrl(sampleUrlInDto);

        assertNotNull(shortUrl);
    }

    @Test
    void givenAnInvalidLongUrl_whenCreatingShortUrl_thenMustThrowException() {
        CreateShortUrlInDto invalidUrlInDto = createInvalidUrlInDto();

        assertThrows(InternalException.class, () -> urlService.createShortUrl(invalidUrlInDto));

    }

    @Test
    void givenTwoSimilarLongUrls_whenCreatingShortUrl_thenResultMustBeSame() {
        CreateShortUrlInDto sampleUrlInDto1 = createSampleUrlInDto();
        CreateShortUrlInDto sampleUrlInDto2 = createSampleUrlInDto();

        String shortUrl1 = urlService.createShortUrl(sampleUrlInDto1);
        String shortUrl2 = urlService.createShortUrl(sampleUrlInDto2);

        assertEquals(shortUrl1, shortUrl2);
    }

    @Test
    void givenPartialDifferentLongUrls_whenCreatingShortUrl_thenResultMustBeDifferent() {
        CreateShortUrlInDto sampleUrlInDto = createSampleUrlInDto();
        CreateShortUrlInDto partialDifferent = createPartialDifferentLongUrl(sampleUrlInDto.getUrl());

        String shortUrl = urlService.createShortUrl(sampleUrlInDto);
        String partialDifferentShortUrl = urlService.createShortUrl(partialDifferent);

        assertNotEquals(shortUrl, partialDifferentShortUrl);
    }

    @Test
    void givenALongUrlAndItsRelatedShortUrl_whenGetLongUrl_thenEverythingMMustBeOk() {
        CreateShortUrlInDto sampleUrlInDto = createSampleUrlInDto();
        String shortUrl = urlService.createShortUrl(sampleUrlInDto);

        String longUrl = urlService.getLongUrl(shortUrl);

        assertEquals(sampleUrlInDto.getUrl(), longUrl);
    }

    @Test
    void givenAShortUrlNotAddedBefore_whenGetLongUrl_thenMustThrowNotFoundException() {
        String shortUrl = "invalid_short_url";

        assertThrows(NotFoundException.class, () -> urlService.getLongUrl(shortUrl));

    }


    private CreateShortUrlInDto createSampleUrlInDto() {
        String url = "https://www.google.com/search?q=long+url+adrress&oq=long+url&aqs=chrome.0.69i59j69i57j0i271l3j69i60l3.3596j0j7&sourceid=chrome&ie=UTF-8";
        return CreateShortUrlInDto.builder()
                .url(url)
                .build();
    }

    private CreateShortUrlInDto createPartialDifferentLongUrl(String url) {
        return CreateShortUrlInDto.builder().url(url + "d").build();
    }

    private CreateShortUrlInDto createInvalidUrlInDto() {
        String url = "http::something@invalid/";
        return CreateShortUrlInDto.builder()
                .url(url)
                .build();
    }
}