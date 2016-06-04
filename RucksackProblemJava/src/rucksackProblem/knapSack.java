package rucksackProblem;
import java.util.Random;
import java.io.*; 

/**
 * Die Klasse des eigentlichen Algorithmus f�r das Rucksackproblem
 */
public class knapSack {
	
	/** 
	 * globaler Profitvektor
	 */
	long[] p;
	
	/**
	 * globaler Gewichtsvektor
	 */
	long[] w;
	
	/** 
	 * Der Vektor enth�lt Werte zwischen 0 (Gep�ckst�ck wird nicht mitgenommen) und 1 (Gep�ckst�ck 
	 *   wird mitgenommen), f�r den greedy-Algorithmus werden auch Br�che zugelassen.
	 *   
	 * Der globale Vektor x wird als �bergabe f�r die erste Rechnung in DoRec verwendet.
	 */
	double[] x;
	
	/** 
	 * Der Vektor enth�lt Werte zwischen 0 (Gep�ckst�ck wird nicht mitgenommen) und 1 (Gep�ckst�ck 
	 *   wird mitgenommen), f�r den greedy-Algorithmus werden auch Br�che zugelassen.
	 *   
	 * Der globale Vektor xTilde wird f�r die tempor�re bestm�gliche L�sung (am Anfang die des
	 *   greedy-Algorithmus �ber das gesamte Array) verwendet und am Ende auch f�r die endg�ltige L�sung genutzt.
	 */
	double[] xTilde;
	
	/**
	 * Variable, die nur zur Visualisierung, dass der Algorithmus noch l�uft, dient
	 */
	long zaehler = 0;
	
	/**
	 * Konstruktor eines Rucksack-Elements, er liest Profitvektor und Gewichtsvektor ein und 
	 *   sortiert diese.
	 * 
	 * Es wird angenommen, dass die beiden Vektoren gleich lang sind.
	 *
	 * @param p Profitvektor
	 * @param w Gewichtsvektor
	 */
	public knapSack(long[] p, long[] w){
		this.p = p;
		this.w = w;
		
		x = new double[p.length];
		xTilde = new double[p.length];
		
		QuickSort.sortiere(p, w);
	}
	
	/**
	 * Die main-Methode, die zur eigentlichen Ausf�hrung verwendet wird.
	 * 
	 * @param args Standard-�bergabeparameter f�r die main-Methode
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
		
		double[] pw = new double[n];
		
		for(int i = 0; i < n; i++){
			
			pw[i] = (double)p[i] / (double)w[i];
		}*/
		
		//Test von knapSack mit dem Beispiel aus dem Skript
		//L�sung 0110 -- funktioniert
		/*long[] tempP = {5, 12, 7, 12};
		long[] tempW = {1, 3, 2, 4};*/
		
		//L�sung 1100 -- funktioniert
		/*long[] tempP = {5, 16, 7, 12};
		long[] tempW = {1, 4, 2, 4};*/
		
		//L�sung 1001 -- funktioniert
		/*long[] tempP = {5, 4, 7, 12};
		long[] tempW = {1, 1, 2, 4};*/
		
		//L�sung 1010 -- funktioniert
		/*long[] tempP = {5, 4, 14, 12};
		long[] tempW = {1, 1, 4, 4};*/
		
		//L�sung 1110 -- funktioniert (besserer Profit mit nicht vollem Gewicht 
		/*long[] tempP = {10, 4, 3, 12};
		long[] tempW = {2, 1, 1, 4};*/
		
		//L�sung 0101 -- funktioniert (Maximalgewicht = 6)
		/*long[] tempP = {20, 12, 7, 9};
		long[] tempW = {5, 3, 2, 3};*/
		
		//L�sung 0001 -- funktioniert
		/*long[] tempP = {10, 18, 18, 20};
		long[] tempW = {2, 4, 4, 5};*/
		
		/*knapSack kS = new knapSack(tempP, tempW);
		double[] ergebnis = kS.knapSackFunc(5);*/
		
		
		//zugesendete Testdaten
		
		//Auslesen der Daten
		int counter = 0;
		long[] tempW = {0};
        long[] tempP = {0};
        long maxGewicht = 0;
		File file = new File(System.getProperty("user.dir") + "\\KnapSack.txt");

        if (!file.canRead() || !file.isFile())
            System.exit(0);

        BufferedReader in = null;
        
        try {
            in = new BufferedReader(new FileReader(file));
            String zeile = null;
            
            //Maximalgewicht auslesen
            String[] tempStringArray = in.readLine().trim().split("[\\:\\s+]");
            maxGewicht = Long.parseLong(tempStringArray[2]);
            
            in.readLine();
            
            //Anzahl der Eintr�ge z�hlen
            while ((zeile = in.readLine()) != null && zeile != "") {
                counter++;
            }
            
            in.close();
            in = new BufferedReader(new FileReader(file));
            
            in.readLine();
            in.readLine();
            tempW = new long[counter];
            tempP = new long[counter];
            counter = 0;
            
            //Gewicht- und Profitvektor bef�llen
            while ((zeile = in.readLine()) != null && zeile != "") {
            	String[] tempArray = zeile.trim().split("[\\[\\,\\]\\s+]");
            	tempW[counter] = Long.parseLong(tempArray[1]);
            	tempP[counter] = Long.parseLong(tempArray[3]);
            	counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                	System.out.println("Ein Fehler mit der Datei ist aufgetreten.");
                }
        } 
		
        //Ausf�hren vom Rucksack Algorithmus
        knapSack kS = new knapSack(tempP, tempW);
		double[] ergebnis = kS.knapSackFunc(maxGewicht);
		
		//Ausgabe
		for(int i = 0; i < ergebnis.length; i++){
			System.out.print(ergebnis[i] + " ");
		}
	}
	
	
	/**
	 * Startmethode, die f�r die L�sung des Rucksackproblems aufgerufen wird.
	 * 
	 * Hier wird nur das Maximalgewicht �bergeben, da die anderen globalen Vektoren bei 
	 *   der Initialisierung eines Rucksack-Elements festgelegt werden.
	 *   
	 * Zun�chst wird als tempor�re bestm�gliche L�sung der greedy-Algorithmus �ber das gesamte
	 *   Array ausgef�hrt. Hierbei wird xTilde als globale Variable modifiziert.
	 * Danach wird die Rekursion �ber doRec gestartet, um systematisch nach besseren L�sungen/
	 *   der optimalen L�sung zu suchen.
	 *
	 * @param m Maximalgewicht
	 * 
	 * @return xTilde Der L�sungsvektor des Rucksackproblems
	 */
	double[] knapSackFunc(long m){
		
		greedyItem newItem = greedyKnapsack(0, p.length-1, xTilde, m);
		xTilde[newItem.k] = 0;
		doRec(0,m,0,x);
		return xTilde;	
	}
	
	/**
	 * Rekursiver Algorithmus um systematisch nach einer besseren/ der optimalen L�sung zu suchen.
	 * 
	 * In doRec wird immer eine Stelle des Vektors festgelegt und anschlie�end doRec mit dem
	 *   restlichen Vektor aufgerufen. Somit wir jede effiziente M�glichkeit einmal getestet. 
	 * Der Test wird f�r die momentane Konstellation abgebrochen, falls 1. der Index end au�erhalb 
	 *   des Arrays liegt, 2. der Gesamtprofit nicht mehr gr��er werden kann, als der, der 
	 *   bisherigen bestm�glichen L�sung oder 3. das n�chste Element nicht mehr in den Rucksack passt.
	 *
	 * @param end Endindex, bis zu dem das zu pr�fende Array fest steht
	 * @param mStrich momentanes Gewicht des Rucksacks bei dieser Konstellation
	 * @param pStrich momentaner Profit der Konstellation
	 * @param localX lokales x, das momentan auf eine bessere L�sung untersucht wird
	 */
	void doRec(int end, long mStrich, double pStrich, double[] localX){
		/* localX wird geklont, da sonst mit der Referenz weitergearbeitet werden w�rde, wir
		 *   ben�tigen jedoch ein neues Array mit den alten Werten, da mit localX ein weiteres
		 *   Mal doRec aufgerufen wird.
		 */
		double[] tempX = localX.clone();
		
		//Visualisierung, das der Algorithmus noch l�uft
		zaehler++;
		if(zaehler%2000000 == 0)
			System.out.println(".");
		
		if(zaehler%20000000 == 0)
			System.out.println(zaehler + " DoRec-Aufrufe");
		
		//berechne den derzeitigen bestm�glichen Gesamtprofit (zu Beginn der von GreedyKnapsack)
		long pTilde = 0;
		for(int k = 0; k < p.length; k++){
			pTilde += p[k] * xTilde[k];
		}
		
		int j = end;
		//liegt der Index j noch im Array
		if(j <= (p.length-1)){
			
			/* f�hre greedyKnapsack mit dem noch �brigen Maximalgewicht ab dem Index j aus,
			 *   um den Teilprofit zu errechnen
			 */
			greedyItem newItem = greedyKnapsack(j, p.length-1, tempX, mStrich);
			
			/* lohnt es sich den Vektor weiter zu �berpr�fen? 
			 *   (ist bisheriger Profit + Teilprofit > Gesamtprofit der aktuellen bestm�glichen L�sung ?)
			 */
			if(pStrich + newItem.d > pTilde){
				
				//�berpr�fe den Vektor weiter, indem das j-te Element gleich 0 gesetzt wird.
				tempX[j] = 0;
				doRec(j+1, mStrich, pStrich, tempX);
				
				/* lohnt es sich den Vektor weiter zu �berpr�fen?
				 *   (passt das j-te Element noch in den Rucksack?)
				 */
				if(w[j] <= mStrich){
					
					/* �berpr�fe den Vektor weiter, indem das j-te Element gleich 1 gesetzt, 
					 *   das Maximalgewicht um das Gewicht des j-ten Element verringert und
					 *   der bisherige Profit um den Profit des j-ten Element erh�ht wird.
					 */
					tempX[j] = 1;
					doRec(j+1, mStrich-w[j], pStrich+p[j], tempX);
					
				}
			}
		}else if(pStrich > pTilde){
			
			//eine bessere L�sung wurde gefunden
			xTilde = tempX;
		}
	}
	
	/**
	 * greedy-Algorithmus f�r das Rucksackproblem
	 * Er nimmt die ersten Elemente, die bereits absteigend nach Profitdichte sortiert sind,
	 *   bis die Maximalmasse erreicht ist. Auch das letzte Element wird als Bruch mitgez�hlt.
	 *
	 * @param start Startindex, ab dem der greedy-Algorithmus durchgef�hrt werden soll
	 * @param end Endindex, bis zu dem der greedy-Algorithmus durchgef�hrt werden soll
	 * @param x Vektor, in dem die L�sung von greedyKnapsack gespeichert wird
	 * @param m Maximalmasse
	 * 
	 * @return greedyItem mit dem letzten Index, der noch in den Rucksack gepackt wird, und dem Gesamtprofit
	 */
	greedyItem greedyKnapsack(int start, int end, double[] x, long m){
		
		long c = 0;
		int k = 0;
		double d = 0;
		
		for(k = start; k <= end; k++){
			c = c + w[k];
			if(c <= m){
				x[k] = 1;
				d = d + p[k];
			} else {
				x[k] = ((double)(w[k] - (c - m)))/(double)w[k];
				d = d + p[k]*x[k];
				break;
			}
		}
		
		return new greedyItem(k, d);
	}
}