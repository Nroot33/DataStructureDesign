public class Deap {
   int[] deap;
   int[] olddeap;
   int n = 0; // deap에 저장되는 원소의 개수, 루트는 비워져있음.

   public Deap(int maxSize) {
      deap = new int[maxSize];
   }

   private void increaseheap(int i) {
      olddeap = deap;
      deap = new int[i];
      System.arraycopy(olddeap, 0, deap, 0, olddeap.length);
   }

   private boolean inMinHeap(int i) {
      int tmp = i;
      while (tmp > 2) {
         tmp = (tmp - 1) / 2;
      }
      if (tmp == 2)
         return true;
      return false;
   }

   private int maxPartner(int i) {
      int tmp = i;
      int distance = 1;
      while (tmp > 2) {
         tmp = (tmp - 1) / 2;
         distance *= 2;
      }
      if ((i + distance) > n)
         return (i + distance - 1) / 2;
      return i + distance;
   }

   private int minPartner(int i) {

      int tmp = i;
      int distance = 1;
      while (tmp > 2) {
         tmp = (tmp - 1) / 2;
         distance *= 2;
      }
      return i - distance;
   }

   private void minInsert(int at, int key) {
      int parent;
      while ((parent = (at - 1) / 2) != 0 && key < deap[parent]) {
         deap[at] = deap[parent];
         at = parent;
         parent = (at - 1) / 2;
      }
      deap[at] = key;
   }

   private void maxInsert(int at, int key) {
      int parent;
      while ((parent = (at - 1) / 2) != 0 && key > deap[parent]) {
         deap[at] = deap[parent];
         at = parent;
         parent = (at - 1) / 2;
      }
      deap[at] = key;
   }

   public int deleteMax() {
       int i, j;
       int heapMax;
       if (n >= 2) { 
    	   heapMax = deap[2];
       } else {
           n--;
           return deap[1];
       }
       int tmp = deap[n--];
       
       for (i = 2; 2*i <= n; deap[i] = deap[j], i = j) {
           j = i * 2+1;
           if (j+1 <= n) {
               if (deap[j] < deap[j+1]) {
                   j++;
               }
           }
       }
       j = minPartner(i);
       int max = j;
       if (2*j +1<= n) {
    	   max = 2*j+1;
           if (((2*j + 2) <= n) && (deap[2*j+1] < deap[2*j+2])) {
        	   max++;
           }
       }
       if (tmp < deap[max]) {
           deap[i] = deap[max];
           minInsert(max, tmp);
       } else {
           maxInsert(i, tmp);
       }
       return heapMax;
   }

   public int deleteMin() {
      int heapMin = deap[1];
      int tmp = deap[n--];
      int i, j;
      for (i = 1; 2 * i <= n; deap[i] = deap[j], i = j) {
         j = 2 * i + 1;
         if (j + 1 <= n && deap[j] > deap[j + 1])
            j++;
      }
      j = maxPartner(i);
      if (tmp > deap[j]) {
         deap[i] = deap[j];
         maxInsert(j, tmp);
      } else
         minInsert(i, tmp);
      return heapMin;
   }

   // x를 Deap에 삽입
   public void insert(int x) {
      if (n == deap.length - 1) {
         System.out.println("The heap is full , The heap size is doubled ");
         increaseheap(deap.length*2);
      }
      n++;
      if (n == 1) {
         deap[1] = x;
         return;
      }
      if (inMinHeap(n)) {
         int i = minPartner(n);
         if (x < deap[i]) {
            deap[n] = deap[i];
            minInsert(i, x);
         } else {
            maxInsert(n, x);
         }
      } else {
         int i = maxPartner(n);
         if (x > deap[i]) {
            deap[n] = deap[i];
            maxInsert(i, x);
         } else {
            minInsert(n, x);
         }
      }
   }

   public void print() {
      int levelNum = 2;
      int thisLevel = 0;
      int gap = 8;
      for (int i = 1; i <= n; i++) {
         for (int j = 0; j < gap - 1; j++) {
            System.out.print(" ");
         }
         if (thisLevel != 0) {
            for (int j = 0; j < gap - 1; j++) {
               System.out.print(" ");
            }
         }
         if (Integer.toString(deap[i]).length() == 1) {
            System.out.print(" ");
         }
         System.out.print(deap[i]);
         thisLevel++;
         if (thisLevel == levelNum) {
            System.out.println();
            thisLevel = 0;
            levelNum *= 2;
            gap /= 2;
         }
      }
      System.out.println();
      if (thisLevel != 0) {
         System.out.println();
      }
   }

   public static void main(String[] argv) {
      Deap a = new Deap(10);
      int i = 0;
      int[] data = { 4, 65, 8, 9, 48, 55, 10, 19, 20, 30, 15, 25, 50 };
      for (i = 0; i < 9; i++) {
         a.insert(data[i]);
      }

      System.out.println("initial Deap----------------------------");
      a.print();

      for (; i < data.length; i++) {
         a.insert(data[i]);
      }

      System.out.println("enlarged Deap----------------------------");
      a.print();
      
      System.out.println("delete Min----------------------------");
      a.deleteMin();
      a.print();
      
      System.out.println("delete Min----------------------------");
      a.deleteMin();
      a.print();
      
      System.out.println("delete Min----------------------------");
      a.deleteMin();
      a.print();
      
      System.out.println("delete Max----------------------------");
      a.deleteMax();
      a.print();
      
      System.out.println("delete Max----------------------------");
      a.deleteMax();
      a.print();
      
      System.out.println("delete Max----------------------------");
      a.deleteMax();
      a.print();
   }
}