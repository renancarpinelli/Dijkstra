package renan.victor.com.br.sliding.model;

/**
 * Created by RananHome on 08/05/2017.
 */

public class Enlance {
    public String pop1;
    public String pop2;
    public Integer metrica;
    private Pop popOrigem, popDestino;

    public Enlance(){
    }

    public Enlance(String pop1, String pop2, Integer metrica) {
        this.pop1 = pop1;
        this.pop2 = pop2;
        this.metrica = metrica;
    }

    public String getPop1() {
        return pop1;
    }

    public void setPop1(String pop1) {
        this.pop1 = pop1;
    }

    public String getPop2() {
        return pop2;
    }

    public void setPop2(String pop2) {
        this.pop2 = pop2;
    }

    public Integer getMetrica() {
        return metrica;
    }

    public void setMetrica(Integer metrica) {
        this.metrica = metrica;
    }

    public Pop getPopOrigem() {
        return popOrigem;
    }

    public void setPopOrigem(Pop popOrigem) {
        this.popOrigem = popOrigem;
    }

    public Pop getPopDestino() {
        return popDestino;
    }

    public void setPopDestino(Pop popDestino) {
        this.popDestino = popDestino;
    }

    @Override
    public String toString() {
        return "Enlance{" +
                "pop1='" + pop1 + '\'' +
                ", pop2='" + pop2 + '\'' +
                ", metrica=" + metrica +
                '}';
    }
}
