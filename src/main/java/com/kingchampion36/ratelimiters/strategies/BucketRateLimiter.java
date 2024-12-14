package com.kingchampion36.ratelimiters.strategies;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;

public final class BucketRateLimiter<T> implements RateLimiter<T> {
  private final Cache<T, Integer> cache =
      Caffeine.newBuilder().maximumSize(1000).expireAfterWrite(1, TimeUnit.MINUTES).build();

  private final Integer ALLOWED_TOKENS = 5;

  @Override
  public boolean isAllowed(T key) {
    Integer currentToken = cache.getIfPresent(key);
    if (currentToken == null) {
      cache.put(key, 1);
      return true;
    }
    if (currentToken + 1 <= ALLOWED_TOKENS) {
      cache.put(key, currentToken + 1);
      return true;
    }
    return false;
  }
}
