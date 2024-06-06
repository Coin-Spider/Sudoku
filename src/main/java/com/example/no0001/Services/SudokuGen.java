package com.example.no0001.Services;

import org.springframework.stereotype.Service;

import javax.annotation.processing.Generated;
import java.util.ArrayList;

@Service
public interface SudokuGen {
    //数独生成算法
    ArrayList<ArrayList<Integer>> SudokuGenerated();
}
