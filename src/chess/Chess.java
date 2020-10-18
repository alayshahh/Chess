package chess;

public class Chess {
	public static void main(String [] args) {
		Board gameBoard = new Board();
		Piece blackKing = new King();
		blackKing.curLoc = new Location ("e8");
		gameBoard.board[blackKing.curLoc.getRank()][blackKing.curLoc.getFile()] = blackKing;
		Piece whiteQueen = new Queen();
		whiteQueen.curLoc = new Location("d1");
		gameBoard.board[whiteQueen.curLoc.getRank()][whiteQueen.curLoc.getFile()] = whiteQueen;
		Piece whiteRook = new Rook();
		whiteRook.curLoc = new Location("a1");
		Piece blackRook = new Rook();
		blackRook.curLoc = new Location ("h8");
		gameBoard.board[whiteRook.curLoc.getRank()][whiteRook.curLoc.getFile()] =whiteRook;
		gameBoard.board[blackRook.curLoc.getRank()][blackRook.curLoc.getFile()] =blackRook;
		gameBoard.printBoard();
		whiteRook.curLoc.locationOnBoard();


		
	}

}
