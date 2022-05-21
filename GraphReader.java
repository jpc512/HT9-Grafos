import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GraphReader {

    public GraphReader(){};
    
    public GraphMatrixDirected getGraph(String filename) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader(filename));
        String linea;
        ArrayList<String[]> lineas = new ArrayList<String[]>();
        while ((linea = bf.readLine()) != null){
            lineas.add(linea.split(" "));
        }

        ArrayList<String> ciudades = new ArrayList<String>();
        for (String[] l : lineas) {
            if (!ciudades.contains(l[0])) { ciudades.add(l[0]);}
            if (!ciudades.contains(l[1])) { ciudades.add(l[1]);}
        }
        
        GraphMatrixDirected guategrafo = new GraphMatrixDirected(ciudades.size());

        for (String[] edge : lineas) {
            guategrafo.addEdge(edge[0], edge[1], Float.valueOf(edge[2]));
        }

        return guategrafo;
    }
        
}
