package rucksackProblem;
/**
 * umgebauter Quicksort als effizienter/relativ leicht zu implementierender Suchalgorithmus.
 * Profitvektor und Gewichtsvektor werden gleichzeitig absteigend nach Profitdichte sortiert.
 */
public class QuickSort {
	
   /**
    * Startmethode, die zum Sortieren aufgerufen wird, hier wird die Rekursion gestartet.
    *
    * @param x Profitvektor
    * @param y Gewichtsvektor
    */
   public static void sortiere(long[] x, long[] y) {
      qSort(x,y, 0, x.length-1);
   }
    
   /**
    * übersichtliche Funktion, die für die rekursiven Aufrufe verantwortlich ist.
    * 1. Nach Pivotelement sortieren
    * 2. Quicksort für das linke Array aufrufen
    * 3. Quicksort für das rechte Array aufrufen
    *
    * @param x Profitvektor
    * @param y Gewichtsvektor
    * @param links linker Zeiger
    * @param rechts rechter Zeiger
    */
   private static void qSort(long[] x, long[] y, int links, int rechts) {
      if (links < rechts) {
         int i = partition(x,y,links,rechts);
         qSort(x,y,links,i-1);
         qSort(x,y,i+1,rechts);
      }
   }
    
   /**
    * partition nimmt den Quotient der Elemente, die ganz rechts stehen als Pivotelement und
    *   sortiert den Profitvektor und den Gewichtsvektor gleichzeitig nach der Profitdichte
    *
    * @param x Profitvektor
    * @param y Gewichtsvektor
    * @param links linker Zeiger
    * @param rechts rechter Zeiger
    * 
    * @return i Index, an dem sich die Zeiger überschneiden und das Pivotelement eingefügt wurde
    */
   private static int partition(long[] x, long[] y, int links, int rechts) {
      double pivot;
      int i,j;
      long help;
      pivot = (double)x[rechts]/(double)y[rechts];               
      i     = links;
      j     = rechts-1;
      
      // Suche von links ein Element, das kleiner ist als das Pivotelement 
      //  und tausche es mit dem rechten Index
      while(i<=j) {
         if ((double)x[i]/(double)y[i] < pivot) {     
            // tausche die Elemente des Profitvektors
            help = x[i]; 
            x[i] = x[j]; 
            x[j] = help; 
            // tausche die Elemente des Gewichtsvektors
            help = y[i];
            y[i] = y[j];
            y[j] = help;
            // gehe mit dem rechten Index nach links, da das Element sortiert ist
            j--;
            // ändere nicht den linken Index, da dieses Element noch untersucht werden muss
         } else i++;          
      }
      // tausche das Element ganz rechts mit dem Element, bei dem sich die Zeiger überscheiden
      // Profitvektor
      help      = x[i];
      x[i]      = x[rechts];
      x[rechts] = help;
      // Gewichtsvektor
      help      = y[i];
      y[i]      = y[rechts];
      y[rechts] = help;
        
      return i;
   }
}