package com.example.jsonexercise.utils;

import java.io.IOException;

public interface FileIOutil {

    String readFileContent(String filePath) throws IOException;

    void write(String content, String filePath) throws IOException;

}
