package com.project.my.service.impl;

import com.project.my.in.CreateShortUrlInDto;
import com.project.my.service.interfaces.KortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KortUrlServiceImpl implements KortUrlService {
    @Override
    public String createShortUrl(CreateShortUrlInDto dto) {
        return "Hello mohsen";
    }
}
