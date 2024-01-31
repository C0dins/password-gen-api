package me.shaivil.passwordgenapi.security.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RatelimitHandler implements HandlerInterceptor {
    private final double REQUESTS_PER_SECOND = 10 / 60.0;

    private final RateLimiter rateLimiter = RateLimiter.create(REQUESTS_PER_SECOND);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!rateLimiter.tryAcquire()) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Rate limit exceeded\"}");
            return false;
        }

        return true;
    }
}
