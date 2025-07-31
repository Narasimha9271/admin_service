package com.bank.admin_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AdminDetailsService adminDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // ✅ 1️⃣ Extract JWT token from header
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtService.extractUsername(token);
            } catch (Exception e) {
                logger.warn("⚠️ Invalid JWT token format: " + e.getMessage());
            }
        }

        // ✅ 2️⃣ Validate and set authentication if not already set
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = adminDetailsService.loadUserByUsername(username);

            if (jwtService.validateToken(token)) {
                // ✅ Extract role from token (e.g., ADMIN)
                String role = jwtService.extractRole(token);

                // ✅ 3️⃣ Store JWT token in credentials (previously it was null!)
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                token,   // ✅ store JWT here for forwarding to other services
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
                        );

                // ✅ 4️⃣ Add request details & set security context
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                logger.warn("⚠️ JWT token validation failed");
            }
        }

        // ✅ 5️⃣ Continue filter chain
        filterChain.doFilter(request, response);
    }
}
