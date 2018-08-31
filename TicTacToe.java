

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class TicTacToe extends JFrame implements ActionListener {

	public static final int BOARD_SIZE = 3;

	private static enum GameStatus {
		Xwins, Zwins, Incomplete, Tie
	}

	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
	boolean crossTurn = true;

	public TicTacToe() {
		// TODO Auto-generated constructor stub
		super.setSize(800,800);
		super.setBackground(Color.cyan);
		super.setTitle("TicTacToe");
		GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);
		Font font = new Font("Conic Sans", 1, 150);
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				JButton button = new JButton("");
				buttons[i][j] = button;
				button.setFont(font);
				//buttons[i][j].setBackground(Color.black);
				button.addActionListener(this);
				super.add(button);
			}
		}
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clickedButton = (JButton) e.getSource();
		makeMove(clickedButton);
		GameStatus gs = getGameStatus();
		if (gs == GameStatus.Incomplete) {
			return;
		} else {
			declareWinner(gs);
			int input = JOptionPane.showConfirmDialog(this, "Do you want to play again?");
			if (input == JOptionPane.YES_OPTION) {
				for (int i = 0; i < BOARD_SIZE; i++) {
					for (int j = 0; j < BOARD_SIZE; j++) {
						buttons[i][j].setText("");
						//buttons[i][j].setBackground(Color.black);
					}
				}
				crossTurn=true;
			}
			else{
				super.dispose();
			}
		}
	}

	private void makeMove(JButton clickedButton) {
		// TODO Auto-generated method stub
		String buttonText = clickedButton.getText();
		if (buttonText.length() > 0) {
			JOptionPane.showMessageDialog(this, "Invalid Move");
			return;
		}
		if (crossTurn) {
			//clickedButton.setBackground(Color.black);
			//clickedButton.setForeground(Color.GRAY);
			clickedButton.setText("X");
			//clickedButton.setBackground(Color.white);

		} else {
			//clickedButton.setBackground(Color.black);
			//clickedButton.setForeground(Color.white);
			clickedButton.setText("O");
			//clickedButton.setBackground(Color.gray);
		}
		crossTurn = !crossTurn;
	}

	private GameStatus getGameStatus() {
		boolean result = false;
		String s = "";
		for (int i = 0; i < BOARD_SIZE; i++) {
			String str = buttons[i][0].getText();
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (buttons[i][j].getText().compareTo(str) == 0) {
					result = true;
					s = str;
				} else {
					result = false;
					break;
				}
			}
			if (result) {
				if (s == "X") {
					return GameStatus.Xwins;
				} else if (s == "O") {
					return GameStatus.Zwins;
				}
			}
		}

		for (int j = 0; j < BOARD_SIZE; j++) {
			String str = buttons[0][j].getText();
			for (int i = 0; i < BOARD_SIZE; i++) {
				if (buttons[i][j].getText().compareTo(str) == 0) {
					result = true;
					s = str;
				} else {
					result = false;
					break;
				}
			}
			if (result) {
				if (s == "X") {
					return GameStatus.Xwins;
				} else if (s == "O") {
					return GameStatus.Zwins;
				}
			}

		}

		int i = 0;
		int j = 0;
		String str = buttons[0][0].getText();
		while (i < BOARD_SIZE && j < BOARD_SIZE) {
			if (buttons[i][j].getText().compareTo(str) == 0) {
				result = true;
				s = str;
			} else {
				result = false;
				break;
			}
			i++;
			j++;
		}
		if (result)

		{
			if (s == "X") {
				return GameStatus.Xwins;
			} else if (s == "O") {
				return GameStatus.Zwins;
			}
		}
		j = BOARD_SIZE - 1;
		i = 0;
		String str1 = buttons[0][BOARD_SIZE - 1].getText();
		while (i < BOARD_SIZE && j >= 0) {
			if (buttons[i][j].getText().compareTo(str1) == 0) {
				result = true;
				s = str1;
			} else {
				result = false;
				break;
			}
			i++;
			j--;
		}
		if (result) {
			if (s == "X") {
				return GameStatus.Xwins;
			} else if (s == "O") {
				return GameStatus.Zwins;
			}
		}
		for (i = 0; i < BOARD_SIZE; i++) {
			for (j = 0; j < BOARD_SIZE; j++) {
				if (buttons[i][j].getText() == "") {
					return GameStatus.Incomplete;

				}
			}
		}
		return GameStatus.Tie;

	}

	private void declareWinner(GameStatus gs) {
		if (gs == GameStatus.Xwins) {
			JOptionPane.showMessageDialog(this, "X Wins");

		} else if (gs == GameStatus.Zwins) {
			JOptionPane.showMessageDialog(this, "O Wins");
		} else
			JOptionPane.showMessageDialog(this, "Its a tie");
		return;
	}

}
