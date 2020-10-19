package chess;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alay Shah & Anshika Khare
 *
 */
public class Player {
	
	public List<Piece> getPieces() {
		return pieces;
	}

	public List<Move> getPossibleMoves() {
		return possibleMoves;
	}

	/**
	 * @return
	 */
	public List<Move> getValidMoves() {
		return validMoves;
	}

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
	 * @param m
	 * @return if King is in check because of the move
	 */
	public boolean inCheck(Board b, Move m) {
		//TODO implement method
		return false;
	}
	
	/**
	 * @param b
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
	public void getPiecesFromBoard(Board b){

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
