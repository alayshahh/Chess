package chess;

/**
 *  @author Alay Shah
 * @author Anshika Khare
*/

public class Move {
	
	
	
	/**
	 * Current Location of the Piece on the board
	 * @see Piece
	 * @see Board
	 * @see Location
	 */
	Location cur;
	
	
	/**
	 * Desired next location of piece on Board
	 * @see Piece
	 * @see Board
	 * @see Location
	 */
	Location next;
	
	
	/**
	 * Promotion type of pawn, if applicable.
	 * @see Pawn
	 * @see PieceType
	 */
	PieceType promotion;
	
	
	/**
	 * The type of Move the move is
	 * @see MoveType
	 */
	MoveType type;
	
	
	/**
	 * Reserved for en Passant and Castling moves where a second location or move is associated with the move
	 * @see King
	 * @see Pawn
	 */
	Move other; // this will be for castling or for enPassant Capture
	
	/**
	 * Creates new Move object , taking in the current location and the next location of the object. 
	 * @param cur Current Location of piece
	 * @param next Intended next Location of Piece.
	 * @see Location
	 */
	public Move(Location cur, Location next){
		this.cur = cur;
		this.next =next;
		type = MoveType.NORMAL;
	}
	/**
	 * Creates new Move object , taking in the current location and the next location of the object.  This is for pawn promotion, where the promotion piece is specified.
	 * @param cur Current Location of piece
	 * @param next Intended next Location of Piece.
	 * @param promotion PieceType for intended promotion
	 * @see Location
	 */
	public Move(Location cur, Location next, PieceType promotion) {
		this(cur,next);
		this.promotion = promotion;
		type = MoveType.PROMOTION;
	}
	
	/**
	 * Creates new Move object , taking in the current location and the next location of the object.  This is reserved for castling, where the rook's move is also specified.
	 * @param cur Current Location of piece
	 * @param next Intended next Location of Piece.
	 * @param rook Move for Rook
	 * @see Location
	 */
	public Move(Location cur, Location next, Move rook) {
		this(cur,next);
		this.other = rook;
		type = MoveType.CASTLING;
	}
	
	/**
	 * Creates new Move object , taking in the current location and the next location of the object. This is for en passant moves, here the location of the captured pawn is specified.
	 * @param cur Current Location of piece
	 * @param next Intended next Location of Piece.
	 * @param enPassant Location of captured piece
	 * @see Location
	 */
	public Move(Location cur, Location next, Location enPassant){
		this(cur, next);
		other  = new Move (enPassant, null);
		type = MoveType.ENPASSANT;
	}
	
	/**
	 * Creates new Move object , taking in the current location and the next location of the object. This allows a change to the move type, reserved for specified if the mvoe was a double move (pawn's first move).
	 * @param cur Current Location of piece
	 * @param next Intended next Location of Piece.
	 * @param type MoveType specified
	 * @see Location
	 */
	public Move(Location cur, Location next, MoveType type) {
		this(cur, next);
		this.type = type;
	}
	
	@Override
	public boolean equals (Object o) {
		if( !(o instanceof Move) || o ==null) {
			return false;
		}
		Move m = (Move) o;
		return cur.equals(m.cur) && next.equals(m.next);
		
	}
	@Override
	public String toString() {
		return type +" "+ cur+ " | " + next;
	}
}
