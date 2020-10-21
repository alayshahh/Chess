package chess;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Alay Shah & Anshika Khare
 */

public class Chess {

	static Scanner sc = new Scanner(System.in);

	private static Board gameBoard;
	private static Player white;
	private static Player black;
	private static boolean drawAsked;


	public static void main(String [] args) throws IOException {
		initGame();//put all pieces in place and populate player's list of pieces with their pieces

		gameBoard.printBoard(); //prints out the board as it is

		Team turn = Team.WHITE;

		boolean gameOn = true;

		white.getValidMoves(gameBoard); //gets valid moves for white -> first turn


		while(gameOn) {


			System.out.print(turn+"'s move: ");
			String [] input = getMove();
			System.out.println();

			if(input.length<1||input.length>3) { //empty move or more than 2 spaces in input
				System.out.println("Illegal move, try again \n");
				continue;
			}

			if(input.length==1) { //can be draw confirmation or resign
				if(drawAsked && input[0].equals("draw")){
					return;
				}else if(input[0].equals("resign")) {
					if(turn==Team.WHITE) {
						System.out.println("Black Wins");
					}else System.out.println("White wins");
					return;

				}else {
					System.out.println("Illegal move, try again \n");
					continue;
				}
			}

			drawAsked = false;

			if(input.length==3) {
				if(input[2].equals("draw?")) {
					drawAsked = true;
				}else {
					if(!(input[2].length()==1 && ( input[2].equals("N") || input[2].equals("B") || input[2].equals("Q") || input[2].equals("R")))){
						System.out.println("Illegal move, try again \n");
						continue;
					}
				}
			}


			Move m = new Move(new Location(input[0]), new Location(input[1]));

			//check to see if move is valid i.e it is in the list of moves we have
			if(turn==Team.WHITE) {
				int indx = white.getMoves().indexOf(m);
				if(indx!=-1) {
					m = white.getMoves().get(indx);
				}else {
					System.out.println("Illegal move, try again \n");
					continue;
				}
			}else {
				int indx = black.getMoves().indexOf(m);
				if(indx!=-1) {
					m= black.getMoves().get(indx);
				}else {
					System.out.println("Illegal move, try again \n");
					continue;
				}	
			}
			if(m.type==MoveType.PROMOTION && input.length==3) {
				m.promotion = getPromotion(input[2]);
			}

			//if the move is valid execute the move
			




		}

		//implement rest
	}


	private static String [] getMove() throws IOException{
		String input  = sc.nextLine().trim();
		String [] inputs  = input.split(" ");
		return inputs;
	}

	private static PieceType getPromotion(String s) {
		switch (s) {
		case "Q":
			return PieceType.QUEEN;
		case "R":
			return PieceType.ROOK;
		case "B":
			return PieceType.BISHOP;
		case "N": 
			return PieceType.KNIGHT;
		default:
			return PieceType.QUEEN;
		}
	}

	private static void initGame() {
		gameBoard  = new Board();
		drawAsked = false;
		King whiteKing =  new King (Team.WHITE, new Location("e1"));
		gameBoard.board[whiteKing.getRank()][whiteKing.getFile()] = whiteKing;
		King blackKing = new King(Team.BLACK, new Location ("e8"));
		gameBoard.board[blackKing.getRank()][blackKing.getFile()] = blackKing;
		white  =  new Player(Team.WHITE, whiteKing);
		black  = new Player (Team.BLACK, blackKing);
		Location [] whitePawn = {new Location("a2"),new Location("b2"),new Location("c2"),new Location("d2"),new Location("e2"), new Location("f2"), new Location("g2"), new Location("h2")};
		for(Location l : whitePawn) {
			Piece p = new Pawn(Team.WHITE, l);
			gameBoard.board[p.getRank()][p.getFile()] = p;
		}
		Location [] blackPawn = {new Location("a7"),new Location("b7"),new Location("c7"),new Location("d7"),new Location("e7"), new Location("f7"), new Location("g7"), new Location("h7")};
		for(Location l : blackPawn) {
			Piece p = new Pawn(Team.BLACK, l);
			gameBoard.board[p.getRank()][p.getFile()] = p;
		}
		Location [] wR = {new Location("h1"), new Location("a1")};
		Location [] wN = {new Location("g1"), new Location("b1")};
		Location [] wB = {new Location ("c1"), new Location("f1")};
		Location [] bR = {new Location("h8"), new Location("a8")};
		Location [] bN = {new Location("g8"), new Location("b8")};
		Location [] bB = {new Location ("c8"), new Location("f8")};
		for (Location l: wR) {
			Piece p = new Rook(Team.WHITE, l);
			gameBoard.board[p.getRank()][p.getFile()] = p;
		}

		for (Location l: wN) {
			Piece p = new Knight(Team.WHITE, l);
			gameBoard.board[p.getRank()][p.getFile()] = p;
		}
		for(Location l: wB) {
			Piece p = new Bishop(Team.WHITE, l);
			gameBoard.board[p.getRank()][p.getFile()] = p;
		}
		for (Location l: bR) {
			Piece p = new Rook(Team.BLACK, l);
			gameBoard.board[p.getRank()][p.getFile()] = p;
		}

		for (Location l: bN) {
			Piece p = new Knight(Team.BLACK, l);
			gameBoard.board[p.getRank()][p.getFile()] = p;
		}
		for(Location l: bB) {
			Piece p = new Bishop(Team.BLACK, l);
			gameBoard.board[p.getRank()][p.getFile()] = p;
		}
		Queen bQ = new Queen(Team.BLACK, new Location("d8"));
		gameBoard.board[bQ.getRank()][bQ.getFile()] = bQ;
		Queen wQ = new Queen(Team.WHITE, new Location("d1"));
		gameBoard.board[wQ.getRank()][wQ.getFile()] = wQ;
		white.getPieces(gameBoard);
		black.getPieces(gameBoard);



	}

}

