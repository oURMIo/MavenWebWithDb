package com.example.demo.web;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;

public interface ForFiles {
    void init();
    void deleteAll();
    Resource loadAsResource(String filename);
}
