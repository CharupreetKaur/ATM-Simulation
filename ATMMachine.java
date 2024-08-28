package com.example.lib;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

public class ATMMachine extends JFrame {
    private BankAccount account;

    private JLabel balanceLabel;
    private JTextField depositField;
    private JTextField withdrawField;

    public ATMMachine(final BankAccount account) {
        this.account = account;
        setTitle("ATM Machine");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        balanceLabel = new JLabel("Your balance is: $" + account.getBalance());
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        depositField = new JTextField(10);
        withdrawField = new JTextField(10);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double depositAmount = Double.parseDouble(depositField.getText());
                    account.deposit(depositAmount);
                    balanceLabel.setText("Your balance is: $" + account.getBalance());
                    depositField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input for deposit. Please enter a valid number.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double withdrawAmount = Double.parseDouble(withdrawField.getText());
                    if (account.withdraw(withdrawAmount)) {
                        balanceLabel.setText("Your balance is: $" + account.getBalance());
                        withdrawField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient funds. Withdrawal failed.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input for withdrawal. Please enter a valid number.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(balanceLabel);
        panel.add(new JLabel("Deposit: $"));
        panel.add(depositField);
        panel.add(depositButton);
        panel.add(new JLabel("Withdraw: $"));
        panel.add(withdrawField);
        panel.add(withdrawButton);

        add(panel);

    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(100.0); // Initial balance of $1000
        ATMMachine atm = new ATMMachine(userAccount);
        atm.setVisible(true);
    }
}
