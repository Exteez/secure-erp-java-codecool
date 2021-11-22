package com.codecool.secureerp.controller;

import com.codecool.secureerp.Util;
import com.codecool.secureerp.dao.HRDAO;
import com.codecool.secureerp.model.HRModel;
import com.codecool.secureerp.view.TerminalView;

import java.io.IOException;
import java.time.LocalDate;

public class HRController {


    public static void listEmployees() throws IOException {
        TerminalView.printTable(HRDAO.getHRDataFromCsv());
    }

    public static void addEmployee() throws IOException {
        listEmployees();
        HRModel hrModel = new HRModel();

        String id = Util.generateId();
        hrModel.setId(id);

        String[] newPerson = TerminalView.getInputs(
                new String[]{"Name:", "Birthdate:", "Department:", "Clearance:"});
        hrModel.setName(newPerson[0]);
        hrModel.setBirthDate(LocalDate.parse(newPerson[1]));
        hrModel.setDepartment(newPerson[2]);
        hrModel.setClearance(Integer.parseInt(newPerson[3]));

        String[] hrData = hrModel.toTableRow();
        HRDAO.addPersonToCsv(hrData, true);
        TerminalView.printMessage("Person added successfully!");
    }

    public static void updateEmployee() throws IOException {
        listEmployees();
        String[][] modifiedHRData = HRDAO.createModifiedHRData(HRDAO.getHRDataFromCsv());

        for (int i = 0; i < modifiedHRData.length; i++) {
            HRDAO.addPersonToCsv(modifiedHRData[i], i != 0);
        }

        TerminalView.printMessage("Person data updated successfully!");
    }

    public static void deleteEmployee() throws IOException {
        listEmployees();
        String[][] deletedHr = HRDAO.deleteHRData(HRDAO.getHRDataFromCsv());

        for (int i = 0; i < deletedHr.length; i++) {
            HRDAO.addPersonToCsv(deletedHr[i], i != 0);
        }
        TerminalView.printMessage("Person data deleted successfully!");
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

    public static void runOperation(int option) throws IOException {
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
