//package com.linkmart.filter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.List;
//
//@Component
//public class AdminGuardFilter extends OncePerRequestFilter {
//    final Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        var session = request.getSession(true);
//        var user = (Integer) session.getAttribute("user");
//        if(user != null){
//           filterChain.doFilter(request,response);
//        }else{
//            response.setStatus(401);
//            response.getWriter().write("{\"message\":\"Unauthorized\"}");
//        }
//    }
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        var pathMatcher = new AntPathMatcher();
//        List<String> matchedPatterns = List.of(
//                "/api/admin/*"
//        );
//
//        return !matchedPatterns
//                .stream()
//                .anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
//    }
//}
