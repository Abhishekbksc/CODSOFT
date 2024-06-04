import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarksCalculatorGUI extends JFrame {
    private JTextField subject1Field;
    private JTextField subject2Field;
    private JTextField subject3Field;
    private JTextField subject4Field;
    private JTextField subject5Field;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;
    private JButton calculateButton;
    private JButton clearButton;
    private JButton closeButton;

    public MarksCalculatorGUI() {
        setTitle("Marks Calculator");
        setSize(1000, 800); // Increased frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Set the background image
        JLabel backgroundLabel = new JLabel(new ImageIcon("calculator_background.jpg"));
        backgroundLabel.setBounds(0, 0, 1000, 800);
        setContentPane(backgroundLabel);
        getContentPane().setLayout(null);

        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font fieldFont = new Font("Arial", Font.PLAIN, 20);

        JLabel studentImageLabel = new JLabel(new ImageIcon("student_image.png"));
        studentImageLabel.setBounds(650, 10, 300, 400);
        add(studentImageLabel);

        JLabel subject1Label = new JLabel("Subject 1:");
        subject1Label.setBounds(50, 50, 200, 40);
        subject1Label.setForeground(Color.BLACK); // Set label to black
        subject1Label.setFont(labelFont);
        add(subject1Label);

        subject1Field = new JTextField();
        subject1Field.setBounds(250, 50, 300, 40);
        subject1Field.setFont(fieldFont);
        add(subject1Field);

        JLabel subject2Label = new JLabel("Subject 2:");
        subject2Label.setBounds(50, 120, 200, 40);
        subject2Label.setForeground(Color.BLACK); // Set label to black
        subject2Label.setFont(labelFont);
        add(subject2Label);

        subject2Field = new JTextField();
        subject2Field.setBounds(250, 120, 300, 40);
        subject2Field.setFont(fieldFont);
        add(subject2Field);

        JLabel subject3Label = new JLabel("Subject 3:");
        subject3Label.setBounds(50, 190, 200, 40);
        subject3Label.setForeground(Color.BLACK); // Set label to black
        subject3Label.setFont(labelFont);
        add(subject3Label);

        subject3Field = new JTextField();
        subject3Field.setBounds(250, 190, 300, 40);
        subject3Field.setFont(fieldFont);
        add(subject3Field);

        JLabel subject4Label = new JLabel("Subject 4:");
        subject4Label.setBounds(50, 260, 200, 40);
        subject4Label.setForeground(Color.BLACK); // Set label to black
        subject4Label.setFont(labelFont);
        add(subject4Label);

        subject4Field = new JTextField();
        subject4Field.setBounds(250, 260, 300, 40);
        subject4Field.setFont(fieldFont);
        add(subject4Field);

        JLabel subject5Label = new JLabel("Subject 5:");
        subject5Label.setBounds(50, 330, 200, 40);
        subject5Label.setForeground(Color.BLACK); // Set label to black
        subject5Label.setFont(labelFont);
        add(subject5Label);

        subject5Field = new JTextField();
        subject5Field.setBounds(250, 330, 300, 40);
        subject5Field.setFont(fieldFont);
        add(subject5Field);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(50, 400, 200, 50);
        calculateButton.setFont(fieldFont);
        add(calculateButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(300, 400, 200, 50);
        clearButton.setFont(fieldFont);
        add(clearButton);
        
        closeButton = new JButton("Close");
        closeButton.setBounds(550, 400, 200, 50);
        closeButton.setFont(fieldFont);
        add(closeButton);

        totalMarksLabel = new JLabel("Total Marks: ");
        totalMarksLabel.setBounds(50, 470, 500, 40);
        totalMarksLabel.setForeground(Color.BLACK); // Set label to black
        totalMarksLabel.setFont(labelFont);
        add(totalMarksLabel);

        averagePercentageLabel = new JLabel("Average Percentage: ");
        averagePercentageLabel.setBounds(50, 540, 500, 40);
        averagePercentageLabel.setForeground(Color.BLACK); // Set label to black
        averagePercentageLabel.setFont(labelFont);
        add(averagePercentageLabel);

        gradeLabel = new JLabel("Grade: ");
        gradeLabel.setBounds(50, 610, 500, 40);
        gradeLabel.setForeground(Color.BLACK); // Set label to black
        gradeLabel.setFont(labelFont);
        add(gradeLabel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResults();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    private void calculateResults() {
        try {
            int subject1 = Integer.parseInt(subject1Field.getText());
            int subject2 = Integer.parseInt(subject2Field.getText());
            int subject3 = Integer.parseInt(subject3Field.getText());
            int subject4 = Integer.parseInt(subject4Field.getText());
            int subject5 = Integer.parseInt(subject5Field.getText());

            int totalMarks = subject1 + subject2 + subject3 + subject4 + subject5;
            double averagePercentage = totalMarks / 5.0;

            totalMarksLabel.setText("Total Marks: " + totalMarks);
            averagePercentageLabel.setText("Average Percentage: " + averagePercentage);

            // Custom grade boundaries
            if (averagePercentage >= 90) {
                gradeLabel.setText("Grade: A+");
            } else if (averagePercentage >= 80) {
                gradeLabel.setText("Grade: A");
            } else if (averagePercentage >= 70) {
                gradeLabel.setText("Grade: B");
            } else if (averagePercentage >= 60) {
                gradeLabel.setText("Grade: C");
            } else {
                gradeLabel.setText("Grade: F");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for all subjects.");
        }
    }

    private void clearFields() {
        subject1Field.setText("");
        subject2Field.setText("");
        subject3Field.setText("");
        subject4Field.setText("");
        subject5Field.setText("");
        totalMarksLabel.setText("Total Marks: ");
        averagePercentageLabel.setText("Average Percentage: ");
        gradeLabel.setText("Grade: ");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MarksCalculatorGUI().setVisible(true);
            }
        });
    }
}
