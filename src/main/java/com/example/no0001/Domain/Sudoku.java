package com.example.no0001.Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Sudoku {
    private int SudokuId;
    private int[][] Gong = new int[9][9];
    private int[][] Column_T = new int[3][3];
    private int[][] Row_T = new int[3][3];
    private int[][] Hang = new int[9][9];
    private int[][] Lie = new int[9][9];
    private HashMap<Integer, Integer> exId = new HashMap<>();
    {
        //交换的序列(宫,行,列)
        exId.put(0, 1);
        exId.put(1, 2);
        exId.put(2, 0);
    }

    private final Random r = new Random();

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

    public int[][] getColumn_T() {
        return Column_T;
    }

    public void setColumn_T(int[][] column_T) {
        Column_T = column_T;
    }

    public int[][] getRow_T() {
        return Row_T;
    }

    public void setRow_T(int[][] row_T) {
        Row_T = row_T;
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

    public HashMap<Integer, Integer> getExId() {
        return exId;
    }

    public void setExId(HashMap<Integer, Integer> exId) {
        this.exId = exId;
    }

    public Random getR() {
        return r;
    }
    public Sudoku(){

    }
    public Sudoku(ArrayList<Integer> exchangeMode){
        ArrayList<Integer> seed = getSeed();
        for (int i = 0; i < 9; i++) {
            Gong[0][i] = seed.get(i);
            Row_T[i / 3][i % 3] = seed.get(i);
            Column_T[i % 3][i / 3] = seed.get(i);
        }
        fillEmpty();
        UpdateRowAndColumn();
        Exchange(exchangeMode);
        FinalShow();
    }
    private ArrayList<Integer> getSeed (){
        ArrayList<Integer> seed= new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            seed.add(i);
        }

        Collections.shuffle(seed);
        return seed;
    }
    /**
     * 最终展示
     */
    public void FinalShow(){
        System.out.println("行记录");
        for (int c = 0; c < 9; c++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(Hang[c][d] + " ");
            }
            System.out.println();
        }
        System.out.println();

        //更新列宫
        //列
        for (int i = 0; i < Hang.length; i++) {
            for (int j = 0; j < 9; j++) {
                Lie[j][i] = Hang[i][j];
            }
        }

        //宫
        for (int i = 0; i < Hang.length; i++) {
            for (int j = 0; j < 9; j++) {
                Gong[i/3*3+j/3][i%3*3+j%3]=Hang[i][j];
            }
        }

        System.out.println("列记录");
        for (int c = 0; c < 9; c++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(Lie[c][d] + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("宫记录");
        for (int c = 0; c < 9; c++) {
            for (int i = 0; i < 9; i++) {
                System.out.print(Gong[c/3*3+i/3][c%3*3+i%3]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 填满空格
     */
    private void fillEmpty(){
        //生成第一行其他宫格
        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    int temp = j + i >= 3 ? j + i - 3 : j + i;
                    Gong[i][temp * 3 + k] = Row_T[j][k];
                }
            }
        }
        //生成完整数独
        for (int i = 0; i < 3; i++) {
            //i:第 i 列
            //存入Column_T
            for (int l = 0; l < 3; l++) {
                for (int c = 0; c < 3; c++) {
                    //l:列标
                    //c:位标
                    //宫位置 c*3+l
                    Column_T[l][c] = Gong[i][c * 3 + l];
                }

            }
            for (int j = 1; j < 3; j++) {
                //j: 第 i 列 第 j 个宫格
                int Gong_id = j * 3 + i;

                for (int n = 0; n < 3; n++) {
                    for (int m = 0; m < 3; m++) {
                        //n:列标
                        //m:行标
                        //错位:temp = n+j>=3? n+j-3 : n+j (行替换)
                        // id = temp*3+n
                        int temp = n + j >= 3 ? n + j - 3 : n + j;//实际列标 1    2   0       2   0   1
                        Gong[Gong_id][m * 3 + temp] = Column_T[n][m];
                        //宫格建立完成
                    }
                }

            }
        }
    }

    /**
     * 获取到随机交换数字
     *
     * @param bound 交换最大值
     * @return  交换对
     */
    private int [] getExIdByRandom(int bound) {
        int[] target = new int[2];
        //获取到要交换的行标
        int need = r.nextInt(bound);
        //获取到所在的宫行
        int GongE = r.nextInt(bound);
        //获取指定行
        target[0] = GongE * 3 + need;
        target[1] = GongE * 3 + exId.get(need);
        return target;
    }

    /**
     * 展示方法
     */
    private void showNow(){
        for (int c = 0; c < 9; c++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(Hang[c][d] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 交换方法
     * @param exchangeMode 交换顺序
     */
    private void Exchange(ArrayList<Integer> exchangeMode){
        for (int i = 0; i < exchangeMode.size(); i++) {
            if (exchangeMode.get(i) == 0) {
                int[] target = getExIdByRandom(3);
                //换行
                exchangeHang(target[0], target[1]);
                showNow();
            } else if (exchangeMode.get(i) == 1) {
                int[] target = getExIdByRandom(3);
                //换列
                exchangeLie(target[0], target[1]);
                showNow();
            } else if (exchangeMode.get(i ) == 2) {
                //获取到所在的宫行
                int need = r.nextInt(3);
                //换宫行前进行操作
                for (int i1 = 0; i1 < 9; i1+=3) {
                    exchangeLie(i1,i1+1);
                    exchangeLie(i1,i1+2);
                    exchangeLie(i1+1,i1+2);
                }
                exchangeHang(need*3,need*3+1);
                exchangeHang(need*3,need*3+2);
                exchangeHang(need*3+1,need*3+2);

                exchangeHang(exId.get(need)*3,exId.get(need)*3+1);
                exchangeHang(exId.get(need)*3,exId.get(need)*3+2);
                exchangeHang(exId.get(need)*3+1,exId.get(need)*3+2);
                //换宫行
                exchangeGongRow(need, exId.get(need));
                showNow();
            } else if (exchangeMode.get(i) == 3) {
                //获取到所在的宫列
                int need = r.nextInt(3);
                //换宫列前进行操作
                for (int i1 = 0; i1 < 9; i1+=3) {
                    exchangeHang(i1,i1+1);
                    exchangeHang(i1,i1+2);
                    exchangeHang(i1+1,i1+2);
                }
                exchangeLie(need*3,need*3+1);
                exchangeLie(need*3,need*3+2);
                exchangeLie(need*3+1,need*3+2);

                exchangeLie(exId.get(need)*3,exId.get(need)*3+1);
                exchangeLie(exId.get(need)*3,exId.get(need)*3+2);
                exchangeLie(exId.get(need)*3+1,exId.get(need)*3+2);

                exchangeGongColumn(need, exId.get(need));
                showNow();
            }

        }
    }

    /**
     * 更新行,列
     */
    public void UpdateRowAndColumn(){
        for (int i = 0; i < 9; i++) {
            //i : 宫位置
            for (int j = 0; j < 9; j++) {
                //j : 数字位置
                //行标: i/3*3+j/3
                int HangId = i / 3 * 3 + j / 3;
                int LieId = (i * 3 + j % 3) % 9;
                Hang[HangId][LieId] = Gong[i][j];
                Lie[LieId][HangId] = Gong[i][j];
            }
        }
    }

    /**
     * 行切换
     * @param target1 切换行标1
     * @param target2 切换航标2
     */
    private void exchangeHang(int target1, int target2) {
        System.out.println("开始换行: " + target1 + " <=> " + target2);
        int []temp_2= Hang[target1];
        Hang[target1]= Hang[target2];
        Hang[target2]=temp_2;

        //列更新
        for (int j = 0; j < Lie.length; j++) {
            int temp_1 = Lie[j][target1];
            Lie[j][target1] = Lie[j][target2];
            Lie[j][target2] = temp_1;
        }
    }

    /**
     * 列切换
     * @param target1 切换列标1
     * @param target2 切换列标2
     */
    private void exchangeLie(int target1, int target2) {
        System.out.println("开始换列: " + target1 + " <=> " + target2);

        int []temp_2=  Lie[target1];
        Lie[target1]= Lie[target2];
        Lie[target2]=temp_2;

        //列更新
        for (int j = 0; j < Lie.length; j++) {
            int temp_1 = Hang[j][target1];
            Hang[j][target1] = Hang[j][target2];
            Hang[j][target2] = temp_1;
        }
    }

    /**
     * 横宫切换
     *
     * @param target1 指定宫行1
     * @param target2 指定宫行2
     */
    private void exchangeGongRow(int target1, int target2) {
        System.out.println("开始换宫行: " + target1 + " <=> " + target2);

        //记录行交换
        int [][]temp=new int[3][9];
        temp[0] = Hang[target1 * 3 + 0];
        temp[1] = Hang[target1 * 3 + 1];
        temp[2] = Hang[target1 * 3 + 2];

        Hang[target1 * 3 + 0]=Hang[target2 * 3 + 0];
        Hang[target1 * 3 + 1]=Hang[target2 * 3 + 1];
        Hang[target1 * 3 + 2]=Hang[target2 * 3 + 2];

        Hang[target2 * 3 + 0]=temp[0];
        Hang[target2 * 3 + 1]=temp[1];
        Hang[target2 * 3 + 2]=temp[2];
        //行切换达成

        //列更新
        for (int i1 = 0; i1 < 9; i1++) {
            int[] temp_3 = new int[3];

            temp_3[0] = Lie[i1][target1 * 3 + 0];
            temp_3[1] = Lie[i1][target1 * 3 + 1];
            temp_3[2] = Lie[i1][target1 * 3 + 2];

            Lie[i1][target1 * 3 + 0] = Lie[i1][target2 * 3 + 0];
            Lie[i1][target1 * 3 + 1] = Lie[i1][target2 * 3 + 1];
            Lie[i1][target1 * 3 + 2] = Lie[i1][target2 * 3 + 2];

            Lie[i1][target2 * 3 + 0] = temp_3[0];
            Lie[i1][target2 * 3 + 1] = temp_3[1];
            Lie[i1][target2 * 3 + 2] = temp_3[2];
        }


    }

    /**
     * 纵宫切换
     *
     * @param target1 指定宫列1
     * @param target2 指定宫列2
     */
    private void exchangeGongColumn(int target1, int target2) {
        System.out.println("开始换宫列: " + target1 + " <=> " + target2);
        int [][]temp=new int[3][9];
        //记录列交换
        temp[0] = Lie[target1 * 3 + 0];
        temp[1] = Lie[target1 * 3 + 1];
        temp[2] = Lie[target1 * 3 + 2];

        Lie[target1 * 3 + 0]=Lie[target2 * 3 + 0];
        Lie[target1 * 3 + 1]=Lie[target2 * 3 + 1];
        Lie[target1 * 3 + 2]=Lie[target2 * 3 + 2];

        Lie[target2 * 3 + 0]=temp[0];
        Lie[target2 * 3 + 1]=temp[1];
        Lie[target2 * 3 + 2]=temp[2];

        //列切换达成

        //行更新
        for (int i1 = 0; i1 < 9; i1++) {
            int[] temp_3 = new int[3];

            temp_3[0] = Hang[i1][target1 * 3 + 0];
            temp_3[1] = Hang[i1][target1 * 3 + 1];
            temp_3[2] = Hang[i1][target1 * 3 + 2];

            Hang[i1][target1 * 3 + 0] = Hang[i1][target2 * 3 + 0];
            Hang[i1][target1 * 3 + 1] = Hang[i1][target2 * 3 + 1];
            Hang[i1][target1 * 3 + 2] = Hang[i1][target2 * 3 + 2];

            Hang[i1][target2 * 3 + 0] = temp_3[0];
            Hang[i1][target2 * 3 + 1] = temp_3[1];
            Hang[i1][target2 * 3 + 2] = temp_3[2];
        }

    }
}
