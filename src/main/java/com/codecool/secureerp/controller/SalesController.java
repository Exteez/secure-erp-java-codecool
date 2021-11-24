package com.codecool.secureerp.controller;

import com.codecool.secureerp.Util;
import com.codecool.secureerp.dao.SalesDAO;
import com.codecool.secureerp.model.SalesModel;
import com.codecool.secureerp.view.TerminalView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SalesController {

    public static void listTransactions() throws IOException {
        TerminalView.printTable(SalesDAO.getTransDataFromCsv());
    }

    public static void addTransaction() throws IOException {
        SalesModel salesModel = new SalesModel();

        String id = Util.generateId();
        salesModel.setId(id);
        String idTransaction = Util.generateId();
        salesModel.setCustomerId(idTransaction);

        String[] newTransactionData = TerminalView.getInputs(
                new String[]{"Product:", "Price:", "Transaction Date 'yyyy-MM-dd':"});
        salesModel.setProduct(newTransactionData[0]);
        salesModel.setPrice(Float.parseFloat(newTransactionData[1]));
        salesModel.setTransactionDate(LocalDate.parse(newTransactionData[2]));

        String[] transactionData = salesModel.toTableRow();
        SalesDAO.addTransToCsv(transactionData, true);
        TerminalView.printMessage("Transaction added successfully!");
    }

    public static void updateTransactions() throws IOException {
        listTransactions();
        String[][] modifiedSalesData = SalesDAO.modifiedData(SalesDAO.getTransDataFromCsv());

        for (int i = 0; i < modifiedSalesData.length; i++) {
            SalesDAO.addTransToCsv(modifiedSalesData[i], i != 0);
        }
        TerminalView.printMessage("Transaction updated successfully!");
    }




    public static void deleteTransactions() throws IOException {
        listTransactions();
        String[][] deletedFromSales = SalesDAO.deleteSalesData(SalesDAO.getTransDataFromCsv());

        for (int i = 0; i < deletedFromSales.length; i++) {
            SalesDAO.addTransToCsv(deletedFromSales[i], i != 0);
        }

        TerminalView.printMessage("Transaction deleted successfully!");
    }

    public static void getBiggestRevenueTransaction() throws IOException {
        List<SalesModel> salesData = SalesDAO.getSalesDataFromCsv();
        SalesModel biggestRevenue = salesData.get(0);

        for (SalesModel model : salesData) {
            if (biggestRevenue.getPrice() < model.getPrice()) {
                biggestRevenue = model;
            }
        }
        TerminalView.printMessage("Biggest Revenue Transaction: " + biggestRevenue);
    }

    public static void getBiggestRevenueProduct() throws IOException {
        List<SalesModel> salesData = SalesDAO.getSalesDataFromCsv();
        Map<String, Float> map = new HashMap<>();

        for (SalesModel salesModel : salesData) {
            if (map.containsKey(salesModel.getProduct())) {
                float sumPrice = map.get(salesModel.getProduct()) + salesModel.getPrice();
                map.put(salesModel.getProduct(), sumPrice);

            } else {
                map.put(salesModel.getProduct(), salesModel.getPrice());
            }
        }

        Map.Entry<String, Float> maxEntry = null;

        for (Map.Entry<String, Float> entry : map.entrySet()) {
            if (maxEntry == null || 0 < entry.getValue().compareTo(maxEntry.getValue())) {
                maxEntry = entry;
            }
        }
        TerminalView.printGeneralResults(maxEntry.getKey(), String.valueOf(maxEntry.getValue()));
    }

    public static void countTransactionsBetween() throws IOException {
        List<SalesModel> salesData = SalesDAO.getSalesDataFromCsv();
        LocalDate before = LocalDate.parse(TerminalView.getInput("Before date 'yyyy-MM-dd': "));
        LocalDate after = LocalDate.parse(TerminalView.getInput("After date 'yyyy-MM-dd': "));
        int count = 0;
        for (SalesModel salesModel : salesData) {
            if (salesModel.getTransactionDate().isAfter(before) && salesModel.getTransactionDate().isBefore(after)) {
                count++;
            }
        }

        TerminalView.printMessage(String.valueOf(count));
    }

    public static void sumTransactionsBetween() throws IOException {
        List<SalesModel> salesData = SalesDAO.getSalesDataFromCsv();
        LocalDate before = LocalDate.parse(TerminalView.getInput("Before date 'yyyy-MM-dd': "));
        LocalDate after = LocalDate.parse(TerminalView.getInput("After date 'yyyy-MM-dd': "));
        float sumPrice = 0.0f;

        for (SalesModel salesModel : salesData) {
            if (salesModel.getTransactionDate().isAfter(before) && salesModel.getTransactionDate().isBefore(after)) {
                sumPrice += salesModel.getPrice();
            }
        }
        TerminalView.printMessage(String.valueOf(sumPrice));
    }

    public static void runOperation(int option) throws IOException {
        switch (option) {
            case 1: {
                listTransactions();
                break;
            }
            case 2: {
                addTransaction();
                break;
            }
            case 3: {
                updateTransactions();
                break;
            }
            case 4: {
                deleteTransactions();
                break;
            }
            case 5: {
                getBiggestRevenueTransaction();
                break;
            }
            case 6: {
                getBiggestRevenueProduct();
                break;
            }
            case 7: {
                countTransactionsBetween();
                break;
            }
            case 8: {
                sumTransactionsBetween();
                break;
            }
            case 0:
                return;
            default:
                throw new IllegalArgumentException("There is no such option");
        }
    }

    public static void displayMenu() {
        String[] options = {
                "Back to main menu",
                "List transactions",
                "Add new transaction",
                "Update transaction",
                "Remove transaction",
                "Get the transaction that made the biggest revenue",
                "Get the product that made the biggest revenue altogether",
                "Count number of transactions between",
                "Sum the price of transactions between"
        };

        TerminalView.printMenu("Sales", options);
    }

    public static void menu() throws IOException {
        int operation = -1;
        while (operation != 0) {
            displayMenu();
            String userInput = TerminalView.getInput("Select an operation");
            if (Util.tryParseInt(userInput)) {
                operation = Integer.parseInt(userInput);
                runOperation(operation);
            } else {
                TerminalView.printErrorMessage("This is not a number");
            }
        }
    }
}
