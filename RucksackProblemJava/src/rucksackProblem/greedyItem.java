package rucksackProblem;
/**
 * Klasse um die zwei Rückgabewerte von greedyKnapsack zu speichern.
 */
public class greedyItem {
	
	/**
	 * Variable wird in greedyKnapsack als k zurückgegeben und sonst als i verwendet.
	 * Index des letzten Elementes, das noch teilweise in das Array der Lösung von greedyKnapsack aufgenommen wurde.
	 */
	int k; 
	
	/** 
	 * Variable wird in greedyKnapsack als d zurückgegeben und sonst als p'' verwendet.
	 * Der gesamte Profit, der bei der Belegung von greedyKnapsack entsteht.
	 */
	double d;
	
	/**
	 * Konstruktor des greedyItem
	 *
	 * @param k auch i
	 * @param d auch p''
	 */
	public greedyItem(int k, double d){
		this.k = k;
		this.d = d;
	}
}