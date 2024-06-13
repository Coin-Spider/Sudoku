package com.example.no0001.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
@Component
public class JwtUtils {
    //设置默认过期时间(1年)
    private final long expires=365L;
    //获取当前时间
    public ZonedDateTime getNow(){
       return ZonedDateTime.now();
    }
    //生成token
    public String getToken(int userId,String userName){
        Date expireDate= Date.from(getNow().plusDays(expires).toInstant());
        return JWT.create()
                .withExpiresAt(expireDate)
                .withClaim("UserId",userId)
                .withClaim("UserName",userName)
                .sign(Algorithm.HMAC256("sign"));
    }
    //验证token是否过期
    public boolean checkExpire(String Token){
        return getNow().toInstant().isBefore(JWT.decode(Token).getExpiresAt().toInstant());
    }
}
