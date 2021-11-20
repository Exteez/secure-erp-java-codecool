package com.codecool.secureerp.controller;

import com.codecool.secureerp.Util;
import com.codecool.secureerp.dao.CRMDAO;
import com.codecool.secureerp.model.CRMModel;
import com.codecool.secureerp.view.TerminalView;

import java.io.IOException;
import java.util.Arrays;

public class CRMController {
    public static void listCustomers() {
        TerminalView.printErrorMessage("Not implemented yet");
    }

    public static void addCustomer() throws IOException {
        CRMModel crmModel = new CRMModel("", "", "", false);

        String id = Util.generateId();
        crmModel.setId(id);

        String[] userData = TerminalView.getInputs(new String[] {"Name:", "Email:", "Subscribed (true or false):"});
        crmModel.setName(userData[0]);
        crmModel.setEmail(userData[1]);
        crmModel.setSubscribed(Boolean.parseBoolean(userData[2]));

        String[] newUser = crmModel.toTableRow();
        CRMDAO.addToCsv(newUser);
        TerminalView.printMessage("Customer added successfully!");
    }

    public static void updateCustomers() {
        TerminalView.printErrorMessage("Not implemented yet");
    }

    public static void deleteCustomers() {
        TerminalView.printErrorMessage("Not implemented yet");
    }

    public static void getSubscribedEmails() {
        TerminalView.printErrorMessage("Not implemented yet");
    }

    public static void runOperation(int option) throws IOException {
        switch (option) {
            case 1: {
                listCustomers();
                break;
            }
            case 2: {
                addCustomer();
                break;
            }
            case 3: {
                updateCustomers();
                break;
            }
            case 4: {
                deleteCustomers();
                break;
            }
            case 5: {
                getSubscribedEmails();
                break;
            }
            case 0:
                return;
            default:
                throw new IllegalArgumentException("There is no such option.");
        }
    }

    public static void displayMenu() {
        String[] options = {
                "Back to main menu",
                "List customers",
                "Add new customer",
                "Update customer",
                "Remove customer",
                "Subscribed customer emails"
        };
        TerminalView.printMenu("Customer Relationship", options);
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
