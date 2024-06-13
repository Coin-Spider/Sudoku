package com.example.no0001.Config.Security.Hander;

import com.alibaba.fastjson2.JSON;
import com.example.no0001.Core.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        if (Objects.isNull(request.getHeader("Token")) || request.getHeader("Token").isBlank()) {
            //Token 异常
            response.getWriter().println(JSON.toJSONString(new Response("403", "Token违规或过期")));
        } else {
            response.getWriter().println(JSON.toJSONString(new Response("403", "认证失败")));
        }
    }
}
