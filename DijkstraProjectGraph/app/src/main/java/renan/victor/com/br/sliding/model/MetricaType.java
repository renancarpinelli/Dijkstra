package renan.victor.com.br.sliding.model;

/**
 * Created by RananHome on 08/05/2017.
 */

public enum MetricaType {

    METRICA1("metrica1"), METRICA2("metrica2"), METRICA3("metrica3");

    private String type;

    private MetricaType(String type){
        this.type = type;
    }


    @Override
    public String toString() {
        return type;
    }
}
