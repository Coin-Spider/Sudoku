package com.example.no0001;

import com.auth0.jwt.JWT;
import com.example.no0001.Domain.Ser.Sudoku;
import com.example.no0001.Services.Impl.SudokuGenImpl;
import com.example.no0001.Utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class No0001ApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        String token = new JwtUtils().getToken(1, "Rum");
        System.out.println(token);
        System.out.println(JWT.decode(token).getExpiresAt());
    }
}
