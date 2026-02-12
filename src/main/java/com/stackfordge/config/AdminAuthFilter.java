package com.stackfordge.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class AdminAuthFilter extends OncePerRequestFilter {

    @Value("${ADMIN_SECRET}")
    private String adminSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();

        // Skip health endpoint
        if (path.equals("/api/contacts/health")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Protect admin endpoints
        boolean isAdminEndpoint =
                path.startsWith("/api/contacts") &&
                        (method.equals("GET") ||
                                method.equals("PUT") ||
                                method.equals("DELETE"));

        if (isAdminEndpoint) {

            String secret = request.getHeader("X-ADMIN-SECRET");

            if (secret == null || !secret.equals(adminSecret)) {

                log.warn("Unauthorized admin access attempt from IP: {}",
                        request.getRemoteAddr());

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("""
                        {
                          "success": false,
                          "message": "Unauthorized access"
                        }
                        """);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
