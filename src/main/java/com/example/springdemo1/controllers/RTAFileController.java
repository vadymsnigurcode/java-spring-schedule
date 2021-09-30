package com.example.springdemo1.controllers;

import com.example.springdemo1.service.FileServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RTAFileController {

    final FileServiceImpl fileService;

    public RTAFileController(FileServiceImpl fileService) {
        this.fileService = fileService;
    }

    //    @RequestMapping(
//            method = RequestMethod.GET,
//            path = "/name")
    @GetMapping("")
    public void getFile() throws Exception {
        fileService.retryMethod();
    }

    @GetMapping("/file")
    public String getFile(@RequestParam String path) throws Exception {
        String fileContent = "";

        fileContent = fileService.readFile(path);

        return fileContent;
    }

    @GetMapping("/name")
    public ResponseEntity<String> getName(
            @RequestParam String path) {
        String fileContent = "";
        if (path.startsWith("a"))
            return new ResponseEntity<>(fileContent, HttpStatus.NOT_FOUND);
        try {
            fileContent = fileService.readFile(path);
        } catch (Exception ex) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(fileContent, HttpStatus.OK);
    }


    //    @RequestMapping(
//            method = RequestMethod.POST,
//            path = "/name")
    @PostMapping("/file{path}")
    public void createName(
            @RequestParam String path,
            @RequestBody String data) {

        fileService.writeFile(path, data);
    }
}
