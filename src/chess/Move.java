package chess;

/**
 * @author Alay Shah & Ankisha Khare
*/

public class Move {
	Location cur;
	Location next;
	
	/**
	 * 
	 * @param cur
	 * @param next
	 * @return Move 
	 */
	
	public Move(Location cur, Location next){
		this.cur = cur;
		this.next =next;
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
