package renan.victor.com.br.sliding.controller;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import renan.victor.com.br.sliding.dao.EnlaceDAO;
import renan.victor.com.br.sliding.dao.PopDAO;
import renan.victor.com.br.sliding.model.Enlance;
import renan.victor.com.br.sliding.model.Graph;
import renan.victor.com.br.sliding.model.MetricaType;
import renan.victor.com.br.sliding.model.Pop;


/**
 * Created by RananHome on 08/05/2017.
 */

public class GraphController {

    private Context context;


    private PopDAO popDao;
    private Pop p1, p2, aux, aux2;
    private EnlaceDAO enlaceDao;
    private Graph g;
    private List<Enlance> graph;
    private List<Pop> pops;


    public GraphController(Context context) {
        this.context = context;
        popDao = new PopDAO(context);
        enlaceDao = new EnlaceDAO(context);
    }

    public List<Pop> getPops() {
        return popDao.selectAll();
    }

    public Pop getPop(String id) {
        return popDao.select(id);
    }

    public String getMinPath(String destino){
        return  g.printPath(destino);
    }

    public List<Enlance> createGraph(MetricaType metricaType, String f1, String f2) {

        graph = new ArrayList<>();
        for (Enlance e : enlaceDao.selectAll(metricaType)) {
            if (f1.isEmpty()) {
                graph.add(e);
            } else {
                if (f1.equals(f2)) {
                    if (!e.pop1.equals(f1) && !e.pop2.equals(f2)) {
                        //Log.d("graph",e.getPop1()+" , "+e.getPop2() +" , " +e.getMetrica() );
                        graph.add(e);
                    }
                } else {
                    if ((!(e.pop1.equals(f1) && e.pop2.equals(f2))) && (!(e.pop1.equals(f2) && e.pop2.equals(f1)))) {
                        // Log.d("graph",e.getPop1()+" , "+e.getPop2() +" , " +e.getMetrica() );
                        graph.add(e);
                    }
                }
            }
        }
        return graph;
    }

    public List<Pop> runDijkstra(String origem, String destino) {
        // Cria o grafo
        g = new Graph(graph);
        // Dijkstra de acordo com a origem
        g.dijkstra(origem);
        // Array de retorno
        pops = new ArrayList<>();
        // Variavel auxiliar, que recebe o pop destino
        aux = g.getPop(destino);
        // Enquanto aux.previus for diferente dele mesmo
        while (aux != aux.previus) {
            Log.i("Teste", aux.toString());
            // Adiciona o pop completo ao array
            pops.add(getCompletePop(aux));
            // Aux recebe o anterior
            aux = aux.previus;
        }
        // Adiciona o ultimo pop (origem)
        pops.add(getCompletePop(aux));
        // Retorna o array de pops com o menor caminho
        return pops;
    }

    private Pop getCompletePop(Pop pop) {
        aux2 = popDao.select(pop.id);
        aux2.dist = pop.dist;
        return aux2;
    }

}
