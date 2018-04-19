package renan.victor.com.br.sliding.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Created by RananHome on 09/05/2017.
 */

// Constrói o grafo apartir de um conjunto de arestas
public class Graph {
    private static final String TAG = "Graph";
    private final Map<String, Pop> graph;
    private NavigableSet<Pop> q;
    private Pop u, v;
    private List<Pop> pops;


    public Graph(List<Enlance> enlances) {
        graph = new HashMap<>(enlances.size());

        // Encontra os vertices
        for (Enlance enlance : enlances) {
            if (!graph.containsKey(enlance.pop1))
                graph.put(enlance.pop1, new Pop(enlance.pop1));
            if (!graph.containsKey(enlance.pop2))
                graph.put(enlance.pop2, new Pop(enlance.pop2));
        }
        Log.d(TAG, "Pops Encontrados");
        // Insere os vizinhos dos vertices
        for (Enlance enlance : enlances) {
            graph.get(enlance.pop1).neighbours.put(graph.get(enlance.pop2), enlance.metrica);
            graph.get(enlance.pop2).neighbours.put(graph.get(enlance.pop1), enlance.metrica);
        }
        Log.d(TAG, "Vizinhos inseridos");
    }

    public void dijkstra(String popId) {
        if (!graph.containsKey(popId)) {
            Log.e(TAG, "O grafo não possui o pop destino " + popId);
            return;
        }
        final Pop source = graph.get(popId);
        q = new TreeSet<>();

        // "Configuras os vertices"
        for (Pop p : graph.values()) {
            p.previus = p == source ? source : null;
            p.dist = p == source ? 0 : Integer.MAX_VALUE;
            q.add(p);
        }
        Log.d(TAG, " Dijkstra - Pops configurados");
        dijkstra(q);
    }

    private void dijkstra(final NavigableSet<Pop> q) {
        while (!q.isEmpty()) {
            u = q.pollFirst(); // Pop de menor distancia
            if (u.dist == Integer.MAX_VALUE)
                break; // Podemos ignorar u (e outros vertices que sobraram) uma vez que eles estão inacessíveis

            // Verifica a distancia de cada vizinho
            for (Map.Entry<Pop, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey(); // Vizinho está nessa iteração

                final int alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) {// Menor caminho para o vizinho encontrado
                    q.remove(v);
                    v.dist = alternateDist;
                    v.previus = u;
                    q.add(v);
                }
            }
        }
    }

    public String printPath(String endName){
        if(!graph.containsKey(endName)){
            Log.d(TAG,"O grafo não possui o pop de destino"+endName);
            return "";
        }
       return graph.get(endName).printPath();
    }

    public Pop getPop(String endName){
        if(!graph.containsKey(endName)){
            Log.d(TAG,"O grafo não possui o pop de destino"+endName);
            return null;
        }
        return graph.get(endName);
    }

    public String printAllPaths(){
        String paths ="";
        for(Pop p : graph.values()){
           paths += p.printPath();
           paths +="\n";
        }
        return paths;
    }

}
