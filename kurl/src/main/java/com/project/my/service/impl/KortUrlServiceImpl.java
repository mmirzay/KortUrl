package com.project.my.service.impl;

import com.project.my.configuration.UrlHazelcastConfiguration;
import com.project.my.entity.Url;
import com.project.my.exception.NotFoundException;
import com.project.my.in.CreateShortUrlInDto;
import com.project.my.repository.UrlRepository;
import com.project.my.service.interfaces.KortUrlService;
import com.project.my.util.MessageTranslatorUtil;
import com.project.my.util.UrlIdentifierUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = UrlHazelcastConfiguration.urlCacheName)
public class KortUrlServiceImpl implements KortUrlService {

    private final UrlRepository urlRepository;

    @Override
    @Transactional()
    public String createShortUrl(CreateShortUrlInDto dto) {
        String shortUrl = UrlIdentifierUtil.identifierOf(dto.getUrl());
        if (shortUrlExists(shortUrl))
            return shortUrl;

        Url url = dto.toUrl(shortUrl);
        urlRepository.save(url);
        return shortUrl;
    }

    private boolean shortUrlExists(String shortUrl) {
        return findCachedUrl(shortUrl).isPresent();
    }

    @Cacheable
    public Optional<Url> findCachedUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

    @Override
    @Transactional(readOnly = true)
    public String getLongUrl(String shortUrl) {
        Url url = findCachedUrl(shortUrl)
                .orElseThrow(() -> new NotFoundException(MessageTranslatorUtil.getText("service.url.get.long.url.not.found")));
        return url.getLongUrl();
    }
}
