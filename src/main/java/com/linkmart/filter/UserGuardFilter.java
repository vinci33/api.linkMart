package com.linkmart.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class UserGuardFilter extends OncePerRequestFilter {

    @Autowired
    Environment env;


    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var header = request.getHeader("Authorization");
            var token = header.split(" ")[1];
            if (token == null) {
                throw new Exception("Invalid Token");
            }
            var decode = JWT.require(Algorithm.HMAC256(Objects.requireNonNull(env.getProperty("jwt.secret"))))
                    .build()
                    .verify(token);
            var userId = decode.getClaim("userId").asString();
            if (userId == null) {
                throw new Exception("No UserId In Token");
            }
            request.setAttribute("userId", userId);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"Permission Denied\"}");

        }
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        var pathMatcher = new AntPathMatcher();
        List<String> matchedPatterns = List.of(
                "/user/*/*/*"
        );

        return !matchedPatterns
                .stream()
                .anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
    }
}
