public class Metodos {
//Burbuja con ajuste con cada arreglo de 10,100... elementos
  public static void burbuja(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n-1; i++) {
      for (int j = 0; j < n-i-1; j++) {
        if (arr[j] > arr[j+1]) {
          // Intercambio de elementos
          int temp = arr[j];
          arr[j] = arr[j+1];
          arr[j+1] = temp;
        }
      }
    }
  } 


}
