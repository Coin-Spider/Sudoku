package com.example.no0001.Services.Impl;

import com.example.no0001.Domain.Ser.Sudoku;
import com.example.no0001.Domain.Trans.SudokuTrans;
import com.example.no0001.Services.SudokuGen;
import com.example.no0001.Utils.SudokuUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SudokuGenImpl implements SudokuGen {
private final SudokuUtils sudokuUtils=new SudokuUtils();
    @Override
    public SudokuTrans GetNewSudoku(int userId) {
        Sudoku sudoku = sudokuUtils.SudokuGenerated();
        Sudoku sudoku1 = sudokuUtils.KillNum(sudoku);
        sudoku1.FinalShow();
        //存入数据库

        //获取
        SudokuTrans sudokuTrans = new SudokuTrans(sudoku);

        return null;
    }
}