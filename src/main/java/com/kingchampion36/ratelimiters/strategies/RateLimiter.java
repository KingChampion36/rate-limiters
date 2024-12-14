package com.kingchampion36.ratelimiters.strategies;

public interface RateLimiter<T> {
  boolean isAllowed(T key);
}
