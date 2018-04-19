package renan.victor.com.br.sliding.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import renan.victor.com.br.sliding.model.Enlance;
import renan.victor.com.br.sliding.model.MetricaType;


/**
 * Created by RananHome on 08/05/2017.
 */

public class EnlaceDAO {

    private Context context;
    private PopDAO popDAO;

    public EnlaceDAO(Context context){
        this.context = context;
        popDAO = new PopDAO(context);
    }

    public boolean insert(String id_pop_a, String id_pop_b, int metrica1, int metrica2,int metrica3  ){
        SQLiteDatabase db = new DataBase(context).getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("_id_pop_a",id_pop_a);
        values.put("_id_pop_b",id_pop_b);
        values.put("metrica1",metrica1);
        values.put("metrica2",metrica2);
        values.put("metrica3",metrica3);

        return (db.insert(DataBase.TABLE_ENLACE,null,values) > 0);
    }

    public Enlance select(String id_pop_a, String id_pop_b, MetricaType metrica){
        SQLiteDatabase db = new DataBase(context).getReadableDatabase();
        String[] whereArgs = new String[]{id_pop_a,id_pop_b};
        Enlance enlance = new Enlance();

        Cursor cursor = db.query(DataBase.TABLE_ENLACE, null, "_id_pop_a=? and _id_pop_b=?", whereArgs, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            enlance.setPop1(cursor.getString(0));
            enlance.setPop2(cursor.getString(1));
            enlance.setMetrica(cursor.getInt(cursor.getColumnIndex(metrica.toString())));
            enlance.setPopOrigem(popDAO.select(enlance.getPop1()));
            enlance.setPopDestino(popDAO.select(enlance.getPop2()));
            return enlance;
        }
        return null;
    }

    public List<Enlance> selectAll(MetricaType metrica){
        SQLiteDatabase db = new DataBase(context).getReadableDatabase();
        List<Enlance> enlances = new ArrayList<>();
        Cursor cursor = db.query(DataBase.TABLE_ENLACE, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            Enlance enlance = new Enlance();
            enlance.setPop1(cursor.getString(0));
            enlance.setPop2(cursor.getString(1));
            enlance.setMetrica(cursor.getInt(cursor.getColumnIndex(metrica.toString())));
            enlance.setPopOrigem(popDAO.select(enlance.getPop1()));
            enlance.setPopDestino(popDAO.select(enlance.getPop2()));
            enlances.add(enlance);
        }
        return enlances;
    }





}
