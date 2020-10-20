package chess;

/**
 * @author Alay Shah & Anshika Khare
*/

public class Chess {
	
	private static Board gameBoard;
	private static Player white;
	private static Player black;
	
	public static void main(String [] args) {
		initGame();
		gameBoard.printBoard();
		boolean whiteTurn = true;
		boolean gameOn = true;
		
		//implement rest
	}
	
	private static void initGame() {
		gameBoard  = new Board();
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
