package chess;

public abstract class Piece {
		public Location curLoc;
		public Location prevLoc;
		public PieceType type;
		public Team team;
		public abstract String toString();
		
}
