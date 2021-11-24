package com.codecool.secureerp.controller;

import com.codecool.secureerp.Util;
import com.codecool.secureerp.dao.HRDAO;
import com.codecool.secureerp.model.HRModel;
import com.codecool.secureerp.view.TerminalView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

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


        ArrayList<HRModel> lista = new ArrayList<>();
        String[][] nyomtatas = {};
        try {
            nyomtatas = HRDAO.getHRDataFromCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String[] row : nyomtatas) {
            // System.out.println(row[2]);
            if (!"Date of birth".equals(row[2])) {
                HRModel model = new HRModel(row[0], row[1], LocalDate.parse(row[2]), row[3], Integer.parseInt(row[4]));
                lista.add(model);
            }
        }
        //System.out.println(lista);
        LocalDate oldest = LocalDate.MAX;
        String oldestName = "";
        for (HRModel model : lista) {
            if (model.getBirthDate().isBefore(oldest)) {
                oldest = model.getBirthDate();
                oldestName = model.getName();
            }
        }
        TerminalView.printGeneralResults(oldestName, "The oldest employee is: ");


        LocalDate youngest = LocalDate.MIN;
        String youngestName = "";
        for (HRModel model : lista) {
            if (model.getBirthDate().isAfter(youngest)) {
                youngest = model.getBirthDate();
                youngestName = model.getName();
            }
        }
        TerminalView.printGeneralResults(youngestName, "The youngest employee is: ");



    }


    public static void getAverageAge() {


    }



    /*avg
    * int sum = 0;
        int [] nums = {1,4, 7 ,8, 25, 53, 43};
        for (int i = 0; i < nums.length ; i++) {
        sum += nums[i];
        }
        int avg = sum / nums.length;
        System.out.println(avg);
    *3
    *
    *
    *
    * */


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
