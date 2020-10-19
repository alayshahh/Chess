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
	
	public Piece(PieceType type, Team team, Location curLoc) {
		this.type = type;
		this.team = team;
		this.curLoc = curLoc;
		hasMoved = false;
		
	}
	


	public Location getCurLoc() {
		return curLoc;
	}

	public void setCurLoc(Location curLoc) {
		this.curLoc = curLoc;
	}

	public PieceType getType() {
		return type;
	}

	public Team getTeam() {
		return team;
	}

	public abstract List<Move> allPossibleMoves(Board gameBoard);


	public abstract String toString();

}
