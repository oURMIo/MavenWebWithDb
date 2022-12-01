package com.example.demo.web;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHs implements ForFiles{
    private final Path rootLocation;

    public FileHs(Path rootLocation) {
        this.rootLocation = rootLocation;
    }

    @Override
    public void init() {
/*        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }*/
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }
}
