package com.codecool.secureerp.dao;

import com.codecool.secureerp.model.SalesModel;
import com.codecool.secureerp.view.TerminalView;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO {
    private final static int ID_TABLE_INDEX = 0;
    private final static int CUSTOMER_ID_TABLE_INDEX = 1;
    private final static int PRODUCT_TABLE_INDEX = 2;
    private final static int PRICE_TABLE_INDEX = 3;
    private final static int TRANSACTION_DATE_TABLE_INDEX = 4;
    private final static String DATA_FILE = "src/main/resources/sales.csv";
    public static String[] headers = {"Id", "Customer Id", "Product", "Price", "Transaction Date"};


    public static String[][] modifiedData(String[][] salesDataFromCsv) {
        SalesModel salesModel = new SalesModel();
        String customerId = TerminalView.getInput("Customer ID:");
        String[][] modifiedSalesData = new String[salesDataFromCsv.length - 1][];
        for (int i = 0; i < modifiedSalesData.length; i++) {
            if (!salesDataFromCsv[i + 1][1].equals(customerId)) {
                modifiedSalesData[i] = salesDataFromCsv[i + 1];
            } else {
                String[] salesData = new String[5];
                salesData[0] = salesDataFromCsv[i + 1][0];
                salesData[1] = salesDataFromCsv[i + 1][1];
                String[] updatedSaleData = TerminalView.getInputs(
                        new String[]{
                                "Saved product: " + salesDataFromCsv[i + 1][2] + "\nEdit product:",
                                "Saved price: " + salesDataFromCsv[i + 1][3] + "\nEdit price:",
                                "Saved Transaction Date 'yyyy-MM-dd': " + salesDataFromCsv[i + 1][4] + "\nEdit Transaction Date 'yyyy-MM-dd':"});
                System.arraycopy(updatedSaleData, 0, salesData, 2, updatedSaleData.length);
                salesModel.setId(salesData[ID_TABLE_INDEX]);
                salesModel.setCustomerId(salesData[CUSTOMER_ID_TABLE_INDEX]);
                salesModel.setProduct(salesData[PRODUCT_TABLE_INDEX]);
                salesModel.setPrice(Float.parseFloat(salesData[PRICE_TABLE_INDEX]));
                salesModel.setTransactionDate(LocalDate.parse(salesData[TRANSACTION_DATE_TABLE_INDEX]));
                modifiedSalesData[i] = salesModel.toTableRow();
            }
        }
        return modifiedSalesData;
    }

    public static String[][] deleteSalesData(String[][] salesDataFromCsv) {
        String salesId = TerminalView.getInput("Customer ID:");
        String[][] deletedFromSales = new String[salesDataFromCsv.length - 1][];
        int nextFreeIndex = 0;
        for (int i = 0; i < deletedFromSales.length; i++) {
            if (!salesDataFromCsv[i + 1][1].equals(salesId)) {
                deletedFromSales[nextFreeIndex++] = salesDataFromCsv[i + 1];
            }
        }
        return deletedFromSales;
    }

    public static void addTransToCsv(String[] salesDate, boolean append) throws IOException {
        FileWriter csvWriter = new FileWriter(DATA_FILE, append);
        if (salesDate != null) {
            if (append) csvWriter.append("\n");
            csvWriter.append(String.join(";", salesDate));
        }
        csvWriter.flush();
        csvWriter.close();
    }

    public static String[][] getTransDataFromCsv() throws IOException {
        String[] dataFromCsv = Files.readAllLines(Paths.get(DATA_FILE), StandardCharsets.UTF_8)
                .toArray(new String[0]);
        String[][] salesData = new String[dataFromCsv.length + 1][];
        salesData[0] = headers;
        for (int i = 0; i < dataFromCsv.length; i++) {
            salesData[i + 1] = dataFromCsv[i].split(";");
        }
        return salesData;
    }

    public static List<SalesModel> getSalesDataFromCsv() throws IOException {
        List<SalesModel> salesDataFromCsv = new ArrayList<>();
        FileReader fileReader = new FileReader(DATA_FILE);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] temp = line.split(";");
            String id = temp[0];
            String customerId = temp[1];
            String product = temp[2];
            float price = Float.parseFloat(temp[3]);
            LocalDate transactionDate = LocalDate.parse(temp[4]);
            salesDataFromCsv.add(new SalesModel(id, customerId, product, price, transactionDate));
        }
        bufferedReader.close();
        return salesDataFromCsv;
    }
}
