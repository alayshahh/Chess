package chess;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	public final Team team;
	public final King king;
	public List<Piece> pieces;
	public List<Move> possibleMoves;
	public List<Move> validMoves;
	
	
	public Player(Team team, King king) {
		this.team = team;
		this.king = king;
	}
	
	public boolean inCheck(Board b, Move m) {
		//TODO implement method
		return false;
	}
	
	public void validateMoves(Board b) {
		for(Move m: possibleMoves) {
			if(!inCheck(b, m)) {
				validMoves.add(m);
			}
		}
		
	}
	
	public void getPieces(Board b){
		
		List<Piece> pieces = new ArrayList<>();
		
		for( Piece[] p : b.board) {
			for(Piece piece : p) {
				if(piece.getTeam()==team) {
					if(piece.type == PieceType.PAWN) {
						//set last move doubleto false
						Pawn pwn = (Pawn)piece;
						pwn.lastMoveDouble = false;
						pieces.add(pwn);
					}else {
						pieces.add(piece);
					}
				}
			}
		}
		this.pieces = pieces;
		//clear the possible moves and valid moves
		possibleMoves.clear();
		validMoves.clear();
		
	}
	
	

}
