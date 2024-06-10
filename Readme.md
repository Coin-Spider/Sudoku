### 基于 spring-boot3 和 vue3 的在线数独游戏

#### 开发任务

- [x] 生成完整数独列表
- [x] 数独挖空并进行验证唯一解
- [ ] 数据库建立
- [ ] 前端页面编写
- [ ] 服务器上线
- [ ] ...
#### 胡扯区  
~~(😕 英语不好,方法名字误骂,骂我你就骂了我了)~~
## 功能解释区域

#### 一.完整数独生成算法


1. 通过随机生成第一个宫格

```java
ArrayList<Integer> seed = getSeed();
        for (int i = 0; i < 9; i++) {
            Gong[0][i] = seed.get(i);
            Row_T[i / 3][i % 3] = seed.get(i);
            Column_T[i % 3][i / 3] = seed.get(i);
        }
```

> 我将第一个宫格称为种子

2. 然后在将种子进行有序排列生成其他宫格

```java
fillEmpty();
    private void fillEmpty() {
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
```

> 具体做法是
> 如第一行的宫格 1->2 2->3 3->1
> 数字为当前行数,这样进行来回排列便可以生成一个完整的数独

3. 进行错位来生成一个合法数独
   通过第二步生成的数独虽然是完整的,但数据排列整齐,并不能算是正常的数独,
   所以仍然需要进行二次加工

```java
Exchange(exchangeMode);
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
```

和sudoku类里面的

```java
exchangeHang(int target1, int target2);
    exchangeLie(int target1, int target2);
    exchangeGongRow(int target1, int target2);
    exchangeGongColumn(int target1, int target2);
```

均为行列宫切换方法
在进行切换后,数独便变成了一个不那么~~垃圾~~的数独

#### 二.数独挖空和保证唯一解

进行随机挖空并在挖空后调用求解程序进行判断是否有唯一解,如果没有,则进行递归进行多次挖空

```java
com.example.no0001.Services.Impl.SudokuGenImpl.KillNum  //挖空方法
com.example.no0001.Services.Impl.SudokuGenImpl.GetSolver //求解方法
```

