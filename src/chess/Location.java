package chess;

/**
 * @author Alay Shah & Anshika Khare
*/

public class Location {
	
	
	
	/**
	 * The column index of piece on board
	 * @see Board
	 * @see Player
	 */
	private int file;
	/**
	 * The row index of piece on board
	 * @see Board
	 * @see Player
	 */
	private int rank;
	
	
	/**
	 * Creates new Location object based on a string in the format File(letter)Rank(number)
	 * @param loc format File(letter)Rank(number)
	 */
	public Location(String loc) {
		int f = loc.charAt(0);
		int r = loc.charAt(1);
		this.file=f-97;
		this.rank=r-49;
	}
	
	/**
	 * Creates new Location object using given coordinates
	 * @param rank row of the Piece in array
	 * @param file column of Piece in array
	 */
	public Location (int rank, int file) {
		this.rank = rank;
		this.file =file;
	}
	
	
	/**
	 * @return rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * @return file
	 */
	public int getFile() {
		return file;
	}
	
	
	@Override
	public boolean equals (Object o) {
		if( !(o instanceof Location)|| o==null) {
			return false;
		}
		Location loc = (Location) o;
		return getFile() == loc.getFile() && getRank() == loc.getRank();
	}
	
	
	/**
	 * Returns the i,j coordinates that the Location references
	 * 
	 */
	public void locationOnBoard() {
		System.out.println(rank+ " "+ file);
	}
	
	@Override
	public String toString() {
		char x = (char)(file +97);
		return x+" " + (rank+1);
	}
}
