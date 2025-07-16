package com.cricMaster.fantasyICL_backend.controller;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    private final CacheManager cacheManager;

    public CacheController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * View all entries in a named cache (e.g. "battingLeader" or "bowlingLeader").
     */
    @GetMapping("/{cacheName}")
    public ResponseEntity<?> viewCache(@PathVariable String cacheName) {
        Cache springCache = cacheManager.getCache(cacheName);
        if (springCache == null) {
            return ResponseEntity.notFound().build();
        }

        if (springCache instanceof CaffeineCache) {
            // Correctly cast and get the native cache
            // You need to explicitly use the full package name for Caffeine's Cache here
            // if you have `import org.springframework.cache.Cache;`
            com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCaffeineCache =
                    ((CaffeineCache) springCache).getNativeCache();

            Map<Object, Object> entries = nativeCaffeineCache.asMap();
            return ResponseEntity.ok(entries);
        }

        // If you had other cache types (e.g., RedisCache, ConcurrentMapCache),
        // you would add `else if` blocks here.
        return ResponseEntity
                .status(501)
                .body("Cache '" + cacheName + "' is not a Caffeine cache or cannot be inspected.");
    }
}