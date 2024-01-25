package TicTakToe;
import java.util.Scanner;
public class GameManager {
	
	private Player player1, player2;
	private Board board;
	private int numPlayers;

	public static void main(String args[]){
		GameManager t = new GameManager();
		t.startGame();
	}

	public void startGame(){
		Scanner s = new Scanner(System.in);
		// Take Players input
		player1 = takePlayerInput(++numPlayers);
		player2 = takePlayerInput(++numPlayers);
		
		while(player1.getSymbol() == player2.getSymbol()){
			System.out.println("Symbol Already taken !! Pick another symbol !!");
			player2.setSymbol(s.next().charAt(0));
		}
		// Create the Board
		board = new Board(player1.getSymbol(), player2.getSymbol());
	
		// Play the Game
		// toggle between turns
		boolean player1Turn = true;
		int status = Board.INCOMPLETE;
		while(status == Board.INCOMPLETE || status == Board.INVALID_MOVE){
			if(player1Turn){
				System.out.println("Player 1 - " + player1.getName() + "'s turn");
				// enter the cell for the players move
				System.out.println("Enter x: ");
				int x = s.nextInt();
				System.out.println("Enter y: ");
				int y = s.nextInt();
				// pass the player symbol who moves and the x,y coordinate
				status = board.move(player1.getSymbol(), x, y);
				if(status == Board.INVALID_MOVE) {
					System.out.println("Invalid move please try again!");
					// continue with an invalid move so player turns aren't switched
					continue;
				}
				
			}else{
				// player 2's move
				System.out.println("Player 2 - " + player2.getName() + "'s turn");
				// enter the cell for the players move
				System.out.println("Enter x: ");
				int x = s.nextInt();
				System.out.println("Enter y: ");
				int y = s.nextInt();
				// pass the player symbol who moves and the x,y coordinate
				status = board.move(player1.getSymbol(), x, y);
				if(status == Board.INVALID_MOVE) {
					System.out.println("Invalid move please try again!");
					// continue with an invalid move so player turns aren't switched
					continue;
				}
			}
			// toggling between turns
			player1Turn = !player1Turn;
			// print the board after each turn
			board.print();
		}	
		if(status == Board.PLAYER1_WINS) {
			System.out.println("Player 1 - " + player1.getName() + "Wins's !");
		}else if(status == Board.PLAYER2_WINS) {
			System.out.println("Player 2 - " + player2.getName() + "Wins's !");
		}else {
			System.out.println("Draw !");
		}
	}

	private Player takePlayerInput(int num){
			Scanner s = new Scanner(System.in);
			System.out.println("Enter Player " + num + " name: ");
			String name = s.nextLine();
			System.out.println("Enter Player " + num + " symbol: ");
			char symbol = s.next().charAt(0);
			Player p = new Player(name, symbol);
			return p;
		}
	}
