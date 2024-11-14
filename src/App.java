import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    static List<Integer> numerosAleatorios = new ArrayList<>();
    static int[] tamanos = {10, 100, 1000, 5000, 10000, 30000};
    static boolean arregloGenerado = false; // Bandera para verificar si el arreglo ha sido generado
    static boolean arregloOrdenado = false; // Bandera para verificar si el arreglo ha sido ordenado

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Generar arreglos aleatorios con diferente tamaño");
            System.out.println("2. Ordenar los arreglos");
            System.out.println("3. Buscar valores en posiciones específicas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    generarArreglos();
                    break;
                case 2:
                    if (!arregloGenerado) {
                        System.out.println("Primero debe generar los arreglos antes de ordenarlos.");
                    } else {
                        ordenarArreglos();
                    }
                    break;
                case 3:
                    if (!arregloGenerado) {
                        System.out.println("Primero debe generar los arreglos antes de realizar una búsqueda.");
                    } else if (!arregloOrdenado) {
                        System.out.println("Primero debe ordenar los arreglos antes de realizar una búsqueda.");
                    } else {
                        buscarValoresPorPosicion();
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 4);

        scanner.close();
    }

    // Método para generar los arreglos aleatorios
    public static void generarArreglos() {
        Random random = new Random();
        numerosAleatorios.clear();

        for (int tamano : tamanos) {
            while (numerosAleatorios.size() < tamano) {
                numerosAleatorios.add(random.nextInt(1000));
            }
            System.out.println("Generado arreglo de tamaño: " + tamano);
        }
        arregloGenerado = true; // Se establece que el arreglo ha sido generado
        arregloOrdenado = false; // Aseguramos que no está ordenado
    }

    // Método para ordenar los arreglos y medir el tiempo
    public static void ordenarArreglos() {
        // Método Burbuja
        System.out.println("\nMétodo Burbuja con Ajustes");
        for (int tamano : tamanos) {
            List<Integer> copiaLista = new ArrayList<>(numerosAleatorios.subList(0, tamano));

            long startTime = System.nanoTime();
            ordenamientoBurbuja(copiaLista);
            long endTime = System.nanoTime();

            System.out.println("Con " + tamano + " valores el tiempo de ejecución es de " + (endTime - startTime) / 1e9 + " seg.");
        }

        // Método Selección
        System.out.println("\nMétodo Selección");
        for (int tamano : tamanos) {
            List<Integer> copiaLista = new ArrayList<>(numerosAleatorios.subList(0, tamano));

            long startTime = System.nanoTime();
            ordenamientoSeleccion(copiaLista);
            long endTime = System.nanoTime();

            System.out.println("Con " + tamano + " valores el tiempo de ejecución es de " + (endTime - startTime) / 1e9 + " seg.");
        }

        // Método Inserción
        System.out.println("\nMétodo Inserción");
        for (int tamano : tamanos) {
            List<Integer> copiaLista = new ArrayList<>(numerosAleatorios.subList(0, tamano));

            long startTime = System.nanoTime();
            ordenamientoInsercion(copiaLista);
            long endTime = System.nanoTime();

            System.out.println("Con " + tamano + " valores el tiempo de ejecución es de " + (endTime - startTime) / 1e9 + " seg.");
        }

        // Se marca el arreglo como ordenado
        arregloOrdenado = true;
    }

    // Métodos de ordenación

    public static void ordenamientoBurbuja(List<Integer> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j) > lista.get(j + 1)) {
                    Collections.swap(lista, j, j + 1);
                }
            }
        }
    }

    public static void ordenamientoSeleccion(List<Integer> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (lista.get(j) < lista.get(minIndex)) {
                    minIndex = j;
                }
            }
            Collections.swap(lista, i, minIndex);
        }
    }

    public static void ordenamientoInsercion(List<Integer> lista) {
        int n = lista.size();
        for (int i = 1; i < n; i++) {
            int key = lista.get(i);
            int j = i - 1;
            while (j >= 0 && lista.get(j) > key) {
                lista.set(j + 1, lista.get(j));
                j = j - 1;
            }
            lista.set(j + 1, key);
        }
    }

    // Método para buscar valores en posiciones específicas
    public static void buscarValoresPorPosicion() {
        // Arreglos de las posiciones a buscar
        int[] posiciones = {9, 98, 957, 4000, 9876, 29475};

        // Realizamos la búsqueda para cada tamaño de arreglo
        for (int i = 0; i < tamanos.length; i++) {
            int tamano = tamanos[i];

            // Hacemos una copia para no modificar el arreglo original
            List<Integer> copiaLista = new ArrayList<>(numerosAleatorios.subList(0, tamano));
            Collections.sort(copiaLista); // Aseguramos que esté ordenado para la búsqueda binaria

            // Verificar si la posición es válida para el tamaño actual
            if (i < posiciones.length && posiciones[i] < tamano) {
                int posicion = posiciones[i];

                // Búsqueda binaria normal (iterativa)
                long startTimeNormal = System.nanoTime();
                int resultadoNormal = busquedaBinariaNormal(copiaLista, copiaLista.get(posicion));
                long endTimeNormal = System.nanoTime();
                if (resultadoNormal != -1) {
                    System.out.println("Búsqueda Binaria Normal:");
                    System.out.println("Arreglo " + tamano + " posición " + posicion + ": " + copiaLista.get(posicion) + " tiempo que se demoró: " + (endTimeNormal - startTimeNormal) / 1e9 + " seg.");
                }

                // Búsqueda binaria recursiva
                long startTimeRecursiva = System.nanoTime();
                int resultadoRecursivo = busquedaBinariaRecursiva(copiaLista, copiaLista.get(posicion), 0, copiaLista.size() - 1);
                long endTimeRecursiva = System.nanoTime();
                if (resultadoRecursivo != -1) {
                    System.out.println("Búsqueda Binaria Recursiva:");
                    System.out.println("Arreglo " + tamano + " posición " + posicion + ": " + copiaLista.get(posicion) + " tiempo que se demoró: " + (endTimeRecursiva - startTimeRecursiva) / 1e9 + " seg.");
                }
            }
        }
    }

    // Búsqueda binaria normal (iterativa)
    public static int busquedaBinariaNormal(List<Integer> lista, int valor) {
        int inicio = 0;
        int fin = lista.size() - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;

            if (lista.get(medio) == valor) {
                return medio;
            }
            if (lista.get(medio) > valor) {
                fin = medio - 1;
            } else {
                inicio = medio + 1;
            }
        }
        return -1;
    }

    // Búsqueda binaria recursiva
    public static int busquedaBinariaRecursiva(List<Integer> lista, int valor, int inicio, int fin) {
        if (inicio > fin) {
            return -1;
        }
        int medio = inicio + (fin - inicio) / 2;
        if (lista.get(medio) == valor) {
            return medio;
        }
        if (lista.get(medio) > valor) {
            return busquedaBinariaRecursiva(lista, valor, inicio, medio - 1);
        } else {
            return busquedaBinariaRecursiva(lista, valor, medio + 1, fin);
        }
    }
}
