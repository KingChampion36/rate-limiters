package com.kingchampion36.ratelimiters;

import com.kingchampion36.ratelimiters.strategies.BucketRateLimiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RateLimitersApplication {
  public static void main(String[] args) {
    SpringApplication.run(RateLimitersApplication.class, args);
  }

  @Bean
  public BucketRateLimiter<String> bucketRateLimiter() {
    return new BucketRateLimiter<>();
  }
}
