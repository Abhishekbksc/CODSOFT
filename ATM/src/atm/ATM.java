package atm;

import atm.BankAccount;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("ATM Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        JPanel panel = new BackgroundPanel("background.jpeg");
        panel.setLayout(null);

        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(250, 10, 500, 150); // Adjust the position and size as needed
        ImageIcon imageIcon = new ImageIcon("logo.png"); // Replace with your image path
        imageLabel.setIcon(imageIcon);
        panel.add(imageLabel);

        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        Font labelFont = new Font("Arial", Font.BOLD, 18);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(400, 200, 200, 50);
        withdrawButton.setFont(buttonFont);
        withdrawButton.setForeground(Color.BLACK);
        panel.add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(400, 300, 200, 50);
        depositButton.setFont(buttonFont);
        depositButton.setForeground(Color.BLACK);
        panel.add(depositButton);

        JButton balanceButton = new JButton("Check Balance");
        balanceButton.setBounds(400, 400, 200, 50);
        balanceButton.setFont(buttonFont);
        balanceButton.setForeground(Color.BLACK);
        panel.add(balanceButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(400, 500, 200, 50);
        cancelButton.setFont(buttonFont);
        cancelButton.setForeground(Color.BLACK);
        panel.add(cancelButton);

        JLabel messageLabel = new JLabel("");
        messageLabel.setBounds(400,560, 3001, 20);
        messageLabel.setFont(labelFont);
        messageLabel.setForeground(Color.BLACK);
        panel.add(messageLabel);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw:");
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (account.withdraw(amount)) {
                        messageLabel.setText("Withdrawling Amount : ₹" + amount);
                    } else {
                        messageLabel.setText("Insufficient funds.");
                    }
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Invalid input.");
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog("Enter amount to deposit:");
                try {
                    double amount = Double.parseDouble(amountStr);
                    account.deposit(amount);
                    messageLabel.setText("Deposited: ₹" + amount);
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Invalid input.");
                }
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText("Current balance: ₹" + account.getBalance());
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Add a label at the bottom for the image
        JLabel bottomImageLabel = new JLabel();
        bottomImageLabel.setBounds(180, 620, 1000, 100); // Full width of the frame, small height
        ImageIcon bottomImageIcon = new ImageIcon("Loan.png"); // Replace with your image path
        bottomImageLabel.setIcon(bottomImageIcon);
        panel.add(bottomImageLabel);

        frame.add(panel);
        frame.setVisible(true);
    }

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String filePath) {
            try {
                backgroundImage = new ImageIcon(filePath).getImage();
            } catch (Exception e) {
                System.out.println("Image not found: " + filePath);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Initial balance set to $1000
        new ATM(account);
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String filePath) {
        try {
            backgroundImage = new ImageIcon(filePath).getImage();
            System.out.println("Background image loaded successfully.");
        } catch (Exception e) {
            System.out.println("Image not found: " + filePath);
            e.printStackTrace(); // Print the exception for further details
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
