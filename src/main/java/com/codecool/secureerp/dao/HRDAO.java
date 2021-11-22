package com.codecool.secureerp.dao;

import com.codecool.secureerp.model.HRModel;
import com.codecool.secureerp.view.TerminalView;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class HRDAO {
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int BIRTH_DATE_TABLE_INDEX = 2;
    private final static int DEPARTMENT_TABLE_INDEX = 3;
    private final static int CLEARANCE_TABLE_INDEX = 4;
    private final static String DATA_FILE = "src/main/resources/hr.csv";
    public static String[] headers = {"Id", "Name", "Date of birth", "Department", "Clearance"};

    public static String[][] createModifiedHRData(String[][] HRDataFromCsv) {
        HRModel hrModel = new HRModel();
        String hrId = TerminalView.getInput("HR ID:");
        String[][] modifiedHRData = new String[HRDataFromCsv.length - 1][];
        for (int i = 0; i < modifiedHRData.length; i++) {
            if (!HRDataFromCsv[i + 1][0].equals(hrId)) {
                modifiedHRData[i] = HRDataFromCsv[i + 1];
            } else {
                String[] hrData = new String[5];
                hrData[0] = hrId;
                String[] updatedHRData = TerminalView.getInputs(
                        new String[]{
                                "Saved name: " + HRDataFromCsv[i + 1][1] + "\nEdit name:",
                                "Saved birth date 'yyyy-MM-dd': " + HRDataFromCsv[i + 1][2] + "\nEdit birth date 'yyyy-MM-dd':",
                                "Saved Department: " + HRDataFromCsv[i + 1][3] + "\nEdit department:",
                                "Saved Clearance:" + HRDataFromCsv[i + 1][4] + "\nEdit Clearance:"});
                System.arraycopy(updatedHRData, 0, hrData, 1, updatedHRData.length);
                hrModel.setId(hrData[ID_TABLE_INDEX]);
                hrModel.setName(hrData[NAME_TABLE_INDEX]);
                hrModel.setBirthDate(LocalDate.parse(hrData[BIRTH_DATE_TABLE_INDEX]));
                hrModel.setDepartment(hrData[DEPARTMENT_TABLE_INDEX]);
                hrModel.setClearance(Integer.parseInt(hrData[CLEARANCE_TABLE_INDEX]));
                modifiedHRData[i] = hrModel.toTableRow();
            }
        }
        return modifiedHRData;
    }

    public static String[][] deleteHRData(String[][] hrDataFromCsv) {
        String hrId = TerminalView.getInput("User ID:");
        String[][] deletedHrData = new String[hrDataFromCsv.length - 1][];
        int nextFreeIndexHR = 0;
        for (int i = 0; i < deletedHrData.length; i++) {
            if (!hrDataFromCsv[i + 1][0].equals(hrId)) {
                deletedHrData[nextFreeIndexHR++] = hrDataFromCsv[i + 1];
            }
        }
        return deletedHrData;
    }

    public static void addPersonToCsv(String[] hrData, boolean append) throws IOException {
        FileWriter csvWriter = new FileWriter(DATA_FILE, append);
        if (hrData != null) {
            if (append) csvWriter.append("\n");
            csvWriter.append(String.join(";", hrData));
        }
        csvWriter.flush();
        csvWriter.close();
    }

    public static String[][] getHRDataFromCsv() throws IOException {
        String[] hrDataFromCsv = Files.readAllLines(Paths.get(DATA_FILE), StandardCharsets.UTF_8)
                .toArray(new String[0]);
        String[][] hrData = new String[hrDataFromCsv.length + 1][];
        hrData[0] = headers;
        for (int i = 0; i < hrDataFromCsv.length; i++) {
            hrData[i + 1] = hrDataFromCsv[i].split(";");
        }
        return hrData;
    }
}
