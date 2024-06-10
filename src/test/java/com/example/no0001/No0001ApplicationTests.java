package com.example.no0001;

import com.example.no0001.Domain.Sudoku;
import com.example.no0001.Services.Impl.SudokuGenImpl;
import com.example.no0001.Services.SudokuGen;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@SpringBootTest
class No0001ApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {

        Sudoku sudoku = new SudokuGenImpl().SudokuGenerated();
        Sudoku sudoku1 = new SudokuGenImpl().KillNum(sudoku);
//        new SudokuGenImpl().GetSolver(sudoku1);
        System.out.println("结果:");
        sudoku1.FinalShow();
    }
}
