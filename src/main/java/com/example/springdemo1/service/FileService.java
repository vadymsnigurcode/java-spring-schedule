package com.example.springdemo1.service;

public interface FileService {
    String readFile(String path) throws Exception;
    boolean writeFile(String path, String data);

    void executePeriodically();
}
