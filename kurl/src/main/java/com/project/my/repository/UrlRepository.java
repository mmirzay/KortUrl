package com.project.my.repository;

import com.project.my.configuration.UrlHazelcastConfiguration;
import com.project.my.entity.Url;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@CacheConfig(cacheNames = UrlHazelcastConfiguration.urlCacheName)
public interface UrlRepository extends BaseJpaRepository<Url, String> {

    Optional<Url> findByShortUrl(String shortUrl);

}
