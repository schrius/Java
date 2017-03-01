package edu.cuny.brooklyn.cisc3120;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;

import javax.swing.SpinnerNumberModel;

public class GUIGuessingGame {
    private static int maxInteger = 16;
    private int remain; 
    private int target;
	private JFrame GuessFrame;
	private JSpinner GuessSpinner;
	private JLabel ResultLabel;
	private JLabel TextLabel;
	private JLabel AttempLabel;
	private JButton HintButton;
	private int min, max;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIGuessingGame window = new GUIGuessingGame();
					window.GuessFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUIGuessingGame() {
		setTarget();
		initialize();
	}

	private void initialize() {
		GuessFrame = new JFrame();
		GuessFrame.setTitle("The GUI Guessing Game");
		GuessFrame.setBounds(100, 100, 450, 300);
		GuessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GuessFrame.getContentPane().setLayout(null);
		
		TextLabel = new JLabel("Guess a number between 1 and " + maxInteger);
		TextLabel.setBounds(20, 8, 220, 20);
		GuessFrame.getContentPane().add(TextLabel);
		
	    GuessSpinner = new JSpinner();
		GuessSpinner.setModel(new SpinnerNumberModel(1, 1, maxInteger, 1));
		GuessSpinner.setBounds(260, 8, 50, 20);
		GuessFrame.getContentPane().add(GuessSpinner);
		
		HintButton = new JButton("Hint"); 
		HintButton.setMnemonic(KeyEvent.VK_X);
		HintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hint();
			}
		});
		
		JButton GuessButton = new JButton("Guess");
		GuessButton.setBounds(335, 8, 75, 20);
		GuessButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int guess = (int) GuessSpinner.getValue();
				Guess(guess);
			}
		});
		GuessFrame.getContentPane().add(GuessButton);
		
		ResultLabel = new JLabel(" ");
		ResultLabel.setFont(new Font("Tahoma", Font.BOLD, 52));
		ResultLabel.setBounds(60, 60, 320, 180);
		GuessFrame.getContentPane().add(ResultLabel);
		
		AttempLabel = new JLabel(remain + " Guess remain!");
		AttempLabel.setBounds(180, 40, 160, 20);
		GuessFrame.getContentPane().add(AttempLabel);
		GuessFrame.setLocationRelativeTo(null);
		GuessFrame.setVisible(true);
	}
	void setTarget(){
		Random rand = new Random(); 
		target = rand.nextInt(maxInteger)+1;
		min = 1;
		max = maxInteger;
		remain = 4;
	}
	
	void Guess(int guess){
		if(guess == target)Win();
		else if (guess > target){
			ResultLabel.setText(" Too High!");
			max = guess;
			GuessSpinner.setModel(new SpinnerNumberModel(guess, min, max, 1));
			remain--;
			if(remain==0)Lose();
			else {
				TextLabel.setText("Guess a number between " + min + " and " + max);
				AttempLabel.setText(remain + " Guess remain!");
			}
		}
		else {ResultLabel.setText(" Too Low!");
			min = guess;
			GuessSpinner.setModel(new SpinnerNumberModel(guess, min, max, 1));
			remain--;
			if(remain==0)Lose();
			else {
				TextLabel.setText("Guess a number between " + min + " and " + max);
				AttempLabel.setText(remain + " Guess remain!");
			}
		}
	}
	
	void Win(){
		ResultLabel.setText(" You Win!");
		if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Would you like to try again?", "You Win!", JOptionPane.YES_NO_OPTION))
			Reset();
		else System.exit(1);
	}
	
	void Lose(){
		ResultLabel.setText(" You Lose!");
		if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Would you like to try again?", "You Lose", JOptionPane.YES_NO_OPTION))
			Reset();
		else System.exit(1);
		
	}

	void Reset(){
		setTarget();
		initialize();
	}
	void hint(){
		JOptionPane.showMessageDialog(null, "Target is " + target);
	}
	
}
