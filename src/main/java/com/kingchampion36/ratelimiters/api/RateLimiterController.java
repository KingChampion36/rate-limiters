package com.kingchampion36.ratelimiters.api;

import com.kingchampion36.ratelimiters.strategies.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RateLimiterController {
  private final RateLimiter<String> rateLimiter;

  @GetMapping("/unlimited")
  public ResponseEntity<String> unlimited() {
    return ResponseEntity.ok("Unlimited! Let's Go!");
  }

  @GetMapping("/limited")
  public ResponseEntity<String> limited(HttpServletRequest request) {
    final String ipAddress = request.getRemoteAddr();
    if (rateLimiter.isAllowed(null)) {
      return ResponseEntity.ok("Limited, don't over use me!");
    }
    return ResponseEntity.status(429).body("Too many requests, slow down!");
  }
}
