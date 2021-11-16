package com.codecool.secureerp.dao;

public class SalesDAO {
    private final static int ID_TABLE_INDEX = 0;
    private final static int CUSTOMER_ID_TABLE_INDEX = 1;
    private final static int PRODUCT_TABLE_INDEX = 2;
    private final static int PRICE_TABLE_INDEX = 3;
    private final static int TRANSACTION_DATE_TABLE_INDEX = 4;
    private final static String DATA_FILE = "src/main/resources/sales.csv";
    public static String[] headers = {"Id", "Customer Id", "Product", "Price", "Transaction Date"};
}
