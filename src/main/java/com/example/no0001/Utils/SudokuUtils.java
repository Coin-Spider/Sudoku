package com.example.no0001.Utils;

import com.example.no0001.Domain.Ser.Sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SudokuUtils {
    private int time=0;
    private final Random random = new Random();
    private final int Max_Num=36;
    ArrayList<ArrayList<Integer>> can = new ArrayList<>();
    {
        for (int i = 0; i < 81; i++) {
            can.add(new ArrayList<>());
        }
    }
    public Sudoku SudokuGenerated() {
        Random r = new Random();

        ArrayList<Integer> exchangeMode = new ArrayList<>();
        for (int i = 0; i < 33; i++) {
            exchangeMode.add(r.nextInt(4));
        }

        Collections.shuffle(exchangeMode);//原始算法   (可修改)

        return new Sudoku(exchangeMode);
    }

    public Sudoku KillNum(Sudoku sudoku) {



        int[][] gong = sudoku.getGong();
        Sudoku sudoku1=new Sudoku();
        sudoku1.setGong(gong);
        sudoku1.UpdateRowAndColumn();
        System.out.println("刚开始的肃毒列表");
        sudoku.FinalShow();
        int num = 0;
        while (num <=  Max_Num) {
            int anInt = random.nextInt(81);
            if (gong[anInt / 9][anInt % 9] == 0) {
                continue;
            }
            gong[anInt / 9][anInt % 9] = 0;
            num++;
        }
        sudoku1.setGong(gong);
        sudoku1.UpdateRowAndColumn();
        boolean solver = GetSolver(sudoku1);
        if (solver){
            System.out.println("成功生成");
            sudoku1.FinalShow();
        }else {
//            if (time>=2){
//                System.out.println("失败");
//                return sudoku;
//            }
            time++;
            System.out.println("第"+time+"次进行挖空");
            KillNum(sudoku);
        }
        return sudoku1;
    }

    public boolean GetSolver(Sudoku sudoku) {
        System.out.println("第"+time+"次进行求解");
//        int [][][] can=new int[9][9][9];
        int num =  Max_Num;


        Sudoku sudoku1=new Sudoku();
        sudoku1.setGong(sudoku.getGong());
        sudoku1.UpdateRowAndColumn();

        int[][] gong = sudoku1.getGong();
        int[][] hang = sudoku1.getHang();
        int[][] lie = sudoku1.getLie();

        int temp=0;
        while (num > 0) {
            if (temp>=5){
                return false;
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (gong[i / 3 * 3 + j / 3][i % 3 * 3 + j % 3] != 0) {
                        continue;
                    }
                    for (int k = 1; k <= 9; k++) {
                        if (canIn(i / 3 * 3 + j / 3,gong,k)&&canIn(i,hang,k)&&canIn(j,lie,k)){
                            can.get(i*9+j).add(k);
                        }
                    }
                    if (can.get(i*9+j).size()==1){
                        gong[i / 3 * 3 + j / 3][i % 3 * 3 + j % 3]=can.get(i*9+j).get(0);
                        hang[i][j]=can.get(i*9+j).get(0);
                        lie[j][i]=can.get(i*9+j).get(0);
                        num--;
                    }
                    can.get(i*9+j).clear();
                }
            }
            temp++;
        }

        return true;
    }

    boolean canIn(int target, int[][] need, int num) {
        for (int j = 0; j < 9; j++) {
            if (need[target][j] == num) {
                return false;
            }
        }
        return true;
    }
}
