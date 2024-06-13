package com.example.no0001.Services;

import com.example.no0001.Domain.Ser.Sudoku;
import com.example.no0001.Domain.Trans.SudokuTrans;
import org.springframework.stereotype.Service;

@Service
public interface SudokuGen {
    //数独生成算法
    SudokuTrans GetNewSudoku(int userId);

}
