package renan.victor.com.br.sliding.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import renan.victor.com.br.sliding.model.Pop;


/**
 * Created by RananHome on 08/05/2017.
 */

public class PopDAO {

    private Context context;


    public PopDAO(Context context) {
        this.context = context;
    }

    public boolean insert(String id, String name, Double latitude, Double longitude) {
        SQLiteDatabase db = new DataBase(context).getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("_id", id);
        values.put("name", name);
        values.put("latitude", latitude);
        values.put("longitude",longitude);

        return (db.insert(DataBase.TABLE_POP, null, values) > 0);
    }

    public Pop select(String id) {
        SQLiteDatabase db = new DataBase(context).getReadableDatabase();
        String[] whereArgs = new String[]{id};
        Pop pop = new Pop(id);

        Cursor cursor = db.query(DataBase.TABLE_POP, null, "_id=?", whereArgs, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            pop.setId(cursor.getString(0));
            pop.setName(cursor.getString(1));
            pop.setLatLng(cursor.getDouble(2),cursor.getDouble(3));
            return pop;
        }

        return null;
    }

    public List<Pop> selectAll() {
        SQLiteDatabase db = new DataBase(context).getReadableDatabase();
        List<Pop> pops = new ArrayList<>();
        Cursor cursor = db.query(DataBase.TABLE_POP, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            Pop pop = new Pop();
            pop.setId(cursor.getString(0));
            pop.setName(cursor.getString(1));
            pop.setLatLng(cursor.getDouble(2),cursor.getDouble(3));
            pops.add(pop);
        }
        return pops;
    }




}
