import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GuessTheNumberGUI {

    // Game state variables
    private static int numberToGuess;
    private static int attempts;
    private static JTextField guessField;
    private static JLabel feedbackLabel;
    private static JLabel attemptsLabel;
    private static JButton restartButton;

    public static void main(String[] args) {
        
        // Create JFrame with a stylish design and custom layout
        JFrame frame = new JFrame("Guess the Number Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);  // Center the window
        frame.setLayout(new GridBagLayout());  // Use GridBagLayout for responsive UI

        // Define a background color for the JFrame
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        // Create a new random number between 1 and 100
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;

        // Set a custom font for a polished look
        Font font = new Font("Helvetica", Font.BOLD, 16);

        // Define UI components
        JLabel instructionLabel = new JLabel("I have selected a number between 1 and 100. Try to guess it!");
        instructionLabel.setFont(font);
        instructionLabel.setForeground(new Color(33, 33, 33));  // Dark color for readability
        
        guessField = new JTextField(10);
        guessField.setFont(font);
        guessField.setHorizontalAlignment(JTextField.CENTER);
        guessField.setBackground(Color.WHITE);
        guessField.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2));

        JButton guessButton = new JButton("Guess");
        guessButton.setFont(font);
        guessButton.setBackground(new Color(255, 69, 0));  // A brighter, contrasting button color (Red-Orange)
        guessButton.setForeground(Color.WHITE);  // White text for high contrast
        guessButton.setFocusPainted(false);  // Remove focus border when clicked

        feedbackLabel = new JLabel("Enter your guess above.");
        feedbackLabel.setFont(font);
        feedbackLabel.setForeground(Color.BLACK);  // Default text color

        attemptsLabel = new JLabel("Attempts: 0");
        attemptsLabel.setFont(font);

        restartButton = new JButton("Restart Game");
        restartButton.setFont(font);
        restartButton.setBackground(new Color(34, 139, 34));  // Green button color
        restartButton.setForeground(Color.WHITE);
        restartButton.setEnabled(false);  // Disable initially

        // Use GridBagLayout to arrange components responsively
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Add space between components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(instructionLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        frame.add(guessField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(guessButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(attemptsLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(feedbackLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        frame.add(restartButton, gbc);

        // Action listener for the Guess button
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        // Action listener for the Restart button
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        // Show the window
        frame.setVisible(true);
    }
    
    // Handle the user's guess
    private static void handleGuess() {
        try {
            // Read the user's guess
            int userGuess = Integer.parseInt(guessField.getText());
            
            // Increment the attempts count
            attempts++;

            // Provide feedback based on the user's guess
            if (userGuess < numberToGuess) {
                feedbackLabel.setText("Too low! Try again.");
                feedbackLabel.setForeground(Color.RED);  // Red for incorrect guess
            } else if (userGuess > numberToGuess) {
                feedbackLabel.setText("Too high! Try again.");
                feedbackLabel.setForeground(Color.RED);  // Red for incorrect guess
            } else {
                feedbackLabel.setText("Congrats!You gotcha!");
                feedbackLabel.setForeground(new Color(34, 139, 34));  // Green for correct guess
                JOptionPane.showMessageDialog(null, "You guessed the number in " + attempts + " attempts!");
                restartButton.setEnabled(true);  // Enable the restart button
                
                // Set the Restart button text color to black once it's enabled
                restartButton.setForeground(Color.BLACK);
            }

            // Update the attempts label
            attemptsLabel.setText("Attempts: " + attempts);
            
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Please enter a valid number!");
            feedbackLabel.setForeground(Color.BLACK);  // Default color for error message
        }
    }
    
    // Reset the game after a win
    private static void resetGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;  // Generate new random number
        attempts = 0;  // Reset attempts

        // Clear the guess field and reset labels
        guessField.setText("");
        attemptsLabel.setText("Attempts: 0");
        feedbackLabel.setText("Enter your guess above.");
        feedbackLabel.setForeground(Color.BLACK);  // Default color

        // Disable the restart button after resetting the game
        restartButton.setEnabled(false);
        
        // Reset the button text color to white after resetting
        restartButton.setForeground(Color.WHITE);
    }
}
