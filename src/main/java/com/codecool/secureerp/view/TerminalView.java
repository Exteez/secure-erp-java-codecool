package com.codecool.secureerp.view;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class TerminalView {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a single message to the terminal
     *
     * @param message information to be printed
     */
    public static void printMessage(String message) {
        final String BLUE_COLOR = "\u001B[34m";
        final String RESET_COLOR = "\u001B[0m";
        System.out.println(BLUE_COLOR + message + RESET_COLOR);
    }

    /**
     * Prints options in standard menu format like this:
     *      Main Menu:
     *      (1) Store manager
     *      (2) Human resources manager
     *      (3) Inventory manager
     *      (0) Exit program
     *
     * @param title the title of the menu (first row)
     * @param options array of all available options in menu as Strings
     */
    public static void printMenu(String title, String[] options) {
        System.out.println(title + ":");

        for (int i = 0; i < options.length; i++) {
            System.out.println("(" + i + ") " + options[i]);
        }
    }

    /**
     * Prints out any type of non-tabular data
     *
     * @param result String with result to be printed
     * @param label label String
     */
    public static void printGeneralResults(String result, String label) {
    }

    /*
     /--------------------------------\
     |   id   |   product  |   type   |
     |--------|------------|----------|
     |   0    |  Bazooka   | portable |
     |--------|------------|----------|
     |   1    | Sidewinder | missile  |
     \--------------------------------/
    */
    /**
     * Prints tabular data like above example
     *
     * @param table 2 dimensional array to be printed as table
     */
    public static void printTable(String[][] table) {
        System.out.println("/--------------------------------\\");
        System.out.printf("|%s|%s|%s|%n",
                StringUtils.center("id", 7),
                StringUtils.center("product", 12),
                StringUtils.center("type", 11));
        System.out.println("|-------|------------|-----------|");

        for (int i = 0; i < table.length; i++) {
            System.out.printf("|%s|%s|%s|%n",
                    StringUtils.center(String.valueOf(i), 7),
                    StringUtils.center(table[i][0], 12),
                    StringUtils.center(table[i][1], 11));

            if (i == table.length - 1) {
                System.out.println("\\--------------------------------/");
            } else {
                System.out.println("|-------|------------|-----------|");
            }
        }
    }

    /**
     * Gets single String input from the user
     *
     * @param label the label before the user prompt
     * @return user input as String
     */
    public static String getInput(String label) {
        throw new RuntimeException("Not implemented yet");
    }

    /**
     * Gets a list of String inputs from the user
     *
     * @param labels array of Strings with the labels to be displayed before each prompt
     * @return array of user inputs
     */
    public static String[] getInputs(String[] labels) {
        throw new RuntimeException("Not implemented yet");
    }

    /**
     * Prints out error messages to terminal
     *
     * @param message String with error details
     */
    public static void printErrorMessage(String message) {
        System.err.println(message);
    }
}
