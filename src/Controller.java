

public class Controller {
	public static void addChip(int column, ConnectGUI gameName){
		if (Database.gameBoard.get(column).size() == gameName.getSize())
			System.out.println("That column is full!");
		else
			gameName.addChip(column, Database.addChip(column));
	}
	public static int checkWinner(ConnectGUI gameName){
		for(int i = 0; i <= gameName.getSize()-gameName.getWinState(); i++)
			for (int j = 0; j <= gameName.getSize()-gameName.getWinState(); i++)
			{
				//CHECK POSITIVE DIAGONAL WIN
				if(		Database.gameBoard.get(i).get(j)== 
						Database.gameBoard.get(i+1).get(j+1) &&
						Database.gameBoard.get(i+1).get(j+1) ==
						Database.gameBoard.get(i+2).get(j+2) &&
						Database.gameBoard.get(i+2).get(j+2) ==
						Database.gameBoard.get(i+3).get(j+3))
					return Database.currentPlayer;
				//CHECK NEGATIVE DIAGONAL WIN
				if(		Database.gameBoard.get(i).get(j+3)== 
						Database.gameBoard.get(i+1).get(j+2) &&
						Database.gameBoard.get(i+1).get(j+2) ==
						Database.gameBoard.get(i+2).get(j+1) &&
						Database.gameBoard.get(i+2).get(j+1) ==
						Database.gameBoard.get(i+3).get(j))
					return Database.currentPlayer;
			}
		for(int i = 0; i <= gameName.getSize()-gameName.getWinState(); i++)
			for(int j = 0; j <= gameName.getSize(); i++)
			{
				//CHECK VERTICAL WIN
				if(		Database.gameBoard.get(i).get(j)== 
						Database.gameBoard.get(i).get(j+1) &&
						Database.gameBoard.get(i).get(j+1) ==
						Database.gameBoard.get(i).get(j+2) &&
						Database.gameBoard.get(i).get(j+2) ==
						Database.gameBoard.get(i).get(j+3))
					return Database.currentPlayer;
				//CHECK HORIZONTAL WIN
				if(		Database.gameBoard.get(i).get(j)== 
						Database.gameBoard.get(i+1).get(j) &&
						Database.gameBoard.get(i+1).get(j) ==
						Database.gameBoard.get(i+2).get(j) &&
						Database.gameBoard.get(i+2).get(j) ==
						Database.gameBoard.get(i+3).get(j))
					return Database.currentPlayer;
			}
		return 0;
	}
}
