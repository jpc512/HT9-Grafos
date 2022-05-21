import java.io.IOException;
import java.util.Scanner;


public class Programa {
    
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        GraphReader r = new GraphReader();

        GraphMatrixDirected g;
        g = r.getGraph("guategrafo.txt");

        g.floyd();
        
       


        
		int opcion = 0;
        while (opcion!=6) {
            System.out.println("\nIngrese el número acorde a la acción que desea realizar:"
                            +"\n1) Mostrar grafo"
                            +"\n2) Mostrar ruta más corta"
                            +"\n3) Mostrar centro"
                            +"\n4) Agregar interrupción entre ciudades"
                            +"\n5) Indicar nueva ruta entre ciudades"
                            +"\n6) Salir\n");
            opcion = 0;
            while (opcion < 1 || opcion > 6) {
                try {
                    opcion = scan.nextInt();
                } catch (Exception e) {
                    scan.nextLine();
                    System.out.println("\n INGRESE VALOR VALIDO");
                    opcion = 0;
                }

                switch (opcion) {
                    case 1:
                		printMatrix(g, g.data);
                        break;
                        
                	case 2:
                        shortestPath(g);
                        
                		break;
                        
                	case 3:
                		String centro = g.getCentro();
                        System.out.println("EL CENTRO DEL GRAFO ES "+centro);
                		break;
                        
                	case 4:
                		agregarInt(g);
                        g.floyd();
                		break;
                    
                	case 5:
                		agregarRuta(g);
                        g.floyd();
                		break;
                        
                    case 6:
                        System.out.println("Gracias por utilizar el programa.");;
                }
		    }
        }
            
        
    }

    

    private static void agregarRuta(GraphMatrixDirected g) {
        int c1 = getCiudad(g, "origen");
        int c2 = getCiudad(g, "destino");

        Scanner scan = new Scanner(System.in);
        float label;

        System.out.println("\nAgregar distancia entre las ciudades.\n");
        try {
            label = scan.nextFloat();
        } catch (Exception e) {
            scan.nextLine();
            System.out.println("\n VALOR NO VALIDO");
            return;
        }
        
        g.removeEdge(g.ids.get(c1), g.ids.get(c2));
        g.addEdge(g.ids.get(c1), g.ids.get(c2), label);

        System.out.println("SE HA AGREGADO LA VIA DIRECTA ENTRE ESTAS CIUDADES");
    }



    private static void printMatrix(GraphMatrixDirected g, Edge[][] matrix){
        String row = "";
        for (int i = 0; i < g.ids.size(); i++) {System.out.println(i+") "+g.ids.get(i));}
        System.out.println(row);
        for (int i = 0; i < g.ids.size(); i++) {
                row = row +"   -   " + i;
            }
        System.out.println(row);
        for (int i = 0; i < g.ids.size(); i++) {
            row = i+"";
            System.out.println();
            for (Edge e : matrix[i]) {
                row = row +"  -  " +e.toString();
            }
            System.out.println(row); 
        }
    }

    private static void shortestPath(GraphMatrixDirected g) {
        
        int c1 = getCiudad(g, "origen");
        int c2 = getCiudad(g, "destino");

        if (g.costos[c1][c2].label().equals(Float.POSITIVE_INFINITY)) {
            System.out.println("NO EXISTE CAMINO ENTRE "+g.ids.get(c1)+" y "+g.ids.get(c2));
        } else {
            System.out.println("El camino mas corto es:");
            System.out.println(g.getCamino(c1, c2, g.ids.get(c1)+"--")+g.ids.get(c2));
            System.out.println("La distancia total es: "+g.costos[c1][c2]);
        }
    }

    private static void agregarInt(GraphMatrixDirected g){
        int c1 = getCiudad(g, "origen");
        int c2 = getCiudad(g, "destino");

        g.removeEdge(g.ids.get(c1), g.ids.get(c2));
        g.addEdge(g.ids.get(c1), g.ids.get(c2), Float.POSITIVE_INFINITY);

        System.out.println("SE HA INTERRUMPIDO LA VIA DIRECTA ENTRE ESTAS CIUDADES");
    }

    private static int getCiudad(GraphMatrixDirected g, String origen){
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < g.ids.size(); i++) {System.out.println(i+") "+g.ids.get(i));}
        System.out.println("\n Escoger ciudad de "+origen+" (indice)");
        int c1 = -1;
        while (c1 < 0 || c1 > g.ids.size()) {
            try {
                c1 = scan.nextInt();
            } catch (Exception e) {
                scan.nextLine();
                System.out.println("\n INGRESE VALOR VALIDO");
                c1 = -1;
            }
        }
        return c1;
    }

    
}


