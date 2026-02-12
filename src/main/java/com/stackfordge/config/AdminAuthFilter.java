package com.stackfordge.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AdminAuthFilter extends OncePerRequestFilter {

    @Value("${admin.secret}")
    private String adminSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Protect admin endpoints only
        if (path.startsWith("/api/contacts") &&
                (request.getMethod().equals("GET") ||
                        request.getMethod().equals("DELETE") ||
                        request.getMethod().equals("PUT"))) {

            String secret = request.getHeader("X-ADMIN-SECRET");

            if (secret == null || !secret.equals(adminSecret)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

