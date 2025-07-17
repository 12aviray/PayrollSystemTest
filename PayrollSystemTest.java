// File: PayrollSystemTest.java
import java.util.InputMismatchException;
import java.util.Scanner;

public class PayrollSystemTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many employees would you like to process? ");
        int numberOfEmployees = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Employee[] employees = new Employee[numberOfEmployees];

        // 1. Create employee objects based on user input
        for (int i = 0; i < numberOfEmployees; i++) {
            System.out.println("\n--- Entering data for Employee #" + (i + 1) + " ---");
            System.out.println("Select Employee Type:");
            System.out.println("1. Salaried Employee");
            System.out.println("2. Hourly Employee");
            System.out.println("3. Commission Employee");
            System.out.println("4. Base Plus Commission Employee");
            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter Social Security Number: ");
            String ssn = scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Weekly Salary: ");
                        double weeklySalary = scanner.nextDouble();
                        employees[i] = new SalariedEmployee(firstName, lastName, ssn, weeklySalary);
                        break;
                    case 2:
                        System.out.print("Enter Hourly Wage: ");
                        double wage = scanner.nextDouble();
                        System.out.print("Enter Hours Worked: ");
                        double hours = scanner.nextDouble();
                        employees[i] = new HourlyEmployee(firstName, lastName, ssn, wage, hours);
                        break;
                    case 3:
                        System.out.print("Enter Gross Sales: ");
                        double sales = scanner.nextDouble();
                        System.out.print("Enter Commission Rate (e.g., 0.1 for 10%): ");
                        double rate = scanner.nextDouble();
                        employees[i] = new CommissionEmployee(firstName, lastName, ssn, sales, rate);
                        break;
                    case 4:
                        System.out.print("Enter Gross Sales: ");
                        double grossSales = scanner.nextDouble();
                        System.out.print("Enter Commission Rate (e.g., 0.1 for 10%): ");
                        double commRate = scanner.nextDouble();
                        System.out.print("Enter Base Salary: ");
                        double baseSalary = scanner.nextDouble();
                        employees[i] = new BasePlusCommissionEmployee(firstName, lastName, ssn, grossSales, commRate, baseSalary);
                        break;
                    default:
                        System.out.println("Invalid choice. Skipping this employee.");
                        break;
                }
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Invalid input: " + e.getMessage() + ". Skipping this employee.");
            } finally {
                scanner.nextLine(); // Clear buffer for next loop iteration
            }
        }

        System.out.println("\n--- Payroll Processing ---\n");

        // 2. Increase base salary for BasePlusCommissionEmployee objects by 10%
        System.out.println("Rewarding Base-Salaried Commission Employees with a 10% salary increase...");
        for (Employee emp : employees) {
            if (emp instanceof BasePlusCommissionEmployee) {
                // Cast the Employee reference to a BasePlusCommissionEmployee reference
                BasePlusCommissionEmployee bpce = (BasePlusCommissionEmployee) emp;
                double oldSalary = bpce.getBaseSalary();
                bpce.setBaseSalary(oldSalary * 1.10); // Increase salary by 10%
                System.out.printf("Salary for %s %s updated from $%,.2f to $%,.2f\n",
                        bpce.getFirstName(), bpce.getLastName(), oldSalary, bpce.getBaseSalary());
            }
        }

        // 3. Iterate and display employee information and earnings polymorphically
        System.out.println("\n--- Employee Payroll Details ---\n");
        for (Employee currentEmployee : employees) {
            if (currentEmployee != null) {
                // a. Polymorphic call to toString()
                System.out.println(currentEmployee);

                // b. Polymorphic call to earnings()
                System.out.printf("Weekly Earnings: $%,.2f\n", currentEmployee.earnings());
                System.out.println("----------------------------------------");
            }
        }

        scanner.close();
    }
}