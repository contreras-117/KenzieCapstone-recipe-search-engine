package com.kenzie.appserver.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CacheStore {
    private Cache<String, List<ReviewResponse>> cache;

    public CacheStore(int expiry, TimeUnit timeUnit) {
        // initializing the cache
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(expiry, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public List<ReviewResponse> get(String key) {
        // Retrieve and return the listing
        return cache.getIfPresent(key);
    }

    public void evict(String key) {
        // Invalidate/evict the listing from cache
        cache.invalidate(key);
    }

    public void add(String key, List<ReviewResponse> value) {
        // Add listing to cache
        cache.put(key, value);
    }
}
