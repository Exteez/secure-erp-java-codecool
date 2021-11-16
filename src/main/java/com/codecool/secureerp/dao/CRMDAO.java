package com.codecool.secureerp.dao;

public class CRMDAO {
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int EMAIL_TABLE_INDEX = 2;
    private final static int SUBSCRIBED_TABLE_INDEX = 3;
    private final static String DATA_FILE = "src/main/resources/crm.csv";
    public static String[] headers = {"Id", "Name", "Email", "Subscribed"};
}
