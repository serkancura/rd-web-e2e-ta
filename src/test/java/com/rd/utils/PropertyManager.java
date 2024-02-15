package com.rd.utils;

import java.io.*;
import java.util.Properties;

public class Property {
    public String getProperty(String property) throws IOException {
        Properties props = new Properties();
        try {
            props.load(new FileReader(new File(ClassLoader.getSystemResource("config.properties").getPath())));

        } catch (IOException ex) {
            // handle error
        }
}
