package chess;

/**
 *  @author Alay Shah
 * @author Anshika Khare
 *
 */
public enum MoveType {
	/**
	 * Normal Move
	 */
	NORMAL,
	/**
	 * En Passant Move
	 * @see Pawn
	 */
	ENPASSANT, 
	
	/**
	 * Castling Move 
	 * @see King
	 */
	CASTLING,
	
	/**
	 * Promotion move
	 * @see Pawn
	 */
	PROMOTION,
	
	/**
	 * Double move 
	 * @see Pawn
	 */
	DOUBLEMOVE
}
