package rucksackProblem;
/**
 * Die Klasse luggage stellt Objekte zur Verfügung, die den Profit und das Gewicht 
 *   eines Gepäckstücks inhaltlich zusammenfasst.
 */
public class luggage {

	/**
	 * Profit des Gepäckstücks
	 */
	private long p;
	
	/**
	 * Gewicht des Gepäckstücks
	 */
	private long w;
	
	/**
	 * Konstruktor eines Gepäckstücks
	 * 
	 * @param p Profit
	 * @param w Gewicht
	 */
	public luggage(long p, long w){
	
		this.w = w;
		this.p = p;
	}
	
	/**
	 * get-Methode für den Profit eines Gepäckstücks
	 * 
	 * @return Profit
	 */
	public long getP() {
		return p;
	}
	
	/**
	 * get-Methode für das Gewicht eines Gepäckstücks
	 * 
	 * @return Gewicht
	 */
	public long getW() {
		return w;
	}
}