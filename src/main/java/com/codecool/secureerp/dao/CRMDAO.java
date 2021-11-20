package com.codecool.secureerp.dao;

import java.io.*;
import java.util.Arrays;

public class CRMDAO {
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int EMAIL_TABLE_INDEX = 2;
    private final static int SUBSCRIBED_TABLE_INDEX = 3;
    private final static String DATA_FILE = "src/main/resources/crm.csv";
    public static String[] headers = {"Id", "Name", "Email", "Subscribed"};

    public static void addToCsv(String[] userData) throws IOException {
        FileWriter csvWriter = new FileWriter(DATA_FILE, true);
        csvWriter.append("\n");
        csvWriter.append(String.join(";", userData));
        csvWriter.flush();
        csvWriter.close();
    }

    public static String[][] getDataFromCsv() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(DATA_FILE));
        int lineCounter = 0;
        while (csvReader.readLine() != null) {
            lineCounter++;
        }
        System.out.println(lineCounter);
        String[][] users = new String[lineCounter][];
        String line;
        int i = 0;
        while (csvReader.readLine() != null) {
            line = csvReader.readLine();
            System.out.println(line);                // issue: line is always null
            String[] user = line.split(";");
            users[i] = user;
            i++;
        }
        System.out.println(Arrays.toString(users));
        csvReader.close();
        return users;
    }
}
