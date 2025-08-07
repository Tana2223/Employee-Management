import java.util.Scanner;

// Employee class
class Employee {
    private String name;
    private double hourlyRate;

    public Employee(String name, double hourlyRate) {
        this.name = name;
        this.hourlyRate = hourlyRate;
    }

    public double calculateAnnualSalary() {
        return hourlyRate * 40 * 50; // 40 hours/week, 50 weeks/year
    }

    public String getName() {
        return name;
    }
}

// Main class
public class Task_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many employees? ");
        int numEmployees = Integer.parseInt(scanner.nextLine());

        Employee[] employees = new Employee[numEmployees];

        for (int i = 0; i < numEmployees; i++) {
            System.out.print("Please enter name of employee #" + (i + 1) + ": ");
            String name = scanner.nextLine();

            System.out.print("Please enter hourly rate of " + name + ": ");
            double hourlyRate = Double.parseDouble(scanner.nextLine());

            employees[i] = new Employee(name, hourlyRate);
        }

        System.out.println();

        for (Employee emp : employees) {
            System.out.printf("%s’s annual salary is $%.2f%n", emp.getName(), emp.calculateAnnualSalary());
        }

        scanner.close();
    }
}
