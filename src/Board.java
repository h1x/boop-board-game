import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board implements ActionListener {
	final int rows = 6;
	final int cols = 6;

	JFrame frame = new JFrame();
	
	JPanel title_panel = new JPanel();
	JLabel textfield = new JLabel();

	JPanel controlPanel = new JPanel();
	
	JPanel infoPanel = new JPanel();
	JLabel infoLabel = new JLabel();
	
	JButton kittenButton = new JButton();
	JButton catButton = new JButton();
	JLabel catLabel = new JLabel();
    JLabel kittenLabel = new JLabel();
    JPanel catPanel = new JPanel();
    JPanel kittenPanel = new JPanel();
	
	JPanel button_panel = new JPanel();
	JButton[][] buttons = new JButton[rows][cols];
	
	int turn = 1;
	int[] cats = {0, 0};
	int[] kittens = {8, 8};
	int maxAvailablePieces = 8;
	boolean placeAKitten = true;
	
	
	Board() {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 800);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		
		
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setVerticalAlignment(JLabel.TOP);
		textfield.setText("Boop");
		textfield.setOpaque(true);
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
		
		title_panel.add(textfield);

		
		
		infoLabel.setBackground(new Color(25,25,25));
		infoLabel.setForeground(new Color(25,255,0));
		infoLabel.setFont(new Font("Ink Free", Font.BOLD, 75));
		infoLabel.setHorizontalAlignment(JLabel.CENTER);
		infoLabel.setVerticalAlignment(JLabel.TOP);
		infoLabel.setText("Player 1's Turn");
		infoLabel.setOpaque(true);
		
		infoPanel.setLayout(new BorderLayout());
		infoPanel.setBackground(new Color(25,25,25));

		infoPanel.add(infoLabel, BorderLayout.NORTH);
		
		catLabel.setBackground(new Color(25,25,25));
		catLabel.setForeground(new Color(25,255,0));
		catLabel.setFont(new Font("Ink Free", Font.BOLD, 75));
		catLabel.setHorizontalAlignment(JLabel.CENTER);
		catLabel.setVerticalAlignment(JLabel.TOP);
		catLabel.setText("Cats: " + cats[0]);
		catLabel.setOpaque(true);
		
		kittenLabel.setBackground(new Color(25,25,25));
		kittenLabel.setForeground(new Color(25,255,0));
		kittenLabel.setFont(new Font("Ink Free", Font.BOLD, 75));
		kittenLabel.setHorizontalAlignment(JLabel.CENTER);
		kittenLabel.setVerticalAlignment(JLabel.TOP);
		kittenLabel.setText("Kittens: " + kittens[0]);
		kittenLabel.setOpaque(true);
		
		catPanel.setLayout(new BorderLayout());
		catPanel.add(catLabel, BorderLayout.NORTH);
		catPanel.add(catButton, BorderLayout.CENTER);
		kittenPanel.setLayout(new BorderLayout());
		kittenPanel.add(kittenLabel, BorderLayout.NORTH);
		kittenPanel.add(kittenButton, BorderLayout.CENTER);
		
		catButton.setIcon(new ImageIcon("./resources/board-tile.png"));
		catButton.setName("cat");
		catButton.setFocusable(false);
		catButton.addActionListener(this);
		catButton.setContentAreaFilled(false);
		catButton.setBorder(BorderFactory.createEmptyBorder());
		catButton.setOpaque(true);
		catButton.setBackground(new Color(25,25,25));


		kittenButton.setIcon(new ImageIcon("./resources/board-tile.png"));
		kittenButton.setName("kitten");
		kittenButton.setFocusable(false);
		kittenButton.addActionListener(this);
		kittenButton.setContentAreaFilled(false);
		kittenButton.setBorder(BorderFactory.createEmptyBorder());
		kittenButton.setOpaque(true);
		kittenButton.setBackground(new Color(50, 168, 82));


		
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.setBounds(800,100,200,700);
		controlPanel.add(infoPanel);
		controlPanel.add(catPanel);
		controlPanel.add(kittenPanel);
		
		
		
		button_panel.setLayout(new GridLayout(rows,cols));
		button_panel.setBackground(new Color(150,150,150));
		
		for(int i = 0 ; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
			buttons[i][j] = new JButton();
			button_panel.add(buttons[i][j]);
			buttons[i][j].setIcon(new ImageIcon("./resources/board-tile.png"));
			buttons[i][j].setName("empty");
			buttons[i][j].setFocusable(false);
			buttons[i][j].addActionListener(this);
			}
		}

		
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(controlPanel, BorderLayout.EAST);
		frame.add(button_panel);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == catButton) {
	        //System.out.println("Cat button was clicked");
	        if(cats[turn - 1] > 0) {
	        	catButton.setBackground(new Color(50, 168, 82));
	    		kittenButton.setBackground(new Color(25,25,25));
	    		placeAKitten = false;
	        }
	    } else if (e.getSource() == kittenButton) {
	        //System.out.println("Kitten button was clicked");
	        if(kittens[turn - 1] > 0)  {
	        	kittenButton.setBackground(new Color(50, 168, 82));
	    		catButton.setBackground(new Color(25,25,25));
	    		placeAKitten = true;
	        }
	    } else {
	    	for(int i = 0; i < rows; i++) {
	    		for(int j = 0; j < cols; j++) {
				
	    			if(e.getSource() == buttons[i][j]) {
	    				System.out.println("Button with coords: " + i + " " + j + " was clicked");
	    				if(buttons[i][j].getName() == "empty" && cats[turn - 1] + kittens[turn - 1] > 0) {
	    					if(turn == 1) {
	    						if(placeAKitten) {
	    							buttons[i][j].setIcon(new ImageIcon("./resources/orange-small-kitten-on-tile.png"));
	    	    					buttons[i][j].setName("kitten");
	    							kittens[turn-1] --;
	    							
	    						} else {
	    							buttons[i][j].setIcon(new ImageIcon("./resources/orange-fat-cat-on-tile.png"));
	    	    					buttons[i][j].setName("cat");
	    							cats[turn-1] --;
	    							
	    						}
	    					} else {
	    						if(placeAKitten) {
	    							buttons[i][j].setIcon(new ImageIcon("./resources/gray-small-kitten-on-tile.png"));
	    	    					buttons[i][j].setName("kitten");
	    							kittens[turn-1] --;
	    							
	    						} else {
	    							buttons[i][j].setIcon(new ImageIcon("./resources/gray-fat-cat-on-tile.png"));
	    	    					buttons[i][j].setName("cat");
	    							cats[turn-1] --;
	    							
	    						}
	    					}
	    					checkForBoop(i, j, placeAKitten);
	    					checkForWin();
	    					checkForUpgrade();
	    					changeTurn();
	    				} else if(buttons[i][j].getName() == "kitten" && cats[turn - 1] + kittens[turn - 1] == 0) {
							if((((ImageIcon) buttons[i][j].getIcon()).getDescription().contains("orange") && turn == 1) || 
									(((ImageIcon) buttons[i][j].getIcon()).getDescription().contains("gray") && turn == 2)) {
								cats[turn - 1] ++;
								resetTile(i, j);
								changeTurn();
							}
	    				}
	    			}
	    		}
	    	}
	    }
		
	}
				
			
	
	public void changeTurn() {
		if(turn == 1) {
			turn = 2;
		} else {
			turn = 1;
		}
		if(kittens[turn - 1] > 0) {
			infoLabel.setText("Player " + turn + "'s turn");
			catLabel.setText("Cats: " + cats[turn - 1]);
			kittenLabel.setText("Kittens: " + kittens[turn - 1]);
			kittenButton.setBackground(new Color(50,168,82));
			catButton.setBackground(new Color(25,25,25));
			placeAKitten = true;
		} else {
			infoLabel.setText("Player " + turn + "'s turn");
			catLabel.setText("Cats: " + cats[turn - 1]);
			kittenLabel.setText("Kittens: " + kittens[turn - 1]);
			kittenButton.setBackground(new Color(25,25,25));
			catButton.setBackground(new Color(50,168,82));
			placeAKitten = false;
		}
	}
	
	public void checkForBoop(int x, int y, boolean isItAKitten) {
		boolean doCatsMove = !isItAKitten;
		int[][] cellsToTry = {{x + 1, y, x + 2, y},
                          	  {x, y + 1, x, y + 2},
                          	  {x - 1, y, x - 2, y},
                          	  {x, y - 1, x, y - 2},
                          	  {x + 1, y + 1, x + 2, y + 2},
                          	  {x - 1, y - 1, x - 2, y - 2},
                          	  {x + 1, y - 1, x + 2, y - 2},
                          	  {x - 1, y + 1, x - 2, y + 2}};
		for(int[] setOfCells: cellsToTry) {
			if(!(setOfCells[0] > 5 || setOfCells[1] > 5 || setOfCells[0] < 0 || setOfCells[1] < 0)) {
				if(!(buttons[setOfCells[0]][setOfCells[1]].getName() == "empty")) {
					if(setOfCells[2] > 5 || setOfCells[3] > 5 || setOfCells[2] < 0 || setOfCells[3] < 0) {
						if(doCatsMove || buttons[setOfCells[0]][setOfCells[1]].getName() != "cat") {
							Icon icon = buttons[setOfCells[0]][setOfCells[1]].getIcon();
							String imagePath = ((ImageIcon) icon).getDescription();
							if(imagePath == "./resources/gray-fat-cat-on-tile.png") {
								cats[1] ++;
							} else if(imagePath == "./resources/orange-fat-cat-on-tile.png") {
								cats[0] ++;
							} else if(imagePath == "./resources/gray-small-kitten-on-tile.png") {
								kittens[1] ++;
							} else if(imagePath == "./resources/orange-small-kitten-on-tile.png") {
								kittens[0] ++;
							}
							
							resetTile(setOfCells[0], setOfCells[1]);
						}
					} else {
						if(buttons[setOfCells[2]][setOfCells[3]].getName() == "empty") {
							if(doCatsMove || buttons[setOfCells[0]][setOfCells[1]].getName() != "cat") {
								buttons[setOfCells[2]][setOfCells[3]].setName(buttons[setOfCells[0]][setOfCells[1]].getName());
								buttons[setOfCells[2]][setOfCells[3]].setIcon(buttons[setOfCells[0]][setOfCells[1]].getIcon());
								resetTile(setOfCells[0], setOfCells[1]);
							}
						}
					}
				}
			}
		}
		
	}

	public void checkForUpgrade() {
		int currentPlayer = 0;
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(buttons[i][j].getName() == "kitten" || buttons[i][j].getName() == "cat") {
					System.out.println(i + " " + j + " " + buttons[i][j].getName());
					String imagePath = ((ImageIcon) buttons[i][j].getIcon()).getDescription();
					String[] temp = { "orange", "gray" };
					if(imagePath.contains("orange")) {
						currentPlayer = 1;
					} else if (imagePath.contains("gray")) {
						currentPlayer = 2;
					}
					
					if(i + 1 < 6 && i - 1 > -1) {
						if(((ImageIcon) buttons[i+1][j].getIcon()).getDescription().contains(temp[currentPlayer - 1]) && ((ImageIcon) buttons[i-1][j].getIcon()).getDescription().contains(temp[currentPlayer - 1])) {
							resetTile(i+1,j);
							resetTile(i,j);
							resetTile(i-1,j);
							
							cats[currentPlayer - 1] += 3;
							break;
						}
					}
					if(j + 1 < 6 && j - 1 > -1) {
						if(((ImageIcon) buttons[i][j+1].getIcon()).getDescription().contains(temp[currentPlayer - 1]) && ((ImageIcon) buttons[i][j-1].getIcon()).getDescription().contains(temp[currentPlayer - 1])) {
							resetTile(i,j+1);
							resetTile(i,j);
							resetTile(i,j-1);
							
							cats[currentPlayer - 1] += 3;
							break;
						}
					} 
					if(i + 1 < 6 && i - 1 > -1 && j + 1 < 6 && j - 1 > -1) {
						if(((ImageIcon) buttons[i+1][j+1].getIcon()).getDescription().contains(temp[currentPlayer - 1]) && ((ImageIcon) buttons[i-1][j-1].getIcon()).getDescription().contains(temp[currentPlayer - 1])) {
							resetTile(i+1,j+1);
							resetTile(i,j);
							resetTile(i-1,j-1);
							
							cats[currentPlayer - 1] += 3;
							break;
						} 
					} 
					if(i + 1 < 6 && i - 1 > -1 && j + 1 < 6 && j - 1 > -1) {
						if(((ImageIcon) buttons[i-1][j+1].getIcon()).getDescription().contains(temp[currentPlayer - 1]) && ((ImageIcon) buttons[i+1][j-1].getIcon()).getDescription().contains(temp[currentPlayer - 1])) {
							resetTile(i-1,j+1);
							resetTile(i,j);
							resetTile(i+1,j-1);
							
							cats[currentPlayer - 1] += 3;
							break;
						}
					} 
					
					
				}
			}
			
		}
	}
	
	public void checkForWin() {
		int winner = 0;
		boolean gameEnds = false;
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(buttons[i][j].getName() == "cat" && gameEnds ==false) {
					System.out.println(i + " " + j + " " + buttons[i][j].getName());
					String imagePath = ((ImageIcon) buttons[i][j].getIcon()).getDescription();

					if(imagePath.contains("orange")) {
						winner = 1;
					} else if (imagePath.contains("gray")) {
						winner = 2;
					}
					
					if(i + 1 < 6 && i - 1 > -1) {
						if(((ImageIcon) buttons[i+1][j].getIcon()).getDescription() == imagePath && ((ImageIcon) buttons[i-1][j].getIcon()).getDescription() == imagePath) {
							gameEnds = true;
							System.out.println(1);
							break;
						}
					}
					if(j + 1 < 6 && j - 1 > -1) {
						if(((ImageIcon) buttons[i][j+1].getIcon()).getDescription() == imagePath && ((ImageIcon) buttons[i][j-1].getIcon()).getDescription() == imagePath) {
							gameEnds = true;
							System.out.println(2);
							break;
						}
					} 
					if(i + 1 < 6 && i - 1 > -1 && j + 1 < 6 && j - 1 > -1) {
						if(((ImageIcon) buttons[i+1][j+1].getIcon()).getDescription() == imagePath && ((ImageIcon) buttons[i-1][j-1].getIcon()).getDescription() == imagePath) {
							gameEnds = true;
							System.out.println(3);
							break;
						} 
					} 
					if(i + 1 < 6 && i - 1 > -1 && j + 1 < 6 && j - 1 > -1) {
						if(((ImageIcon) buttons[i-1][j+1].getIcon()).getDescription() == imagePath && ((ImageIcon) buttons[i+1][j-1].getIcon()).getDescription() == imagePath) {
							gameEnds = true;
							System.out.println(4);
							break;
						}
					} 
					
					
				}
			}
			
		}
		
		if(gameEnds) {
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					buttons[i][j].setEnabled(false);
				}
			}
			textfield.setText("Player " + winner + " wins!");
		}
		
	}
	
	public void resetTile(int x, int y) {
		buttons[x][y].setName("empty");
		buttons[x][y].setIcon(new ImageIcon("./resources/board-tile.png"));
	}
	
}
