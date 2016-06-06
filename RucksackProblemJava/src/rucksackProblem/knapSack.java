package rucksackProblem;
import java.util.Random;
import java.io.*; 

/**
 * Die Klasse des eigentlichen Algorithmus für das Rucksackproblem.
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
	 * Der globale Vektor x wird als Übergabe für die erste Rechnung in DoRec verwendet.
	 * <br>
	 * <br>Der Vektor enthält Werte zwischen 0 (Gepäckstück wird nicht mitgenommen) und 1 (Gepäckstück 
	 *   wird mitgenommen), für den greedy-Algorithmus werden auch Brüche zugelassen.
	 */
	double[] x;
	
	/** 
	 * Der globale Vektor xTilde wird für die temporäre bestmögliche Lösung (am Anfang die des
	 *   greedy-Algorithmus über das gesamte Array) verwendet und am Ende auch für die endgültige Lösung genutzt.
	 * <br>
	 * <br>Der Vektor enthält Werte zwischen 0 (Gepäckstück wird nicht mitgenommen) und 1 (Gepäckstück 
	 *   wird mitgenommen), für den greedy-Algorithmus werden auch Brüche zugelassen.
	 */
	double[] xTilde;
	
	/**
	 * Variable, die nur zur Visualisierung, dass der Algorithmus noch läuft, dient.
	 */
	long zaehler = 0;
	
	/**
	 * Konstruktor eines Rucksack-Elements, er liest Profitvektor und Gewichtsvektor ein und 
	 *   sortiert diese.
	 * <br>
	 * <br>Es wird angenommen, dass die beiden Vektoren gleich lang sind.
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
		
		double[] pw = new double[n];
		
		for(int i = 0; i < n; i++){
			
			pw[i] = (double)p[i] / (double)w[i];
		}*/
		
		//------------Testfälle des Rucksackproblems------------
		/*Diese kleineren Beispiele sind bereits nach Profitdichte sortiert, 
		   sodass sie leichter nachvollziehbar sind. */
		
		double[] ergebnis;
		knapSack kS;
		
		//Profitdichte(5,4,3.5,3) Lösung 0110 -- funktioniert (Skript-Beispiel)
		long[] tempP1 = {5, 12, 7, 12};
		long[] tempW1 = {1, 3, 2, 4};
		
        kS = new knapSack(tempP1, tempW1);
		ergebnis = kS.knapSackFunc(5);
		
		System.out.print("erwartete Lösung: 0110 -- ");
		for(int i = 0; i < ergebnis.length; i++){
			System.out.print(ergebnis[i] + " ");
		}
		System.out.println();
		
		//Profitdichte(5,4,3.5,3) Lösung 1100 -- funktioniert (greedy-Lösung ist bester Profit)
		long[] tempP2 = {5, 16, 7, 12};
		long[] tempW2 = {1, 4, 2, 4};
		
        kS = new knapSack(tempP2, tempW2);
		ergebnis = kS.knapSackFunc(5);
		
		System.out.print("erwartete Lösung: 1100 -- ");
		for(int i = 0; i < ergebnis.length; i++){
			System.out.print(ergebnis[i] + " ");
		}
		System.out.println();
		
		//Profitdichte(5,4,3.5,3) Lösung 1001 -- funktioniert
		long[] tempP3 = {5, 4, 7, 12};
		long[] tempW3 = {1, 1, 2, 4};

        kS = new knapSack(tempP3, tempW3);
		ergebnis = kS.knapSackFunc(5);
		
		System.out.print("erwartete Lösung: 1001 -- ");
		for(int i = 0; i < ergebnis.length; i++){
			System.out.print(ergebnis[i] + " ");
		}
		System.out.println();
		
		//Profitdichte(5,4,3.5,3) Lösung 1010 -- funktioniert
		long[] tempP4 = {5, 4, 14, 12};
		long[] tempW4 = {1, 1, 4, 4};

        kS = new knapSack(tempP4, tempW4);
		ergebnis = kS.knapSackFunc(5);
		
		System.out.print("erwartete Lösung: 1010 -- ");
		for(int i = 0; i < ergebnis.length; i++){
			System.out.print(ergebnis[i] + " ");
		}
		System.out.println();
		
		//Profitdichte(5,4,3,3) Lösung 1110 -- funktioniert (besserer Profit mit nicht vollem Gewicht)
		long[] tempP5 = {10, 4, 3, 12};
		long[] tempW5 = {2, 1, 1, 4};

        kS = new knapSack(tempP5, tempW5);
		ergebnis = kS.knapSackFunc(5);
		
		System.out.print("erwartete Lösung: 1110 -- ");
		for(int i = 0; i < ergebnis.length; i++){
			System.out.print(ergebnis[i] + " ");
		}
		System.out.println();
		
		//Profitdichte(4,4,3.5,3) Lösung 0101 -- funktioniert (Maximalgewicht = 6)
		long[] tempP6 = {20, 12, 7, 9};
		long[] tempW6 = {5, 3, 2, 3};

        kS = new knapSack(tempP6, tempW6);
		ergebnis = kS.knapSackFunc(6);
		
		System.out.print("erwartete Lösung: 0101 -- ");
		for(int i = 0; i < ergebnis.length; i++){
			System.out.print(ergebnis[i] + " ");
		}
		System.out.println();
		
		//Profitdichte(5,4.5,4.5,4)Lösung 0001 -- funktioniert
		long[] tempP7 = {10, 18, 18, 20};
		long[] tempW7 = {2, 4, 4, 5};
		

        kS = new knapSack(tempP7, tempW7);
		ergebnis = kS.knapSackFunc(5);
		
		System.out.print("erwartete Lösung: 0001 -- ");
		for(int i = 0; i < ergebnis.length; i++){
			System.out.print(ergebnis[i] + " ");
		}
		System.out.println();
		
		
		//zugesendete Testdaten (noch nicht sortiert)
		
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
            
            //Anzahl der Einträge zählen
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
            
            //Gewicht- und Profitvektor befüllen
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
		
        //Ausführen vom Rucksack Algorithmus
        kS = new knapSack(tempP, tempW);
		ergebnis = kS.knapSackFunc(maxGewicht);
		
		//Ausgabe
		for(int i = 0; i < ergebnis.length; i++){
			System.out.print(ergebnis[i] + " ");
		}
	}
	
	
	/**
	 * Startmethode, die für die Lösung des Rucksackproblems aufgerufen wird.
	 * 
	 * <br>Hier wird nur das Maximalgewicht übergeben, da die anderen globalen Vektoren bei 
	 *   der Initialisierung eines Rucksack-Elements festgelegt werden.
	 * <br>  
	 * <br>Zunächst wird als temporäre bestmögliche Lösung der greedy-Algorithmus über das gesamte
	 *   Array ausgeführt. Hierbei wird xTilde als globale Variable modifiziert.
	 * <br>Danach wird die Rekursion über doRec gestartet, um systematisch nach besseren Lösungen/
	 *   der optimalen Lösung zu suchen.
	 *
	 * @param m Maximalgewicht
	 * 
	 * @return xTilde Der Lösungsvektor des Rucksackproblems
	 */
	double[] knapSackFunc(long m){
		
		greedyItem newItem = greedyKnapsack(0, p.length-1, xTilde, m);
		xTilde[newItem.k] = 0;
		doRec(0,m,0,x);
		return xTilde;	
	}
	
	/**
	 * Rekursiver Algorithmus um systematisch nach einer besseren/ der optimalen Lösung zu suchen.
	 * <br>
	 * <br>In doRec wird immer eine Stelle des Vektors festgelegt und anschließend doRec mit dem
	 *   restlichen Vektor aufgerufen. Somit wir jede effiziente Möglichkeit einmal getestet. 
	 * <br>Der Test wird für die momentane Konstellation abgebrochen, falls 1. der Index end außerhalb 
	 *   des Arrays liegt, 2. der Gesamtprofit nicht mehr größer werden kann, als der, der 
	 *   bisherigen bestmöglichen Lösung oder 3. das nächste Element nicht mehr in den Rucksack passt.
	 *
	 * @param end Endindex, bis zu dem das zu prüfende Array fest steht
	 * @param mStrich momentanes Gewicht des Rucksacks bei dieser Konstellation
	 * @param pStrich momentaner Profit der Konstellation
	 * @param localX lokales x, das momentan auf eine bessere Lösung untersucht wird
	 */
	void doRec(int end, long mStrich, double pStrich, double[] localX){
		/* localX wird geklont, da sonst mit der Referenz weitergearbeitet werden würde, wir
		 *   benötigen jedoch ein neues Array mit den alten Werten, da mit localX ein weiteres
		 *   Mal doRec aufgerufen wird.
		 */
		double[] tempX = localX.clone();
		
		//Visualisierung, das der Algorithmus noch läuft
		zaehler++;
		if(zaehler%2000000 == 0)
			System.out.println(".");
		
		if(zaehler%20000000 == 0)
			System.out.println(zaehler + " DoRec-Aufrufe");
		
		//berechne den derzeitigen bestmöglichen Gesamtprofit (zu Beginn der von GreedyKnapsack)
		long pTilde = 0;
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
	 * Der greedy-Algorithmus für das fraktale Rucksackproblem.
	 * <br>Er nimmt die ersten Elemente, die bereits absteigend nach Profitdichte sortiert sind,
	 *   bis die Maximalmasse erreicht ist. Auch das letzte Element wird als Bruch mitgezählt.
	 *
	 * @param start Startindex, ab dem der greedy-Algorithmus durchgeführt werden soll
	 * @param end Endindex, bis zu dem der greedy-Algorithmus durchgeführt werden soll
	 * @param x Vektor, in dem die Lösung von greedyKnapsack gespeichert wird
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