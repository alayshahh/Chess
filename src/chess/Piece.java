package chess;
import java.util.*;

/**
 * @author Alay Shah & Anshika Khare

*/

public abstract class Piece {
	
	public Location curLoc;
	//public Location prevLoc;
	public PieceType type;
	public final Team team;
	public boolean hasMoved;
	
	/**
	 * @param type PieceType that is set, done through the constructor
	 * @param team Team of piece set by constructor
	 * @param curLoc CUrrent Location of Piece
	 * @see Location
	 * @see PieceType
	 */
	public Piece(PieceType type, Team team, Location curLoc) {
		this.type = type;
		this.team = team;
		this.curLoc = curLoc;
		hasMoved = false;
		
	}
	


	/**
	 * @return Current Location field of Piece
	 * @see Location
	 */
	public Location getCurLoc() {
		return curLoc;
	}

	/**
	 * @return the row index of Piece on board
	 * @see Location
	 * @see Board
	 */
	public int getRank() {
		return getCurLoc().getRank();
	}
	/**
	 * @return the column index of Piece on board
	 * @see Location
	 * @see Board
	 */
	public int getFile(){
		return getCurLoc().getFile();
	}

	/**
	 * @return Type of Piece
	 */
	public PieceType getType() {
		return type;
	}

	/**
	 * @return Team of Piece (BLACK or WHITE)
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @param gameBoard
	 * @return List of Moves this will be all possible moves that the piece can make, will not check if it is invalid, i.e doesn't matter if the king will be in check because of this move.
	 * @see Move
	 * @see Board
	 */
	public abstract List<Move> allPossibleMoves(Board gameBoard);
	
	@Override
	public abstract Piece clone();

	@Override
	public abstract String toString();

}
