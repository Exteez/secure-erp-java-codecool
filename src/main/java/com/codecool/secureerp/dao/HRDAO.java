package com.codecool.secureerp.dao;

public class HRDAO {
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int BIRTH_DATE_TABLE_INDEX = 2;
    private final static int DEPARTMENT_TABLE_INDEX = 3;
    private final static int CLEARANCE_TABLE_INDEX = 4;
    private final static String DATA_FILE = "src/main/resources/hr.csv";
    public static String[] headers = {"Id", "Name", "Date of birth", "Department", "Clearance"};
}
