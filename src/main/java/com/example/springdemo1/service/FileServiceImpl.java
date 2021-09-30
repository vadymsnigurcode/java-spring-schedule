package com.example.springdemo1.service;

import com.example.springdemo1.dao.FileDB;
import com.example.springdemo1.service.FileService;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
@EnableScheduling
public class FileServiceImpl implements FileService {

    public FileServiceImpl(FileDB fileDB) {
        this.fileDB = fileDB;
    }

    private FileDB fileDB;

    @Override
    @Retryable(value = Exception.class)
    public String readFile(String path) throws Exception {
        StringBuilder contentBuilder = new StringBuilder();
        System.out.println("try to read file:");
        System.out.println("path="+path);

        try
        {
            Stream<String> stream = Files.lines( Paths.get(path), StandardCharsets.UTF_8);
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException ex)
        {
            //ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }

        return contentBuilder.toString();
    }

    @Override
    public boolean writeFile(String path, String data) {
        System.out.println("try to create file:");
        System.out.println("path="+path);
        System.out.println("data="+data);
        try (FileWriter fw
                     = new FileWriter(new File(path))) {
            fw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Scheduled(fixedRate  = 5000)
    public void executePeriodically() {
        System.out.println("trigger event!");
    }

    @Retryable(value = Exception.class)
    public void retryMethod() throws Exception {
        System.out.println("HelloService.retryMethod");
        throw new Exception();
    }
}
