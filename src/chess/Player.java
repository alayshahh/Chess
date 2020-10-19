package chess;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alay Shah & Anshika Khare
 *
 */
public class Player {
	

	/**
	 * @return
	 */
	public Team getTeam() {
		return team;
	}

	private final Team team;
	public final King king;
	private List<Piece> pieces;
	private List<Move> possibleMoves = new ArrayList<>();
	private List<Move> validMoves = new ArrayList<>();
	
	
	public Player(Team team, King king) {
		this.team = team;
		this.king = king;
	}
	
	/**
	 * @param b
	 * @param m takes in a move
	 * @return if King is in check because of the move, then it will return true.
	 */
	public boolean inCheck(Board b, Move m) {
		//TODO implement method
		return false;
	}
	
	public void getPossibleMoves(Board b) {
		possibleMoves.clear();
		for(Piece p: pieces) {
			possibleMoves.addAll(p.allPossibleMoves(b));
		}
	}
	
	/**
	 * @param b
	 * 
	 */
	public void validateMoves(Board b) {
		for(Move m: possibleMoves) {
			if(!inCheck(b, m)) {
				validMoves.add(m);
			}
		}
		
	}
	
	/**
	 * @param b
	 */
	public void getPieces(Board b){

		List<Piece> pieces = new ArrayList<>();

		for( int i =0; i<8; i++) {
			for(int j =0; j<8; j++) {
				if(b.board[i][j]==null) {
					continue;
				}
				if(b.board[i][j].getTeam()==team) {
					if(b.board[i][j].type == PieceType.PAWN) {
						//set last move doubleto false
						Pawn pwn = (Pawn)b.board[i][j];
						pwn.lastMoveDouble = false;
						pieces.add(pwn);
					}else {
						pieces.add(b.board[i][j]);
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
