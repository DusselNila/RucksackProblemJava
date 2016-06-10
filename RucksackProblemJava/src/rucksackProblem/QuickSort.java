package rucksackProblem;
/**
 * Die Klasse enth�lt einen, f�r das Rucksackproblem umgebauten, Quicksort als effizienter/relativ 
 *   leicht zu implementierender Suchalgorithmus.
 * <br>Das Array mit Gep�ckst�cken wird <b>absteigend nach Profitdichte</b> sortiert.
 */
public class QuickSort {
	
   /**
    * Startmethode, die zum Sortieren aufgerufen wird, hier wird die Rekursion gestartet.
    *
    * @param items Gep�ckst�cke, die sortiert werden sollen
    */
   public static void sortiere(luggage[] items) {
      qSort(items, 0, items.length-1);
   }
    
   /**
    * Die Funktion dient einem �bersichtlichen rekursiven Aufruf.
    * <br>1. Nach Pivotelement sortieren
    * <br>2. Quicksort f�r das linke Array aufrufen
    * <br>3. Quicksort f�r das rechte Array aufrufen
    *
    * @param items Gep�ckst�cke, die sortiert werden
    * @param links linker Zeiger
    * @param rechts rechter Zeiger
    */
   private static void qSort(luggage[] items, int links, int rechts) {
      if (links < rechts) {
         int i = partition(items,links,rechts);
         qSort(items,links,i-1);
         qSort(items,i+1,rechts);
      }
   }
    
   /**
    * Die Funktion nimmt den Quotient aus Profit und Gewicht des am weitesten rechts stehenden 
    *   Gep�ckst�cks und sortiert die Gep�ckst�cke nach diesem Quotienten.
    *
    * @param items Gep�ckst�cke, die sortiert werden
    * @param links linker Zeiger
    * @param rechts rechter Zeiger
    * 
    * @return i Index, an dem sich die Zeiger �berschneiden und das Pivotelement eingef�gt wurde
    */
   private static int partition(luggage[] items, int links, int rechts) {
      double pivot;
      int i,j;
      luggage help;
      pivot = (double)items[rechts].getP()/(double)items[rechts].getW();               
      i     = links;
      j     = rechts-1;
      
      // Suche von links ein Element, das kleiner ist als das Pivotelement 
      //  und tausche es mit dem rechten Index
      while(i<=j) {
         if ((double)items[i].getP()/(double)items[i].getW() < pivot) {     
            // tausche die Elemente des Profitvektors
            help = items[i]; 
            items[i] = items[j]; 
            items[j] = help; 

            // gehe mit dem rechten Index nach links, da das Element sortiert ist
            j--;
            // �ndere nicht den linken Index, da dieses Element noch untersucht werden muss
         } else i++;          
      }
      // tausche das Element ganz rechts mit dem Element, bei dem sich die Zeiger �berscheiden
      // Profitvektor
      help      = items[i];
      items[i]      = items[rechts];
      items[rechts] = help;
     
        
      return i;
   }
}