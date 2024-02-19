package com.github.liudi0105;

import com.github.liudi0105.summer.boot.core.annotation.SummerBootApplication;
import com.github.liudi0105.summer.boot.core.bootstrap.SummerApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SummerBootApplication
public class Main {
    public static void main(String[] args) {
        SummerApplication.run(Main.class, args);
    }
}