package com.mazsi;

import Classes.*;

import java.util.Map;
import java.util.Scanner;

public class EmployeeMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Employee> employeeMap =  FileReader.loadEmployeeeFile();




    public static void employeeMenu() {

        printEmployeeMenu();
        boolean back = false;
        while (!back) {

            System.out.println("Válasz...");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    back = true;
                    System.out.println("Főmenü");
                    break;
                case 1:
                    newEmployee(0);
                    break;
                case 2:
                    newEmployee(1);
                    break;
                case 3:
                    newEmployee(2);
                    break;
                case 4:
                    newEmployee(3);
                    break;
                case 5:
                    printEmployees();
                    break;
                case 6:
                    removeEmployee();
                    break;
                case 7:
                    printEmployeeMenu();
                    break;
            }

        }
    }

    private static void printEmployeeMenu() {
        System.out.println("\t0  -  Vissza\n" +
                "\t1  -  Új óradíjas dolgozó\n" +
                "\t2  -  Új jutalékos dolgozó\n" +
                "\t3  -  Új jutalék + napidíjas dolgozó\n" +
                "\t4  -  Új fixen fizetett dolgozó\n" +
                "\t5  -  Dolgozók listája\n" +
                "\t6  -  Dolgozó törlése\n" +
                "\t7  -  Menü kiírása\n");
    }

    private static void newEmployee(int type) {

        String[] employeePiece;
        Employee employee;
        StringBuilder sb = new StringBuilder();

        switch (type) {
            case 0:
                System.out.println("Adja meg az adatokat a következő formában: Vezetéknév,Keresznév,Közzöségi azonosító,Bér,Ledolgozott órák száma");
                employeePiece = getData();
                sb.append(employeePiece[0]).append(' ').append(employeePiece[1]);
                employee = new HourlyEmployee(employeePiece[0],
                    employeePiece[1],
                    employeePiece[2],
                    Double.parseDouble(employeePiece[3]),
                    Double.parseDouble(employeePiece[4]));
                break;
            case 1:
                System.out.println("Adja meg az adatokat a következő formában: Vezetéknév,Keresznév,Közzöségi azonosító,Bruttó eladás,Jutalék mértéke");
                employeePiece = getData();
                employee = new CommissionEmployee(employeePiece[0],
                        employeePiece[1],
                        employeePiece[2],
                        Double.parseDouble(employeePiece[3]),
                        Double.parseDouble(employeePiece[4]));
                break;
            case 2:
                System.out.println("Adja meg az adatokat a következő formában: Vezetéknév,Keresznév,Közzöségi azonosító,Bruttó eladás,Jutalék mértéke,Alap bér");
                employeePiece = getData();
                employee = new BasePlusCommissionEmployee(employeePiece[0],
                        employeePiece[1],
                        employeePiece[2],
                        Double.parseDouble(employeePiece[3]),
                        Double.parseDouble(employeePiece[4]),
                        Double.parseDouble(employeePiece[5]));
                break;
            case 3:
                System.out.println("Adja meg az adatokat a következő formában: Vezetéknév,Keresznév,Közzöségi azonosító,Fizetés");
                employeePiece = getData();
                employee = new SalariedEmployee(employeePiece[0],
                        employeePiece[1],
                        employeePiece[2],
                        Double.parseDouble(employeePiece[3]));
                break;


            default:
                return;
        }
        ;

        employeeMap.put(employee.getName(), employee);
        FileReader.saveFile("employee.dat", employeeMap);
    }

    private static void printEmployees() {
        Map<String, Employee> newMap = FileReader.loadEmployeeeFile();
        for (Employee item : newMap.values()) {
            System.out.println("Teljes név: " + item.getName() +
                    " Fizetése: " + item.getPaymentAmount());
            System.out.println();
        }
    }

    private static String[] getData() {
        String getEmloyeeData = scanner.nextLine();
        String[] employeePiece = getEmloyeeData.split(",");
        return employeePiece;

    }

    private static void removeEmployee() {
        System.out.println("Kérem adja meg a teljes nevét annak akit törölni szeretne:");
        String name = scanner.nextLine();
        employeeMap.remove(name);
        FileReader.saveFile("employee.dat", employeeMap);
    }
}
