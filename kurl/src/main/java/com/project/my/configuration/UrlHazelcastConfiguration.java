package com.project.my.configuration;

import com.hazelcast.config.*;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UrlHazelcastConfiguration {
    private final Integer cacheTimeToLiveSeconds;

    public static final String urlCacheName = "UrlMap";

    @Autowired
    public UrlHazelcastConfiguration(HazelcastInstance hazelcastInstance,
                                     @Value("${cache.config.url.timeToLiveSeconds.seconds}") Integer cacheTimeToLiveSeconds) {
        this.cacheTimeToLiveSeconds = cacheTimeToLiveSeconds;
        initConfig(hazelcastInstance.getConfig());
    }

    private void initConfig(Config config) {
        addUrlMapConfig(config);
    }

    private void addUrlMapConfig(Config config) {
        MapConfig map = new MapConfig(urlCacheName);
        map.setReadBackupData(true);
        map.setEvictionConfig(new EvictionConfig()
                .setEvictionPolicy(EvictionPolicy.LRU));
        map.setBackupCount(0);
        map.setTimeToLiveSeconds(cacheTimeToLiveSeconds);
        config.addMapConfig(map);
    }
}