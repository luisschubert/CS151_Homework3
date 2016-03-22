
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class ConnectGUI {
	private int size, winState;
	public ConnectGUI(int siz, int winStat) {
		size = siz;
		winState = winStat;
		Database.setGameBoard(siz);
	}
	static ImageIcon redInBoard = new ImageIcon("images/redInBoardsm.png", "redInBoard");
	static ImageIcon redChip = new ImageIcon("images/redChipsm.png", "redChip");
	static ImageIcon noneInBoard = new ImageIcon("images/noneInBoardsm.png", "noneInBoard");
	static ImageIcon blueInBoard = new ImageIcon("images/blueInBoardsm.png", "blueInBoard");
	static ImageIcon blueChip = new ImageIcon("images/blueChipsm.png", "blueChip");
	static ImageIcon redChipOp = new ImageIcon("images/redChipsmOp.png", "redChipOp");
	static ImageIcon blueChipOp = new ImageIcon("images/blueChipsmOp.png", "blueChipOp");
	
	JLabel[] chipButtons;
	JLabel[][] gameBoardSlot;
	public void addChip(int column, int row){
		
		if (Database.currentPlayer == 2){
			gameBoardSlot[getSize()-row][column].setIcon(redInBoard);
			for(int i = 0; i < chipButtons.length; i++)
				chipButtons[i].setIcon(blueChipOp);
			chipButtons[column].setIcon(blueChip);
		}
		else{
			gameBoardSlot[getSize()-row][column].setIcon(blueInBoard);
			for(int i = 0; i < chipButtons.length; i++)
				chipButtons[i].setIcon(redChipOp);
			chipButtons[column].setIcon(redChip);
		}
		if(Controller.checkWinner(this) != 0){
			gameOver(Controller.checkWinner(this));
		}
	}
	public void gameOver(int winner){
		System.out.println("Player " + (3 - Database.currentPlayer) + " wins!");
		//TODO: show gameover screen with winner
	}
	public void drawBoard(){
		chipButtons = new JLabel[getSize()];
		gameBoardSlot = new JLabel[getSize()][getSize()];
		JFrame guiFrame = new JFrame();
		//guiFrame.setSize(new Dimension(size * 155 + 20, (size+1) * 155 + 20));
		//guiFrame.setPreferredSize(new Dimension(1000, 1000));
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(getSize()+1, getSize()));
		contentPane.setSize(new Dimension(getSize() * 55 + 20, (getSize()+1) * 55 + 20));
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int i = 0; i < getSize(); i++){
			chipButtons[i] = new JLabel();
		}
		for(int i = 0; i < getSize(); i++){
			for (int j = 0; j < getSize(); j++)
				gameBoardSlot[i][j] = new JLabel();
		}
		
		for(int i = 0; i < getSize(); i++){
			chipButtons[i].setIcon(redChipOp);
			chipButtons[i].addMouseListener(new MouseListener() {
				
				@Override public void mouseReleased(MouseEvent e) {}
				@Override public void mousePressed(MouseEvent e) {}
				
				@Override
				public void mouseExited(MouseEvent e) {
					if (Database.currentPlayer == 2)
						ConnectGUI.this.chipButtons[e.getComponent().getX()/50].setIcon(blueChipOp);
					else
						ConnectGUI.this.chipButtons[e.getComponent().getX()/50].setIcon(redChipOp);
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					if (Database.currentPlayer == 2)
						ConnectGUI.this.chipButtons[e.getComponent().getX()/50].setIcon(blueChip);
					else
						ConnectGUI.this.chipButtons[e.getComponent().getX()/50].setIcon(redChip);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					Controller.addChip(e.getComponent().getX()/50, ConnectGUI.this);
					
				}
			});//end mouseListener
			
	        contentPane.add(chipButtons[i]);
	        
			}//end for loop
		for(int i = 0; i < getSize(); i++){
			for(int j = 0; j < getSize(); j++){
				gameBoardSlot[i][j].setIcon(noneInBoard);
				contentPane.add(gameBoardSlot[i][j]);
			}
		}
		
		guiFrame.setContentPane(contentPane);
		guiFrame.pack();
		guiFrame.setVisible(true);
		//draw the gameboard with specified size and required chips in a row to win
	}
	public static void main(String[] args){
		ConnectGUI game = new ConnectGUI(8, 4);
		game.drawBoard();
		
	}
	public int getSize() {
		return size;
	}
	public int getWinState() {
		return winState;
	}
}
