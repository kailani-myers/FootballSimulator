package FootballSimulator;
import java.util.Random;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;


public class FootballSimulator {

	public static void main(String[] args) {
		
		//this code is setting up the basic frames and data structures, etc. needed to run the program
		LinkedList<Integer> homePoints = new LinkedList<>();
		LinkedList<Integer> awayPoints = new LinkedList<>();
		
        Random rand = new Random();
		
		JFrame frame = new JFrame("Soccer Simulator");

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 

		frame.add(panel);
	
		
		//frame.setLayout(new GridLayout(7,2,10,10));

		frame.setSize(500, 300);
		JLabel display = new JLabel("Welcome to Soccer Simulator!");
		panel.add(display);
	
		//this sets up the button that allows the user to modify the names of the teams playing
		JButton button = new JButton("Click to modify team names");
		panel.add(button);
		JLabel homeName = new JLabel("Home Team: ");
		JLabel awayName = new JLabel("Away Team: ");
		panel.add(homeName);
		panel.add(awayName);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String HT = JOptionPane.showInputDialog(frame, "Enter home team name here:");
				String AT = JOptionPane.showInputDialog(frame, "Enter away team name here:");
				
				homeName.setText("Home Team: " + HT);
				awayName.setText("Away Team: " + AT);

				button.setEnabled(false);
				frame.repaint();
				frame.revalidate();
			}
		});
		
		//this sets up the button to start the simulation
		
		JButton start = new JButton("Start Simulation");
		panel.add(start);
		
		//this sets up some labels in the GUI
		JLabel firsthalf = new JLabel("First Half: ");
		JLabel secondhalf = new JLabel("Second Half: ");
		panel.add(firsthalf);
		panel.add(Box.createRigidArea(new Dimension(20,0)));
		panel.add(secondhalf);
		
		//this handles the button that starts the simulation
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//this gets the random scores for the match
				for (int i = 0; i<=1; i++) {
					 int score1 = rand.nextInt(4);
					 int score2 = rand.nextInt(4);
					 
					 homePoints.add(score1);
					 awayPoints.add(score2);
				}
			
				firsthalf.setText("First Half: " + "Home Team- " + homePoints.get(0)  + "| Away Team- " + awayPoints.get(0)); 
				secondhalf.setText("Second Half: " + "Home Team- " + homePoints.get(1)  + "| Away Team- " + awayPoints.get(1));
				
				int[] totals = total(homePoints, awayPoints, panel);
				checkWinner(totals, panel);
				
				start.setEnabled(false);
				
				frame.repaint();
				frame.revalidate();
			}
		});
	
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	//this method finds the total score for each team
	public static int[] total(LinkedList<Integer> homePoints, LinkedList<Integer> awayPoints, JPanel panel) {
		int amt1 = 0;
		int amt2 = 0;
		for (int item: homePoints) {
			amt1 = amt1 + item;
		}
		for (int item: awayPoints) {
			amt2 = amt2 + item;
		}
		JLabel total1 = new JLabel("Total Home Team: " + amt1);
		JLabel total2 = new JLabel("Total Away Team: " + amt2);
		
		panel.add(total1);
		panel.add(total2);
		int[] returnValues = {amt1, amt2};
		
		return returnValues;
	}
	
	
	//this method checks the values of the total scores and determines the winner
	public static void checkWinner(int[] totals, JPanel panel) {
		JLabel winner = new JLabel();
		JLabel tie = new JLabel();
		
		if (totals[0] > totals[1]) {
			winner.setText("The home team has won the game!");
		}else if (totals[0] < totals[1]) {
			winner.setText("The away team has won the game!");

		}else {
			tie.setText("The game ended in a tie! Commencing penalty kicks.");
			panel.add(tie);
			tiebreaker(panel);
			
		}
		panel.add(winner);
	}
	
	
	//this code commences in case there is a tie in the previous match
	public static void tiebreaker(JPanel panel) {
		Random rand = new Random();
		JLabel trueWinner = new JLabel();
		JLabel score = new JLabel();
		
		while (true){
			int score1 = rand.nextInt(5);
			int score2 = rand.nextInt(5);

			if (score1 != score2) {
				if (score1 > score2) {
					score.setText("The penalty scores are "+ score1  + "| Away Team- " + score2);
					panel.add(score);
					trueWinner.setText("The home team has won the game!");
					panel.add(trueWinner);

				}else {
					score.setText("The penalty scores are "+ "Home Team-" + score1  + "| Away Team- " + score2);
					trueWinner.setText("The away team has won the game!");
					panel.add(score);
					panel.add(trueWinner);

				}
			break;
			}
		}
	}
	
}
