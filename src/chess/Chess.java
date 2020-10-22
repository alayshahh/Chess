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
				if(input[0].equals("draw")){
					System.out.println("draw");
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

			if(input.length==3) {
				if(!input[2].equals("draw?") && !( input[2].equals("N") || input[2].equals("B") || input[2].equals("Q") || input[2].equals("R"))) {
					System.out.println("Illegal move, try again \n");
					continue;
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
					System.out.println(white.getMoves());
					continue;
				}
			}else {
				int indx = black.getMoves().indexOf(m);
				if(indx!=-1) {
					m= black.getMoves().get(indx);
				}else {
					System.out.println("Illegal move, try again \n");
					System.out.println(black.getMoves());
					continue;
				}	
			}
			if(m.type==MoveType.PROMOTION && input.length==3) {
				m.promotion = getPromotion(input[2]);
			}
			executeMove(m);
			
			gameBoard.printBoard();
			
			boolean check = false;
			boolean checkMate =  false;
			
			if(turn == Team.WHITE) {
				black.getPieces(gameBoard);
				black.getValidMoves(gameBoard);
				check = black.inCheck(gameBoard.board, null);
				checkMate = black.getMoves().isEmpty();
			}else {
				white.getPieces(gameBoard);
				white.getValidMoves(gameBoard);
				check = white.inCheck(gameBoard.board, null);
				checkMate = white.getMoves().isEmpty();
			}
		
			if(checkMate) {
				System.out.println("Checkmate \n");
				System.out.println(turn+" Wins");
				return;
			}
			if(check) {
				System.out.println("Check \n");
			}
			
			if(turn==Team.WHITE) {
				turn = Team.BLACK;
			}else turn = Team.WHITE;
		}

		//implement rest
	}
	
	
	private static void executeMove(Move m) {
		
		//change location field of peice
		
		gameBoard.board[m.cur.getRank()][m.cur.getFile()].curLoc = m.next;
		gameBoard.board[m.cur.getRank()][m.cur.getFile()].hasMoved = true;
		//change location of Piece on Board
		gameBoard.board[m.next.getRank()][m.next.getFile()] = gameBoard.board[m.cur.getRank()][m.cur.getFile()];
		gameBoard.board[m.cur.getRank()][m.cur.getFile()] = null;
		
		if(m.type==MoveType.ENPASSANT) { 
			//remove captured piece
			gameBoard.board[m.other.cur.getRank()][m.other.cur.getFile()] = null; 
		}
		if(m.type==MoveType.CASTLING) {
			
			//set rook location
			gameBoard.board[m.other.cur.getRank()][m.other.cur.getFile()].hasMoved = true;
			gameBoard.board[m.other.cur.getRank()][m.other.cur.getFile()].curLoc = m.other.next;
			//move rook
			gameBoard.board[m.other.next.getRank()][m.other.next.getFile()]= gameBoard.board[m.other.cur.getRank()][m.other.cur.getFile()];
			gameBoard.board[m.other.cur.getRank()][m.other.cur.getFile()] = null;
		}
		
		if (m.type==MoveType.PROMOTION) {
			Team t = gameBoard.board[m.next.getRank()][m.next.getFile()].team;
			Piece p;
			switch(m.promotion){
			case BISHOP:
				p = new Bishop(t, m.next);
				break;
			case KNIGHT:
				p = new Knight(t, m.next);
				break;
			case QUEEN:
				p = new Queen(t, m.next);
				break;
			case ROOK:
				p = new Rook(t, m.next);
				break;
			default:
				p = new Queen(t, m.next);
					
			}
			p.hasMoved = true;
			gameBoard.board[m.next.getRank()][m.next.getFile()] = p;
		}
		if(m.type == MoveType.DOUBLEMOVE) {
			Pawn p = (Pawn) gameBoard.board[m.next.getRank()][m.next.getFile()];
			p.lastMoveDouble = true;
			gameBoard.board[m.next.getRank()][m.next.getFile()] = p;
		}
		
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

