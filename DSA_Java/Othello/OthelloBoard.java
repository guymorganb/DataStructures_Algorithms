package Othello;

public class OthelloBoard {
	
	
	private int board[][];
	final static int player1Symbol = 1;
	final static int player2Symbol = 2;

	public OthelloBoard() {
		board = new int[8][8];
		board[3][3] = player1Symbol;
		board[3][4] = player2Symbol;
		board[4][3] = player2Symbol;
		board[4][4] = player1Symbol;
	}

	public void print() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public boolean move(int symbol, int x, int y) {
    if (board[x][y] != 0) {
        return false;  // Cell already occupied.
    }

    int opponentSymbol = (symbol == player1Symbol) ? player2Symbol : player1Symbol;
    boolean validMove = false;

    // Directions: Up, Down, Left, Right, Diagonal Left Up, Diagonal Right Up, Diagonal Left Down, Diagonal Right Down
    int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    for (int dir = 0; dir < 8; dir++) {
        int currX = x + dx[dir], currY = y + dy[dir];
        boolean foundOpponent = false;

        while (currX >= 0 && currX < 8 && currY >= 0 && currY < 8) {
            if (board[currX][currY] == opponentSymbol) {
                foundOpponent = true;
            } else if (board[currX][currY] == symbol) {
                if (foundOpponent) {
                    validMove = true;
                    // Flip the pieces for this direction.
                    while (currX != x || currY != y) {
                        currX -= dx[dir];
                        currY -= dy[dir];
                        board[currX][currY] = symbol;
                    }
                    break;
                } else {
                    break;
                }
            } else {
                break;  // Empty cell.
            }

            currX += dx[dir];
            currY += dy[dir];
        }
    }

    if (validMove) {
        board[x][y] = symbol;
    }
    
    return validMove;
	}
}
