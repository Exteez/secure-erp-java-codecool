package com.codecool.secureerp.controller;

import com.codecool.secureerp.Util;
import com.codecool.secureerp.view.TerminalView;

public class HRController {


    public static void listEmployees() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void addEmployee() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void updateEmployee() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void deleteEmployee() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void getOldestAndYoungest() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void getAverageAge() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void nextBirthdays() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void countEmployeesWithClearance() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void countEmployeesPerDepartment() {
        TerminalView.printErrorMessage("Not implemented yet.");
    }

    public static void runOperation(int option) {
        switch (option) {
            case 1: {
                listEmployees();
                break;
            }
            case 2: {
                addEmployee();
                break;
            }
            case 3: {
                updateEmployee();
                break;
            }
            case 4: {
                deleteEmployee();
                break;
            }
            case 5: {
                getOldestAndYoungest();
                break;
            }
            case 6: {
                getAverageAge();
                break;
            }
            case 7: {
                nextBirthdays();
                break;
            }
            case 8: {
                countEmployeesWithClearance();
                break;
            }
            case 9: {
                countEmployeesPerDepartment();
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
                "List employees",
                "Add new employee",
                "Update employee",
                "Remove employee",
                "Oldest and youngest employees",
                "Employees average age",
                "Employees with birthdays in the next two weeks",
                "Employees with clearance level",
                "Employee numbers by department"
        };
        TerminalView.printMenu("Human Resources", options);
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
