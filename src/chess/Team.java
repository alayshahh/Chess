package chess;


/**
 * @author Alay Shah
 * @author Anshika Khare
*/

public enum Team {
	/**
	 * White Team
	 */
	WHITE("White"), 
	/**
	 * Black Team
	 */
	BLACK("Black");
	private String teamName;
	
	
	/**
	 * @param name Gives the value a teamName attribute
	 */
	private Team(String teamName) {
		this.teamName = teamName;
	}
	
	public String toString() {
		return teamName;
	}
}
