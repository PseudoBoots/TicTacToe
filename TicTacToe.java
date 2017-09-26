import java.util.Scanner;
import java.util.Random;

public class TicTacToe
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		char[][] board = new char[3][3];
		String input = "";
		String coord = "";

		clearBoard(board);
		System.out.print("Please enter number of players(1 or 2): ");
		while(!input.equals("1") && !input.equals("2"))
		{
			if(!input.equals("")) System.out.print("Please enter a valid number(1 or 2): ");
			input = scan.next();
		}

		if(input.equals("1"))
		{
			//1 player vs AI
			System.out.println("\nONE PLAYER MODE SELECTED\n");
			sleep(1000);
			displayBoard(board);
			while(true)
			{
				//game loop
				System.out.print("You are X select a coordinate: ");
				coord = scan.next();
				while(!isValidCoord(coord))
				{
					System.out.print("Invalid coordinate! Please enter valid coordinate: ");
					coord = scan.next();
				}
				while(!enterCoord(coord, 'X', board))
				{
					System.out.print("Coordinate already taken! Enter a free coordinate: ");
					coord = scan.next();
					while(!isValidCoord(coord))
					{
						System.out.print("Invalid coordinate! Please enter valid coordinate: ");
						coord = scan.next();
					}
				}
				displayBoard(board);
				checkForWin(board);
				checkForFullBoard(board);

				//AI move
				System.out.println("Now the AI will make a move.");
				sleep(1000);
				while(!enterCoord(getAICoord(board),'O',board)){}
				displayBoard(board);
				checkForWin(board);
				checkForFullBoard(board);

			}//end 1 player game loop
		}
		else if(input.equals("2"))
		{
			//2 players taking turns
			System.out.println("\nTWO PLAYER MODE SELECTED\n");
			sleep(1000);
			displayBoard(board);
			while(true)
			{
				//game loop
				System.out.print("It is X's turn. Enter a coordinate: ");
				coord = scan.next();
				while(!isValidCoord(coord))
				{
					System.out.print("Invalid coordinate! Please enter valid coordinate: ");
					coord = scan.next();
				}
				while(!enterCoord(coord, 'X', board))
				{
					System.out.print("Coordinate already taken! Enter a free coordinate: ");
					coord = scan.next();
					while(!isValidCoord(coord))
					{
						System.out.print("Invalid coordinate! Please enter valid coordinate: ");
						coord = scan.next();
					}
				}
				displayBoard(board);
				checkForWin(board);
				checkForFullBoard(board);
				System.out.print("It is O's turn. Enter a coordinate: ");
				coord = scan.next();
				while(!isValidCoord(coord))
				{
					System.out.print("Invalid coordinate! Please enter valid coordinate: ");
					coord = scan.next();
				}
				while(!enterCoord(coord, 'O', board))
				{
					System.out.print("Coordinate already taken! Enter a free coordinate: ");
					coord = scan.next();
					while(!isValidCoord(coord))
					{
						System.out.print("Invalid coordinate! Please enter valid coordinate: ");
						coord = scan.next();
					}
				}
				displayBoard(board);
				checkForWin(board);
				checkForFullBoard(board);
			}//end of 2 player game loop
		}

	}//end main

	private static void clearBoard(char[][] board)
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				board[i][j] = ' ';
			}
		}
	}//end of clear board

	private static boolean isValidCoord(String c)
	{
		return c.matches("([A-Ca-c][1-3])|([1-3][A-Ca-c])");
	}

	private static void sleep(int time)
	{
		try{
			Thread.sleep(time);
		} catch(Exception e){}
	}//end sleep

	private static String getAICoord(char[][] board)
	{
		Random rand = new Random();
		int row = rand.nextInt(3) + 1;
		char col = (char)('a' + rand.nextInt(3));
		String output = "" + col + row;
		int numO;
		int numX;

		//check for 2 in a row
		//check row
		for(int i = 0; i < board.length; i++)
		{
			numX = 0;
			numO = 0;
			for(int j = 0; j < board[i].length; j++)
			{
				if(board[i][j] == 'X') numX++;
				else if(board[i][j] == 'O') numO++;
			}
			if(numX == 2 || numO == 2)
			{
				for(int j = 0; j < board[i].length; j++)
				{
					if(board[i][j] == ' ')
					{
						if(rand.nextInt(10) + 1 > 3)
						{
							//70% chance of selecting the third spot
							row = i + 1;
							col = (char) ('a' + j);
							output = "" + col + row;
							return output;
						}
						break;//we already know what the last space is
					}
				}
			}
		}
		//check col
		for(int i = 0; i < board.length; i++)
		{
			numX = 0;
			numO = 0;
			for(int j = 0; j < board[i].length; j++)
			{
				if(board[j][i] == 'X') numX++;
				else if(board[i][j] == 'O') numO++;
			}
			if(numX == 2 || numO == 2)
			{
				for(int j = 0; j < board[i].length; j++)
				{
					if(board[j][i] == ' ')
					{
						if(rand.nextInt(10) + 1 > 3)
						{
							//70% chance of selecting the third spot
							row = j + 1;
							col = (char) ('a' + i);
							output = "" + col + row;
							return output;
						}
						break;
					}
				}
			}
		}
		//check \ diagonal
		numX = 0;
		numO = 0;
		if(board[0][0] == 'X') numX++;
		else if(board[0][0] == 'O') numO++;
		if(board[1][1] == 'X') numX++;
		else if(board[1][1] == 'O') numO++;
		if(board[2][2] == 'X') numX++;
		else if(board[2][2] == 'O') numO++;

		if(numO == 2 || numX == 2)
		{
			if(board[0][0] == ' ')
			{
				if(rand.nextInt(10) + 1 > 3) return "a1";
			}
			if(board[1][1] == ' ')
			{
				if(rand.nextInt(10) + 1 > 3) return "b2";
			}
			if(board[2][2] == ' ')
			{
				if(rand.nextInt(10) + 1 > 3) return "c3";
			}
		}
		//check / diagonal
		numX = 0;
		numO = 0;
		if(board[0][2] == 'X') numX++;
		else if(board[0][2] == 'O') numO++;
		if(board[1][1] == 'X') numX++;
		else if(board[1][1] == 'O') numO++;
		if(board[2][0] == 'X') numX++;
		else if(board[2][0] == 'O') numO++;

		if(numO == 2 || numX == 2)
		{
			if(board[0][2] == ' ')
			{
				if(rand.nextInt(10) + 1 > 3) return "c1";
			}
			if(board[1][1] == ' ')
			{
				if(rand.nextInt(10) + 1 > 3) return "b2";
			}
			if(board[2][0] == ' ')
			{
				if(rand.nextInt(10) + 1 > 3) return "a3";
			}
		}

		if(!isValidCoord(output))
		{
			System.out.printf("INVALID AI MOVE ERROR: %s", output);
			System.exit(0);
		}
		System.out.println(output);
		return output;
	}//end getAICoord

	private static void checkForFullBoard(char[][] board)
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				if(board[i][j] == ' ') return;
			}
		}
		//if there are no empty spaces in the array
		System.out.println("\nThere are no more spaces available, it is a\n");
		System.out.println(
			"#####################################\n" +
			"#  **    * * *      *    *       *  #\n" +
			"#  *  *  *   *     * *    *     *   #\n" +
			"#  *  *  *  *     * _ *    * * *    #\n" +
			"#  **    *    *  *     *    * *     #\n" +
			"#####################################\n");
		System.exit(0);
	}//end of checkForFullBoard

	private static void checkForWin(char[][] board)
	{
		int numX;
		int numO;
		//check rows
		for(int i = 0; i < board.length; i++)
		{
			numX = 0;
			numO = 0;
			for(int j = 0; j < board[i].length; j++)
			{
				if(board[i][j] == 'X') numX++;
				else if(board[i][j] == 'O') numO++;
			}
			if(numX == 3) displayWin("X");
			if(numO == 3) displayWin("O");
		}
		//check cols
		for(int i = 0; i < board.length; i++)
		{
			numX = 0;
			numO = 0;
			for(int j = 0; j < board[i].length; j++)
			{
				if(board[j][i] == 'X') numX++;
				else if(board[j][i] == 'O') numO++;
			}
			if(numX == 3) displayWin("X");
			if(numO == 3) displayWin("O");
		}

		//check \ diagonal
		if(board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') displayWin("X");
		if(board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') displayWin("O");
		//check / diagonal
		if(board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') displayWin("X");
		if(board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') displayWin("O");
	}//end of checkForWin

	private static void displayWin(String winner)
	{
		System.out.print("The winner is");
		sleep(400);
		System.out.print(".");
		sleep(400);
		System.out.print(".");
		sleep(400);
		System.out.print(".\n\n");
		sleep(600);

		if(winner.equals("X"))
		{
			//display X
			System.out.println(
				" ,ggg,          ,gg\n"+
				"dP\"\"\"Y8,      ,dP' \n"+
				"Yb,_  \"8b,   d8\"   \n"+
				" `\"\"    Y8,,8P'    \n"+
				"         Y88\"      \n"+
				"        ,888b      \n"+
				"       d8\" \"8b,    \n"+
				"     ,8P'    Y8,   \n"+
				"    d8\"       \"Yb, \n"+
				"  ,8P'          \"Y8\n"
                   );
		}
		else if(winner.equals("O"))
		{
			//display O
			System.out.println(
				"   _,gggggg,_     \n"+
				" ,d8P\"\"d8P\"Y8b,   \n"+
				",d8'   Y8   \"8b,dP\n"+
				"d8'    `Ybaaad88P'\n"+
				"8P       `\"\"\"\"Y8  \n"+
				"8b            d8  \n"+
				"Y8,          ,8P  \n"+
				"`Y8,        ,8P'  \n"+
				" `Y8b,,__,,d8P'   \n"+
				"   `\"Y8888P\"'     \n"
				);
		}
		System.exit(0);
	}//end displayWin

	private static boolean enterCoord(String crd, char symbol, char[][] board)
	{
		int col;
		int row;
		if(Character.isDigit(crd.charAt(0)))
		{
			//coord is num then letter format
			row = Character.getNumericValue(crd.charAt(0));
			row--;

			switch(crd.charAt(1)){
				case 'a': col = 0;
					break;
				case 'A': col = 0;
					break;
				case 'b': col = 1;
					break;
				case 'B': col = 1;
					break;
				case 'c': col = 2;
					break;
				case 'C': col = 2;
					break;
				default: col = -1;
					System.out.println("INVALID COORDINATE ERROR");
					System.exit(0);
					break;
			}
		}
		else
		{
			//coord is letter then num format
			row = Character.getNumericValue(crd.charAt(1));
			row--;

			switch(crd.charAt(0)){
				case 'a': col = 0;
					break;
				case 'A': col = 0;
					break;
				case 'b': col = 1;
					break;
				case 'B': col = 1;
					break;
				case 'c': col = 2;
					break;
				case 'C': col = 2;
					break;
				default: col = -1;
					System.out.println("INVALID COORDINATE ERROR");
					System.exit(0);
					break;
			}
		}
		if(board[row][col] == ' ')
		{
			board[row][col] = symbol;
			return true;
		}
		return false;
	}//end enterCoord

	private static void displayBoard(char[][] board)
	{
		System.out.println("   a   b   c");
		System.out.printf("1  %s | %s | %s\n", board[0][0], board[0][1], board[0][2]);
		System.out.println("   --+---+--");
		System.out.printf("2  %s | %s | %s\n", board[1][0], board[1][1], board[1][2]);
		System.out.println("   --+---+--");
		System.out.printf("3  %s | %s | %s\n", board[2][0], board[2][1], board[2][2]);
	}//end of displayBoard
}//end class