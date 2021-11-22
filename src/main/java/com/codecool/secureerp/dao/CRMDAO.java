package com.codecool.secureerp.dao;

import com.codecool.secureerp.model.CRMModel;
import com.codecool.secureerp.view.TerminalView;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CRMDAO {
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int EMAIL_TABLE_INDEX = 2;
    private final static int SUBSCRIBED_TABLE_INDEX = 3;
    private final static String DATA_FILE = "src/main/resources/crm.csv";
    public static String[] headers = {"Id", "Name", "Email", "Subscribed"};

    public static String[][] createModifiedData(String[][] userDataFromCsv) {
        CRMModel crmModel = new CRMModel("", "", "", false);
        String userId = TerminalView.getInput("User ID:");
        String[][] userDataAfterModification = new String[userDataFromCsv.length - 1][];
        for (int i = 0; i < userDataAfterModification.length; i++) {
            if (!userDataFromCsv[i+1][0].equals(userId)) {
                userDataAfterModification[i] = userDataFromCsv[i+1];
            } else {
                String[] userData = new String[4];
                userData[0] = userId;
                String[] updatedUserData = TerminalView.getInputs(
                        new String[] {
                                "Saved name: " + userDataFromCsv[i+1][1] + "\nEdit name:",
                                "Saved email: " + userDataFromCsv[i+1][2] + "\nEdit email:",
                                "Subscribed: " + userDataFromCsv[i+1][3].equals("1") + "\nEdit:"});
                System.arraycopy(updatedUserData, 0, userData, 1, updatedUserData.length);
                crmModel.setId(userData[ID_TABLE_INDEX]);
                crmModel.setName(userData[NAME_TABLE_INDEX]);
                crmModel.setEmail(userData[EMAIL_TABLE_INDEX]);
                crmModel.setSubscribed(Boolean.parseBoolean(userData[SUBSCRIBED_TABLE_INDEX]));
                userDataAfterModification[i] = crmModel.toTableRow();
            }
        }
        return userDataAfterModification;
    }

    public static String[][] deleteData(String[][] userDataFromCsv) {
        String userId = TerminalView.getInput("User ID:");
        String[][] userDataAfterDeletion = new String[userDataFromCsv.length - 1][];
        int nextFreeIndexInUserDataAfterDeletion = 0;
        for (int i = 0; i < userDataAfterDeletion.length; i++) {
            if (!userDataFromCsv[i+1][0].equals(userId)) {
                userDataAfterDeletion[nextFreeIndexInUserDataAfterDeletion++] = userDataFromCsv[i + 1];
            }
        }
        return userDataAfterDeletion;
    }

    public static void addToCsv(String[] userData, boolean append) throws IOException {
        FileWriter csvWriter = new FileWriter(DATA_FILE, append);
        if (userData != null){
            if (append) csvWriter.append("\n");
            csvWriter.append(String.join(";", userData));
        }
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