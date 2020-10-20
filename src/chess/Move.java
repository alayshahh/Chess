package chess;

/**
 * @author Alay Shah & Ankisha Khare
*/

public class Move {
	Location cur;
	Location next;
	PieceType promotion;
	MoveType type;
	Move other; // this will be for castling or for enPassant Capture
	
	/**
	 * 
	 * @param cur
	 * @param next
	 * @return Move 
	 */
	

	public Move(Location cur, Location next){
		this.cur = cur;
		this.next =next;
		type = MoveType.NORMAL;
	}
	public Move(Location cur, Location next, PieceType promotion) {
		this(cur,next);
		this.promotion = promotion;
		type = MoveType.PROMOTION;
	}
	public Move(Location cur, Location next, Move rook) {
		this(cur,next);
		this.other = rook;
		type = MoveType.CASTLING;
	}
	public Move(Location cur, Location next, Location enPassant){
		this(cur, next);
		other  = new Move (enPassant, null);
		type = MoveType.ENPASSANT;
	}
	
	@Override
	public boolean equals (Object o) {
		if( !(o instanceof Move) || o ==null) {
			return false;
		}
		Move m = (Move) o;
		return cur.equals(m.cur) && next.equals(m.next);
		
	}
}
