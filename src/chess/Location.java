package chess;

/**
 * @author Alay Shah & Anshika Khare
*/

public class Location {
	private int file;
	private int rank;
	
	
	/**
	 * @param loc
	 */
	public Location(String loc) {
		int f = loc.charAt(0);
		int r = loc.charAt(1);
		this.file=f-97;
		this.rank=r-49;
	}
	
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
	
	
	public boolean equals (Object o) {
		if( !(o instanceof Location)|| o==null) {
			return false;
		}
		Location loc = (Location) o;
		return getFile() == loc.getFile() && getRank() == loc.getRank();
	}
	
	
	public void locationOnBoard() {
		System.out.println(rank+ " "+ file);
	}
	
	public String toString() {
		char x = (char)(file +97);
		return x+" " + (rank+1);
	}
}
