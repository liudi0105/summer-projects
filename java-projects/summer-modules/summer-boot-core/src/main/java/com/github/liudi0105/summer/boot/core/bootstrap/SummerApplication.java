//package com.github.liudi0105.summer.boot.core.bootstrap;
//
//import com.github.liudi0105.summer.core.ioc.utils.PackageScanner;
//
//import java.io.IOException;
//import java.util.List;
//
//public class SummerApplication {
//
//    public static void run(Class<?> clazz, String[] args) {
//        String packageName = clazz.getPackageName();
//        PackageScanner packageScanner = new PackageScanner(packageName);
//        try {
//            List<String> scan = packageScanner.scan();
//            System.out.println();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
