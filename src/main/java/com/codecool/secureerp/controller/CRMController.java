package com.codecool.secureerp.controller;

import com.codecool.secureerp.Util;
import com.codecool.secureerp.model.CRMModel;
import com.codecool.secureerp.view.TerminalView;

public class CRMController {
    public static void listCustomers() {
        TerminalView.printErrorMessage("Not implemented yet");
    }

    public static void addCustomer() {
        CRMModel crmModel = new CRMModel("", "", "", false);

        //String id;
        //crmModel.setId(id);

        String name = TerminalView.getInput("Name:");
        crmModel.setName(name);

        String email = TerminalView.getInput("Email:");
        crmModel.setEmail(email);

        boolean subscribed = Boolean.parseBoolean(TerminalView.getInput("Subscribed (true or false):"));
        crmModel.setSubscribed(subscribed);

        String[] newUser = crmModel.toTableRow();
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

    public static void runOperation(int option) {
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
