import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int MAX_ATTEMPTS = 10;
    private int randomNumber;
    private int attempts;
    private int score;
    
    private JLabel messageLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JButton playAgainButton;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(173, 216, 230));
        setLayout(new GridLayout(6, 1));

        messageLabel = new JLabel("Guess a number between 1 and 100:");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(messageLabel);

        guessField = new JTextField();
        guessField.setHorizontalAlignment(SwingConstants.CENTER);
        guessField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(guessField);

        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 18));
        guessButton.addActionListener(new GuessButtonListener());
        add(guessButton);

        attemptsLabel = new JLabel("Attempts left: " + MAX_ATTEMPTS);
        attemptsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        attemptsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(attemptsLabel);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(scoreLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 18));
        playAgainButton.addActionListener(new PlayAgainButtonListener());
        playAgainButton.setVisible(false);
        add(playAgainButton);

        newGame();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void newGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attempts = MAX_ATTEMPTS;
        attemptsLabel.setText("Attempts left: " + attempts);
        messageLabel.setText("Guess a number between 1 and 100:");
        guessField.setText("");
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
        playAgainButton.setVisible(false);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts--;
                if (guess == randomNumber) {
                    messageLabel.setText("Correct! The number was " + randomNumber);
                    score++;
                    endGame();
                } else if (guess < randomNumber) {
                    messageLabel.setText("Too low! Try again.");
                } else {
                    messageLabel.setText("Too high! Try again.");
                }
                attemptsLabel.setText("Attempts left: " + attempts);

                if (attempts <= 0) {
                    messageLabel.setText("You've run out of attempts! The number was " + randomNumber);
                    endGame();
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number.");
            }
        }
    }

    private void endGame() {
        guessField.setEnabled(false);
        guessButton.setEnabled(false);
        playAgainButton.setVisible(true);
        scoreLabel.setText("Score: " + score);
    }

    private class PlayAgainButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            newGame();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGame());
    }
}
