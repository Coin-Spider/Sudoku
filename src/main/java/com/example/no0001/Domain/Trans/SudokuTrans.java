package com.example.no0001.Domain.Trans;

import com.example.no0001.Domain.Ser.Sudoku;

public class SudokuTrans {
    private int SudokuId;
    private int[][] Gong = new int[9][9];
    private int[][] Hang = new int[9][9];
    private int[][] Lie = new int[9][9];
    private int userId;

    public SudokuTrans(Sudoku sudoku) {
        Gong = sudoku.getGong();
        Hang = sudoku.getHang();
        Lie = sudoku.getLie();
        userId = sudoku.getUserId();
        SudokuId=sudoku.getSudokuId();
    }

    public int getSudokuId() {
        return SudokuId;
    }

    public void setSudokuId(int sudokuId) {
        SudokuId = sudokuId;
    }

    public int[][] getGong() {
        return Gong;
    }

    public void setGong(int[][] gong) {
        Gong = gong;
    }

    public int[][] getHang() {
        return Hang;
    }

    public void setHang(int[][] hang) {
        Hang = hang;
    }

    public int[][] getLie() {
        return Lie;
    }

    public void setLie(int[][] lie) {
        Lie = lie;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
