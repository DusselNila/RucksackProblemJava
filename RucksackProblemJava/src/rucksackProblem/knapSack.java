package rucksackProblem;
import java.util.Random;

/**
 * Die Klasse des eigentlichen Algorithmus für das Rucksackproblem
 */
public class knapSack {
	
	/** 
	 * globaler Profitvektor
	 */
	int[] p;
	
	/**
	 * globaler Gewichtsvektor
	 */
	int[] w;
	
	/** 
	 * Der Vektor enthält Werte zwischen 0 (Gepäckstück wird nicht mitgenommen) und 1 (Gepäckstück 
	 *   wird mitgenommen), für den greedy-Algorithmus werden auch Brüche zugelassen.
	 *   
	 * Der globale Vektor x wird als Übergabe für die erste Rechnung in DoRec verwendet.
	 */
	float[] x;
	
	/** 
	 * Der Vektor enthält Werte zwischen 0 (Gepäckstück wird nicht mitgenommen) und 1 (Gepäckstück 
	 *   wird mitgenommen), für den greedy-Algorithmus werden auch Brüche zugelassen.
	 *   
	 * Der globale Vektor xTilde wird für die temporäre bestmögliche Lösung (am Anfang die des
	 *   greedy-Algorithmus über das gesamte Array) verwendet und am Ende auch für die endgültige Lösung genutzt.
	 */
	float[] xTilde;
	
	/**
	 * Konstruktor eines Rucksack-Elements, er liest Profitvektor und Gewichtsvektor ein und 
	 *   sortiert diese.
	 * 
	 * Es wird angenommen, dass die beiden Vektoren gleich lang sind.
	 *
	 * @param p Profitvektor
	 * @param w Gewichtsvektor
	 */
	public knapSack(int[] p, int[] w){
		this.p = p;
		this.w = w;
		
		x = new float[p.length];
		xTilde = new float[p.length];
		
		QuickSort.sortiere(p, w);
	}
	
	/**
	 * Die main-Methode, die zur eigentlichen Ausführung verwendet wird.
	 * 
	 * @param args Standard-Übergabeparameter für die main-Methode
	 */
	public static void main(String[] args) {
		
		//Test QuickSort
		/*Random r = new Random();
		
		for(int i = 0; i < kS.n; i++){
			p[i] = r.nextInt(300);
			if(p[i] == 0){
				p[i] = 1;
			}
			w[i] = r.nextInt(300);
			if(w[i] == 0){
				w[i] = 1;
			}
		}
		QuickSort.sortiere(p,w);
		
		float[] pw = new float[n];
		
		for(int i = 0; i < n; i++){
			
			pw[i] = (float)p[i] / (float)w[i];
		}*/
		
		//Test von knapSack mit dem Beispiel aus dem Skript
		int[] tempP = {5, 12, 7, 12};
		int[] tempW = {1, 3, 2, 4};
		
		knapSack kS = new knapSack(tempP, tempW);
		float[] ergebnis = kS.knapSackFunc(5);
		
		//Ausgabe
		for(int i = 0; i < ergebnis.length; i++){
			System.out.print(ergebnis[i] + " ");
		}
	}
	
	
	/**
	 * Startmethode, die für die Lösung des Rucksackproblems aufgerufen wird.
	 * 
	 * Hier wird nur das Maximalgewicht übergeben, da die anderen globalen Vektoren bei 
	 *   der Initialisierung eines Rucksack-Elements festgelegt werden.
	 *   
	 * Zunächst wird als temporäre bestmögliche Lösung der greedy-Algorithmus über das gesamte
	 *   Array ausgeführt. Hierbei wird xTilde als globale Variable modifiziert.
	 * Danach wird die Rekursion über doRec gestartet, um systematisch nach besseren Lösungen/
	 *   der optimalen Lösung zu suchen.
	 *
	 * @param m Maximalgewicht
	 * 
	 * @return xTilde Der Lösungsvektor des Rucksackproblems
	 */
	float[] knapSackFunc(int m){
		
		greedyItem newItem = greedyKnapsack(0, p.length-1, xTilde, m);
		xTilde[newItem.k] = 0;
		doRec(0,m,0,x);
		return xTilde;	
	}
	
	/**
	 * Rekursiver Algorithmus um systematisch nach einer besseren/ der optimalen Lösung zu suchen.
	 * 
	 * In doRec wird immer eine Stelle des Vektors festgelegt und anschließend doRec mit dem
	 *   restlichen Vektor aufgerufen. Somit wir jede effiziente Möglichkeit einmal getestet. 
	 * Der Test wird für die momentane Konstellation abgebrochen, falls 1. der Index end außerhalb 
	 *   des Arrays liegt, 2. der Gesamtprofit nicht mehr größer werden kann, als der, der 
	 *   bisherigen bestmöglichen Lösung oder 3. das nächste Element nicht mehr in den Rucksack passt.
	 *
	 * @param end Endindex, bis zu dem das zu prüfende Array fest steht
	 * @param mStrich momentanes Gewicht des Rucksacks bei dieser Konstellation
	 * @param pStrich momentaner Profit der Konstellation
	 * @param localX lokales x, das momentan auf eine bessere Lösung untersucht wird
	 */
	void doRec(int end, int mStrich, float pStrich, float[] localX){
		/* localX wird geklont, da sonst mit der Referenz weitergearbeitet werden würde, wir
		 *   benötigen jedoch ein neues Array mit den alten Werten, da mit localX ein weiteres
		 *   Mal doRec aufgerufen wird.
		 */
		float[] tempX = localX.clone();
		
		//berechne den derzeitigen bestmöglichen Gesamtprofit (zu Beginn der von GreedyKnapsack)
		int pTilde = 0;
		for(int k = 0; k < p.length; k++){
			pTilde += p[k] * xTilde[k];
		}
		
		int j = end;
		//liegt der Index j noch im Array
		if(j <= (p.length-1)){
			
			/* führe greedyKnapsack mit dem noch übrigen Maximalgewicht ab dem Index j aus,
			 *   um den Teilprofit zu errechnen
			 */
			greedyItem newItem = greedyKnapsack(j, p.length-1, tempX, mStrich);
			
			/* lohnt es sich den Vektor weiter zu überprüfen? 
			 *   (ist bisheriger Profit + Teilprofit > Gesamtprofit der aktuellen bestmöglichen Lösung ?)
			 */
			if(pStrich + newItem.d > pTilde){
				
				//überprüfe den Vektor weiter, indem das j-te Element gleich 0 gesetzt wird.
				tempX[j] = 0;
				doRec(j+1, mStrich, pStrich, tempX);
				
				/* lohnt es sich den Vektor weiter zu überprüfen?
				 *   (passt das j-te Element noch in den Rucksack?)
				 */
				if(w[j] <= mStrich){
					
					/* überprüfe den Vektor weiter, indem das j-te Element gleich 1 gesetzt, 
					 *   das Maximalgewicht um das Gewicht des j-ten Element verringert und
					 *   der bisherige Profit um den Profit des j-ten Element erhöht wird.
					 */
					tempX[j] = 1;
					doRec(j+1, mStrich-w[j], pStrich+p[j], tempX);
					
				}
			}
		}else if(pStrich > pTilde){
			
			//eine bessere Lösung wurde gefunden
			xTilde = tempX;
		}
	}
	
	/**
	 * greedy-Algorithmus für das Rucksackproblem
	 * Er nimmt die ersten Elemente, die bereits absteigend nach Profitdichte sortiert sind,
	 *   bis die Maximalmasse erreicht ist. Auch das letzte Element wird als Bruch mitgezählt.
	 *
	 * @param start Startindex, ab dem der greedy-Algorithmus durchgeführt werden soll
	 * @param end Endindex, bis zu dem der greedy-Algorithmus durchgeführt werden soll
	 * @param x Vektor, in dem die Lösung von greedyKnapsack gespeichert wird
	 * @param m Maximalmasse
	 * 
	 * @return greedyItem mit dem letzten Index, der noch in den Rucksack gepackt wird, und dem Gesamtprofit
	 */
	greedyItem greedyKnapsack(int start, int end, float[] x, int m){
		
		int c = 0;
		int k = 0;
		float d = 0;
		
		for(k = start; k <= end; k++){
			c = c + w[k];
			if(c <= m){
				x[k] = 1;
				d = d + p[k];
			} else {
				x[k] = ((float)(w[k] - (c - m)))/(float)w[k];
				d = d + p[k]*x[k];
				break;
			}
		}
		
		return new greedyItem(k, d);
	}
}