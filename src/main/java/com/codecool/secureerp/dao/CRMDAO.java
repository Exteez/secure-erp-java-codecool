package com.codecool.secureerp.dao;

import com.codecool.secureerp.view.TerminalView;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class CRMDAO {
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int EMAIL_TABLE_INDEX = 2;
    private final static int SUBSCRIBED_TABLE_INDEX = 3;
    private final static String DATA_FILE = "src/main/resources/crm.csv";
    public static String[] headers = {"Id", "Name", "Email", "Subscribed"};

    public static void addToCsv(String[] userData, boolean append) throws IOException {
        FileWriter csvWriter = new FileWriter(DATA_FILE, append);
        csvWriter.append("\n");
        csvWriter.append(String.join(";", userData));
        csvWriter.flush();
        csvWriter.close();
    }

    public static String[][] getDataFromCsv() throws IOException {
        String[] dataFromCsv = Files.readAllLines(Paths.get(DATA_FILE), StandardCharsets.UTF_8)
                .toArray(new String[0]);
        String[][] userData = new String[dataFromCsv.length + 1][];
        userData[0] = headers;
        for (int i = 0; i < dataFromCsv.length; i++) {
            userData[i+1] = dataFromCsv[i].split(";");
        }

        return userData;
    }
}
