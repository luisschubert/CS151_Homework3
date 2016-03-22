
import java.util.ArrayList;
import java.util.Stack;

public class Database {
	static int currentPlayer = 1;
	static ArrayList<ArrayList<Integer>> gameBoard = new ArrayList<>();
	public static void setGameBoard(int size) {
		for(int i = 0; i < size; i++){
			ArrayList<Integer> stk = new ArrayList<>();
			gameBoard.add(stk);
		}
	}
	public static int getColumns(){
		return gameBoard.size();
	}
	public static int addChip(int column){
		//returns how many chips are in this column and adds current player's chip
		gameBoard.get(column).add(currentPlayer);
		currentPlayer = 3 - currentPlayer;
		return gameBoard.get(column).size();
		
	}
}
