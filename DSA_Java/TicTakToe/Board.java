package TicTakToe;

public class Board {
	private char board[][];
	private int boardSize = 3;
	private char p1Symbol, p2Symbol;
	private int count;	// count of cell filled. Initial value is 0
	private static final char EMPTY = ' ';	
	public static final int PLAYER1_WINS = 1;
	public static final int PLAYER2_WINS = 2;
	public static final int DRAW = 3;
	public static final int INCOMPLETE = 4;
	public static final int INVALID_MOVE = 5;
	
	public Board (char p1Symbol, char p2Symbol) {
		
		board = new char[boardSize][boardSize];
		
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				board[i][j] = EMPTY;	// filling each cell with an ' ' 
			}
		}
		this.p1Symbol = p1Symbol;
		this.p2Symbol = p2Symbol; 
	}
	
	public void print() {
		System.out.println("--------------");
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				System.out.print("| " + board[i][j] + " |");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("--------------");
	}
	
	public int move(char symbol, int x, int y) {
		// check for invalid move
		if(x < 0 || x >= boardSize || y < 0 || y >= boardSize || board[x][y] != EMPTY) {
			return INVALID_MOVE;
		}
		// place the players move
		board[x][y] = symbol;
		count++;
		// check for winning conditions
		/// check for winning row
		if(board[x][0] == board[x][1] && board[x][0] == board[x][2]) {
			return symbol == p1Symbol ? PLAYER1_WINS : PLAYER2_WINS;
		}
		
		/// check for winning column
		if(board[0][y] == board[1][y] && board[0][y] == board[2][y]) {
		    return symbol == p1Symbol ? PLAYER1_WINS : PLAYER2_WINS;
		}
		// check diagonal right --> left
		if(board[x][y] == board[0][0] || board[x][y] == board[1][1] || board[x][y] == board[2][2]) {
			
			if((board[0][0] == board[1][1] && board[0][0] == board[2][2])) {
				return symbol == p1Symbol ? PLAYER1_WINS : PLAYER2_WINS;
			}
		}
		// check diagonal left --> right (alternate way to manage the check, without an outter if like the above check
		if(board[0][2] != EMPTY && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
			return symbol == p1Symbol ? PLAYER1_WINS : PLAYER2_WINS;
		}
		
		
		if(count == boardSize * boardSize) {
			return DRAW;
		}
		
		return INCOMPLETE;

	}
	
	
	
	public void diagonalVictory(int[][] gameBoard, int row, int col, char p1Symbol, char p2Symbol) {
	
	    
	}


	
	public void noWinner() {
		
	}
}
