package com.example.no0001.Services;

import com.example.no0001.Domain.Sudoku;
import org.springframework.stereotype.Service;

@Service
public interface SudokuGen {
    //数独生成算法
    Sudoku SudokuGenerated();
    //随机挖空
    Sudoku KillNum(Sudoku sudoku);
    //获取解
    boolean GetSolver(Sudoku sudoku);
}
