import java.util.Scanner;

// BankAccount class
class BankAccount {
    private String name;
    private String accountNumber;
    private double balance;
    private String pin;

    public BankAccount(String name, String accountNumber, String pin) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount);
        } else {
            System.out.println("Invalid or insufficient balance for withdrawal!");
        }
    }

    public void transfer(BankAccount receiver, double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            receiver.balance += amount;
            System.out.println("Transferred $" + amount + " to " + receiver.name);
        } else {
            System.out.println("Transfer failed due to insufficient balance!");
        }
    }

    public void checkBalance() {
        System.out.printf("Current balance: $%.2f%n", balance);
    }

    public void changePIN(String newPin) {
        if (newPin.length() == 4) {
            this.pin = newPin;
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("PIN must be 4 digits.");
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }
}

// Main class
public class BankApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create two accounts
        System.out.print("Enter name for first account: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter account number for " + name1 + ": ");
        String accNum1 = scanner.nextLine();
        System.out.print("Set a 4-digit PIN: ");
        String pin1 = scanner.nextLine();
        BankAccount acc1 = new BankAccount(name1, accNum1, pin1);

        System.out.print("\nEnter name for second account: ");
        String name2 = scanner.nextLine();
        System.out.print("Enter account number for " + name2 + ": ");
        String accNum2 = scanner.nextLine();
        System.out.print("Set a 4-digit PIN: ");
        String pin2 = scanner.nextLine();
        BankAccount acc2 = new BankAccount(name2, accNum2, pin2);

        // Menu-driven operations
        while (true) {
            System.out.println("\n--- BANK MENU ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Check Balance");
            System.out.println("5. Change PIN");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                System.out.println("Exiting... Thank you!");
                break;
            }

            System.out.print("Enter your account number: ");
            String enteredAccNum = scanner.nextLine();

            BankAccount current = null;
            BankAccount other = null;
            if (enteredAccNum.equals(acc1.getAccountNumber())) {
                current = acc1;
                other = acc2;
            } else if (enteredAccNum.equals(acc2.getAccountNumber())) {
                current = acc2;
                other = acc1;
            } else {
                System.out.println("Account not found!");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount to deposit: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    current.deposit(amount);
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    current.withdraw(amount);
                }
                case 3 -> {
                    System.out.print("Enter amount to transfer: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    current.transfer(other, amount);
                }
                case 4 -> current.checkBalance();
                case 5 -> {
                    System.out.print("Enter new 4-digit PIN: ");
                    String newPin = scanner.nextLine();
                    current.changePIN(newPin);
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
