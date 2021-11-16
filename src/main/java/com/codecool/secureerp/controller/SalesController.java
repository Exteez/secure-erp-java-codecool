package com.codecool.secureerp.controller;

import com.codecool.secureerp.Util;
import com.codecool.secureerp.view.TerminalView;

public class SalesController {

    public static void listTransactions() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void addTransaction() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void updateTransactions() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void deleteTransactions() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void getBiggestRevenueTransaction() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void getBiggestRevenueProduct() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void countTransactionsBetween() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void sumTransactionsBetween() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void runOperation(int option) {
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

    public static void menu() {
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
