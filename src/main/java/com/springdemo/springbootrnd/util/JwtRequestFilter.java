package com.springdemo.springbootrnd.util;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springdemo.springbootrnd.security.WebSecurityConfig;
import com.springdemo.springbootrnd.dao.caching.RedisDataAccessService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.springdemo.springbootrnd.services.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    Logger logger = LogManager.getLogger(JwtRequestFilter.class);
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private RedisDataAccessService redisDataAccessService;
    private final String refreshTokenApi = "/api/v1/auth/refresh";
    private final String logoutApi = "/api/v1/auth/logout";

    @Autowired
    public JwtRequestFilter(CustomUserDetailsService customUserDetailsService, JwtTokenUtil jwtTokenUtil, RedisDataAccessService redisDataAccessService) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.redisDataAccessService = redisDataAccessService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
            throws ServletException {
        String path = request.getRequestURI();
        // logger.info("path: " + path);
        for (String ignoredPath : WebSecurityConfig.AUTH_WHITELIST) {
            if (path.equals(ignoredPath)) {
                logger.info("not applying custom filter to: " + path);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        logger.info("doFilterInternal path: " + path);
        String username = null, type = null;
        String jwtToken = extractJwtFromRequest(request);
        Boolean exceptionOccurred = false;
        logger.info("JwtRequestFilter: doFilterInternal jwtToken-> " + jwtToken);
        try {
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            type = jwtTokenUtil.getTypeFromToken(jwtToken);
            logger.info("Valid JWT Token In Header username: " + username);
            logger.info("token type: " + type);
        } catch (IllegalArgumentException e) {
            exceptionOccurred = true;
            logger.warn("Unable to get JWT Token or Token is blacklisted");
        } catch (ExpiredJwtException e) {
            exceptionOccurred = true;
            logger.warn("JWT Token has expired");
        }
        logger.warn("exceptionOccurred "+ exceptionOccurred);
        if (!exceptionOccurred) {
            if (shouldHandleReqWithAccessToken(type, username, path)) {
                logger.info("Handling request with access token");
                // if token is valid configure Spring Security to manually set
                // authentication
                validateTokenAndSetAuthentication(jwtToken, username, request);
            } else if (shouldHandleReqWithRefreshToken(type, username, path)) {
                logger.info("Handling request with refresh token");
                validateTokenAndSetAuthentication(jwtToken, username, request);
                request.setAttribute("username", username);
            }
        }
        chain.doFilter(request, response);
    }

    private void validateTokenAndSetAuthentication(String jwtToken, String username, HttpServletRequest request) {
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // After setting the Authentication in the context, we specify
            // that the current user is authenticated. So it passes the
            // Spring Security Configurations successfully.
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    private boolean shouldHandleReqWithAccessToken(String type, String username, String path) {
        return type.equals("access") &&
                !path.equals(refreshTokenApi) &&
                username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private boolean shouldHandleReqWithRefreshToken(String type, String username, String path) {
        return type.equals("refresh") &&
                (path.equals(refreshTokenApi) || path.equals(logoutApi)) &&
                username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) &&
                bearerToken.startsWith("Bearer ") &&
                !this.redisDataAccessService.isTokenBlacklisted(bearerToken.substring(7))
        ) {
            return bearerToken.substring(7);
        } else if (bearerToken == null) {
            logger.warn("No JWT Token In Header");
        } else if (!bearerToken.startsWith("Bearer ")) {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        else if(this.redisDataAccessService.isTokenBlacklisted(bearerToken.substring(7))){
            logger.warn("JWT Token is blacklisted");
        }
        return null;
    }
}