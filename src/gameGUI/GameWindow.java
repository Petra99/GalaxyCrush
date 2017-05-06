package gameGUI;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameWindow {

	private JButton[][] buttons;
	private JFrame frame;
	private JPanel panelBoard;
	private OnUserAction listener;
	private static JLabel lblTimeHolder;

	public GameWindow(int width, int heigth) {
		getComponentsOfTheWindow();
	}

	public void runWindow() {
		try {
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getComponentsOfTheWindow() {

		prepareFrame();

		preparePanelBoard();

		prepareTitleLabel();

		prepareStartButton();

		prepareScorePanel();

		prepareScoreLabel();

		prepareTimePanel();

		prepareTimeLabel();

		prepareBackground();

	}

	private void prepareBackground() {
		JLabel lblBackgroundHolder = new JLabel("");
		lblBackgroundHolder.setIcon(new ImageIcon("background.gif"));
		lblBackgroundHolder.setBounds(0, 0, 650, 650);
		frame.getContentPane().add(lblBackgroundHolder);

	}

	private void prepareScorePanel() {
		JPanel panelScore = new JPanel();
		panelScore.setBackground(new Color(0, 0, 0));
		panelScore.setBounds(437, 327, 170, 30);
		panelScore.setBackground(new Color(0, 0, 0, 64));
		panelScore.setBorder(BorderFactory.createLineBorder(Color.white));
		frame.getContentPane().add(panelScore);
		panelScore.setLayout(null);

		JLabel lblScoreHolder = new JLabel("0");
		lblScoreHolder.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreHolder.setForeground(new Color(255, 255, 255));
		lblScoreHolder.setFont(new Font("Trajan Pro", Font.PLAIN, 15));
		lblScoreHolder.setBounds(62, 11, 46, 14);
		panelScore.add(lblScoreHolder);
	}

	private void prepareScoreLabel() {
		JLabel lblScore = new JLabel("Score :");
		lblScore.setForeground(Color.LIGHT_GRAY);
		lblScore.setFont(new Font("Trajan Pro", Font.PLAIN, 20));
		lblScore.setBounds(492, 295, 79, 21);
		frame.getContentPane().add(lblScore);
	}

	private void prepareTimePanel() {
		JPanel panelTime = new JPanel();
		panelTime.setBackground(Color.BLACK);
		panelTime.setBounds(437, 249, 170, 35);
		panelTime.setBackground(new Color(0, 0, 0, 64));
		panelTime.setBorder(BorderFactory.createLineBorder(Color.white));
		frame.getContentPane().add(panelTime);
		panelTime.setLayout(null);

		lblTimeHolder = new JLabel("you have 60 seconds");
		lblTimeHolder.setBounds(-5, 7, 180, 21);
		lblTimeHolder.setHorizontalAlignment(SwingConstants.CENTER);
		panelTime.add(lblTimeHolder);
		lblTimeHolder.setBackground(new Color(255, 255, 0));
		lblTimeHolder.setForeground(new Color(255, 255, 255));
		lblTimeHolder.setFont(new Font("Trajan Pro", Font.BOLD, 12));
	}

	private void prepareTimeLabel() {
		JLabel lblTime = new JLabel("Time :");
		lblTime.setForeground(Color.LIGHT_GRAY);
		lblTime.setFont(new Font("Trajan Pro", Font.PLAIN, 20));
		lblTime.setBounds(492, 217, 79, 21);
		frame.getContentPane().add(lblTime);
	}

	private void prepareStartButton() {
		JButton startBtn = new JButton("start");
		startBtn.setBackground(Color.LIGHT_GRAY);
		startBtn.setForeground(Color.BLACK);
		startBtn.setFont(new Font("Trajan Pro", Font.BOLD, 20));
		startBtn.addActionListener(new ActionListener() {

			Timer timer;
			int count = 61;

			public void actionPerformed(ActionEvent arg0) {

				timer = new Timer(1100, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						count--;
						if (count < 0) {
							JOptionPane.showMessageDialog(null, "Time is up !", "Game over", JOptionPane.ERROR_MESSAGE);
							System.exit(0);
						}
						lblTimeHolder.setText(count + "");

					}
				});
				timer.start();
				panelBoard.setVisible(true);
			}
		});
		startBtn.setBounds(159, 555, 101, 29);
		frame.getContentPane().add(startBtn);
	}

	private void prepareTitleLabel() {
		JLabel lblGalaxyCrush = new JLabel("Galaxy Crush");
		lblGalaxyCrush.setForeground(Color.LIGHT_GRAY);
		lblGalaxyCrush.setHorizontalAlignment(SwingConstants.CENTER);
		lblGalaxyCrush.setFont(new Font("Trajan Pro", Font.BOLD, 40));
		lblGalaxyCrush.setBounds(159, 61, 316, 41);
		frame.getContentPane().add(lblGalaxyCrush);
	}

	private void preparePanelBoard() {
		panelBoard = new JPanel();
		panelBoard.setBounds(27, 140, 400, 400);
		frame.getContentPane().add(panelBoard);
		panelBoard.setLayout(new GridLayout(0, 6, 0, 0));
		panelBoard.setBackground(new Color(0, 0, 0, 64));
		panelBoard.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBoard.setVisible(false);
	}

	private void prepareFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	// new

	public void setBoard(GameBoard board) {
		buttons = new JButton[GameBoard.getBoard_height()][GameBoard.getBoard_width()];
		for (int x = 0; x < GameBoard.getBoard_height(); x++) {
			for (int y = 0; y < GameBoard.getBoard_width(); y++) {
				prepareButtonOnBoard(board.getPlanetAt(x, y), x, y);
			}
		}
	}

	public void prepareButtonOnBoard(Figure figure, int x, int y) {
		JButton btn = new JButton();
		btn.setIcon(new ImageIcon(figure.getIconPath()));
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listener.onButtonClicked(x, y);

			}
		});
		buttons[x][y] = btn;
		panelBoard.add(btn);
	}

	// new

	public void showBoard(GameBoard board) {
		for (int x = 0; x < GameBoard.getBoard_height(); x++) {
			for (int y = 0; y < GameBoard.getBoard_width(); y++) {
				buttons[x][y].setIcon(new ImageIcon(board.getPlanetAt(x, y).getIconPath()));
			}
		}
	}

	public void setListener(OnUserAction listener) {
		this.listener = listener;
	}
}