package rucksackProblem;
/**
 * Die Klasse luggage stellt Objekte zur Verf�gung, die den Profit und das Gewicht 
 *   eines Gep�ckst�cks inhaltlich zusammenfasst.
 */
public class luggage {

	/**
	 * Profit des Gep�ckst�cks
	 */
	private long p;
	
	/**
	 * Gewicht des Gep�ckst�cks
	 */
	private long w;
	
	/**
	 * Konstruktor eines Gep�ckst�cks
	 * 
	 * @param p Profit
	 * @param w Gewicht
	 */
	public luggage(long p, long w){
	
		this.w = w;
		this.p = p;
	}
	
	/**
	 * get-Methode f�r den Profit eines Gep�ckst�cks
	 * 
	 * @return Profit
	 */
	public long getP() {
		return p;
	}
	
	/**
	 * get-Methode f�r das Gewicht eines Gep�ckst�cks
	 * 
	 * @return Gewicht
	 */
	public long getW() {
		return w;
	}
}