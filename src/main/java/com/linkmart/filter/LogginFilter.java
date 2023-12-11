package com.linkmart.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class LogginFilter extends OncePerRequestFilter {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info(request.getRequestURL().toString());
        filterChain.doFilter(request,response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        var pathMatcher = new AntPathMatcher();
        List<String> matchedPatterns = List.of(
                "/api*","/user*"
        );

        return !matchedPatterns
                .stream()
                .anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
    }
}
