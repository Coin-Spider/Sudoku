package com.example.no0001.Services.Impl;

import com.example.no0001.Services.SudokuGen;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SudokuGenImpl implements SudokuGen {

    @Override
    public ArrayList<ArrayList<Integer>> SudokuGenerated() {
        ArrayList<Integer> random =new ArrayList<>();

        int [][] Gong=new int[9][9];

        for (int i=1;i<=9;i++){
            random.add(i);
        }
        Collections.shuffle(random);
        int [][] Column_T=new int[3][3];
        int [][] Row_T=new int[3][3];
        //填充第一宫格,并拿到第一行第一列的顺序列表
        for (int i=0;i<9;i++){
            Gong[0][i]=random.get(i);
            Row_T[i/3][i%3]=random.get(i);
            Column_T[i%3][i/3]=random.get(i);
        }
        //展示

        //生成第一行其他宫格
        for (int i=1;i<3;i++){
            for (int j=0;j<3;j++){
                for (int k=0;k<3;k++){
                    int temp=j+i>=3?j+i-3:j+i;
                    Gong[i][temp*3+k]=Row_T[j][k];
                }
            }
        }


        //生成完整数独
        for (int i=0;i<3;i++){
            //i:第 i 列
            //存入Column_T
            for (int l=0;l<3;l++){
                for (int c=0;c<3;c++){
                    //l:列标
                    //c:位标
                    //宫位置 c*3+l
                    Column_T[l][c]=Gong[i][c*3+l];
                }

            }
            for (int j=1;j<3;j++){
                //j: 第 i 列 第 j 个宫格
                int Gong_id = j*3+i;

                for (int n=0;n<3;n++){
                    for (int m=0;m<3;m++){
                        //n:列标
                        //m:行标
                        //错位:temp = n+j>=3? n+j-3 : n+j (行替换)
                        // id = temp*3+n
                        int temp = n+j>=3 ? n+j-3 : n+j;//实际列标 1    2   0       2   0   1
                        Gong[Gong_id][m*3+temp]=Column_T[n][m];
                        //宫格建立完成
                    }
                }

            }
        }
        //行列数组录入
        int [][] Hang=new int[9][9];
        int [][] Lie=new int[9][9];
        for (int i=0;i<9;i++){
            //i : 宫位置
            for (int j=0;j<9;j++){
                //j : 数字位置
                //行标: i/3*3+j/3
                int HangId=i/3*3+j/3;
                int LieId=(i*3+j%3)%9;
                Hang[HangId][LieId]=Gong[i][j];
                Lie[LieId][HangId]=Gong[i][j];
            }
        }


        ArrayList<Integer> exchangeMode=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
                exchangeMode.add(i);
        }


        Collections.shuffle(exchangeMode);
//        Collections.shuffle(exId);
        HashMap<Integer,Integer> exId=new HashMap<>();

        //交换的序列(宫,行,列)
        exId.put(0,1);
        exId.put(1,2);
        exId.put(2,0);
        //交换序列( 宫行,宫列 前缀)
        int [] GongId={0,1,2};

        Random r=new Random();

        for (int c=0;c<9;c++){
            for (int d=0;d<9;d++){
                System.out.print(Hang[c][d]+" ");
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < (exchangeMode.size())*4; i++) {
            if (exchangeMode.get(i%4)==0){
                //获取到要交换的行标
                int need=r.nextInt(3);
                //获取到所在的宫行
                int GongE=r.nextInt(3);
                //获取指定行
                int target1=GongE*3+need;
                int target2=GongE*3+exId.get(need);
                //换行
                int[][] ints = exchangeHang(target1, target2, Hang, Lie);

                Hang[target2]=ints[0];
                Hang[target1]=ints[1];

                System.arraycopy(ints, 2, Lie, 0, 9);

                for (int c=0;c<9;c++){
                    for (int d=0;d<9;d++){
                        System.out.print(Hang[c][d]+" ");
                    }
                    System.out.println();
                }
            }else if (exchangeMode.get(i%4)==1){
                //获取到要交换的行标
                int need=r.nextInt(3);
                //获取到所在的宫列
                int GongE=r.nextInt(3);
                //获取指定列
                int target1=GongE*3+need;
                int target2=GongE*3+exId.get(need);
                //换列
                int[][] ints = exchangeLie(target1, target2, Hang, Lie);

                Hang[target2]=ints[0];
                Hang[target1]=ints[1];

                System.arraycopy(ints, 2, Hang, 0, 9);

                for (int c=0;c<9;c++){
                    for (int d=0;d<9;d++){
                        System.out.print(Hang[c][d]+" ");
                    }
                    System.out.println();
                }

            }
            else if (exchangeMode.get(i%4)==2) {
                //获取到所在的宫行
                int need=r.nextInt(3);
                int[][] ints = exchangeGongRow(need, exId.get(need), Hang, Lie);

                Hang[need*3+0]=ints[3];
                Hang[need*3+1]=ints[4];
                Hang[need*3+2]=ints[5];

                Hang[exId.get(need)*3+0]=ints[0];
                Hang[exId.get(need)*3+1]=ints[1];
                Hang[exId.get(need)*3+2]=ints[2];

                System.arraycopy(ints, 6, Lie, 0, 9);

                for (int c=0;c<9;c++){
                    for (int d=0;d<9;d++){
                        System.out.print(Hang[c][d]+" ");
                    }
                    System.out.println();
                }
                System.out.println();
            } else if (exchangeMode.get(i%4)==3) {
                //获取到所在的宫列
                int need=r.nextInt(3);
                int[][] ints = exchangeGongColumn(need, exId.get(need), Hang, Lie);

                Lie[need*3+0]=ints[3];
                Lie[need*3+1]=ints[4];
                Lie[need*3+2]=ints[5];

                Lie[exId.get(need)*3+0]=ints[0];
                Lie[exId.get(need)*3+1]=ints[1];
                Lie[exId.get(need)*3+2]=ints[2];

                System.arraycopy(ints, 6, Hang, 0, 9);

                for (int c=0;c<9;c++){
                    for (int d=0;d<9;d++){
                        System.out.print(Hang[c][d]+" ");
                    }
                    System.out.println();
                }
                System.out.println();
            }

        }
        System.out.println("行记录");
        for (int c=0;c<9;c++){
            for (int d=0;d<9;d++){
                System.out.print(Hang[c][d]+" ");
            }
            System.out.println();
        }
        System.out.println();

        //更新列宫
        for (int i = 0; i < Hang.length; i++) {
            for (int j = 0; j < 9; j++) {
                Lie[j][i]=Hang[i][j];
            }
        }

        System.out.println("列记录");
        for (int c=0;c<9;c++){
            for (int d=0;d<9;d++){
                System.out.print(Lie[c][d]+" ");
            }
            System.out.println();
        }
        System.out.println();
        return null;
    }
    //换行方法

    /**
     *
     * @param target1 切换行标1
     * @param target2 切换航标2
     * @param Hang    行源
     * @param Lie     列源
     * @return
     */
    public int[][] exchangeHang(int target1,int target2,int [][]Hang,int [][]Lie){
        System.out.println("开始换行: "+target1+" <=> "+target2);

        int [][]res=new int[11][9];

        res[0]=Hang[target1];
        res[1]=Hang[target2];

        //列更新
        for (int j = 0; j < Lie.length; j++) {
            int temp_1=Lie[j][target1];
            Lie[j][target1]=Lie[j][target2];
            Lie[j][target2]=temp_1;
        }
        for (int i = 0; i < Lie.length; i++) {
            res[i+2]=Lie[i];
        }
        return res;
    }

    /**
     *
     * @param target1 切换列标1
     * @param target2 切换列标2
     * @param Hang    行源
     * @param Lie     列源
     * @return 切换结果
     */
    public int[][] exchangeLie(int target1,int target2,int [][]Hang,int [][]Lie){
        System.out.println("开始换列: "+target1+" <=> "+target2);
        int [][]res=new int[11][9];

        res[0]=Lie[target1];
        res[1]=Lie[target2];

        //列更新
        for (int j = 0; j < Lie.length; j++) {
            int temp_1=Hang[j][target1];
            Hang[j][target1]=Hang[j][target2];
            Hang[j][target2]=temp_1;
        }
        for (int i = 0; i < Hang.length; i++) {
            res[i+2]=Hang[i];
        }

        return res;
    }

    /**
     * 横宫切换
     * @param target1   指定宫行1
     * @param target2   指定宫行2
     * @param Hang      行源
     * @param Lie      列源
     * @return
     */
    public int[][] exchangeGongRow(int target1,int target2,int [][]Hang,int [][]Lie){
        System.out.println("开始换宫行: "+target1+" <=> "+target2);
        int [][]res=new int[15][9];
        //记录行交换
        res[0]=Hang[target1*3+0];
        res[1]=Hang[target1*3+1];
        res[2]=Hang[target1*3+2];

        res[3]=Hang[target2*3+0];
        res[4]=Hang[target2*3+1];
        res[5]=Hang[target2*3+2];
        //行切换达成

        //列更新
        for (int i1 = 0; i1 < 9; i1++) {
            int []temp_3=new int[3];

            temp_3[0]=Lie[i1][target1*3+0];
            temp_3[1]=Lie[i1][target1*3+1];
            temp_3[2]=Lie[i1][target1*3+2];

            Lie[i1][target1*3+0]=Lie[i1][target2*3+0];
            Lie[i1][target1*3+1]=Lie[i1][target2*3+1];
            Lie[i1][target1*3+2]=Lie[i1][target2*3+2];

            Lie[i1][target2*3+0]=temp_3[0];
            Lie[i1][target2*3+1]=temp_3[1];
            Lie[i1][target2*3+2]=temp_3[2];
        }

        for (int i = 0; i < Lie.length; i++) {
            res[i+6]=Lie[i];
        }

        return res;
    }

    /**
     * 纵宫切换
     * @param target1   指定宫列1
     * @param target2   指定宫列2
     * @param Hang      行源
     * @param Lie      列源
     * @return          切换结果
     */
    public int[][] exchangeGongColumn(int target1, int target2, int [][]Hang, int [][]Lie){
        System.out.println("开始换宫列: "+target1+" <=> "+target2);
        int [][]res=new int[15][9];
        //记录列交换
        res[0]=Lie[target1*3+0];
        res[1]=Lie[target1*3+1];
        res[2]=Lie[target1*3+2];

        res[3]=Lie[target2*3+0];
        res[4]=Lie[target2*3+1];
        res[5]=Lie[target2*3+2];
        //列切换达成

        //行更新
        for (int i1 = 0; i1 < 9; i1++) {
            int []temp_3=new int[3];

            temp_3[0]=Hang[i1][target1*3+0];
            temp_3[1]=Hang[i1][target1*3+1];
            temp_3[2]=Hang[i1][target1*3+2];

            Hang[i1][target1*3+0]=Hang[i1][target2*3+0];
            Hang[i1][target1*3+1]=Hang[i1][target2*3+1];
            Hang[i1][target1*3+2]=Hang[i1][target2*3+2];

            Hang[i1][target2*3+0]=temp_3[0];
            Hang[i1][target2*3+1]=temp_3[1];
            Hang[i1][target2*3+2]=temp_3[2];
        }

        for (int i = 0; i < Hang.length; i++) {
            res[i+6]=Hang[i];
        }

        return res;
    }
}
