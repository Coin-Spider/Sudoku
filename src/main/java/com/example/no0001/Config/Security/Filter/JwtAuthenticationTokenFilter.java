package com.example.no0001.Config.Security.Filter;

import com.auth0.jwt.JWT;
import com.example.no0001.Config.Security.KSCAuthenticationProvider;
import com.example.no0001.Config.Security.SecurityUser;
import com.example.no0001.Core.Except.ApiExcept;
import com.example.no0001.Utils.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private KSCAuthenticationProvider kscAuthenticationProvider;
    private JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
        if (request.getRequestURI().contains("/User/Login")||request.getRequestURI().contains("/User/Register")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Token");
        if (Objects.isNull(token) || token.isBlank()||token.split("\\.").length!=3) {
            filterChain.doFilter(request, response);
            return;
        }
        if (token.startsWith("Bearer ")) {
            token = token.split("Bearer ")[1];
        }
        if (!jwtUtils.checkExpire(token)) {
            throw new ApiExcept("0003", "token过期");
        }
        String userName = String.valueOf(JWT.decode(token).getClaim("userName"));
        if (userName.isBlank()) {
            throw new ApiExcept("0004", "Token无效");
        }
        SecurityUser s= (SecurityUser) kscAuthenticationProvider.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(s, s.getPassword(), s.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}

