package renan.victor.com.br.sliding.model;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RananHome on 08/05/2017.
 */

public class Pop implements Comparable<Pop>{
    public  String id;
    private String name;
    private LatLng latLng;
    public int dist = Integer.MAX_VALUE;
    public Pop previus = null;
    public final Map<Pop, Integer> neighbours = new HashMap<>();

    public Pop(){}

    public Pop(String id){
        this.id = id;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(Double latitude, Double longitude) {
        latLng = new LatLng(latitude,longitude);
    }

    public String printPath(){
        if(this == this.previus){
            return this.id +" ";
        }else if (this.previus == null){
            return this.id+"(inalcaçado)";
        }else{
            return this.previus.printPath() + " ->"+this.id+"("+this.dist+")";
        }
    }


    @Override
    public String toString() {
        return id;
    }

    @Override
    public int compareTo(Pop other) {
        if(dist == other.dist){
            return id.compareTo(other.id);
        }
        return Integer.compare(dist, other.dist);
    }
}
