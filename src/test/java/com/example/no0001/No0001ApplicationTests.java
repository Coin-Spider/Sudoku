package com.example.no0001;

import com.example.no0001.Services.Impl.SudokuGenImpl;
import com.example.no0001.Services.SudokuGen;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class No0001ApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        new SudokuGenImpl().SudokuGenerated();
    }
}
