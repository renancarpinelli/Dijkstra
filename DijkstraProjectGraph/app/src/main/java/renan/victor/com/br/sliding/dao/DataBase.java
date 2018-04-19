package renan.victor.com.br.sliding.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by RananHome on 08/05/2017.
 */

public class DataBase extends SQLiteOpenHelper {
    private static final String TAG = "sql";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "graph";
    public static final String TABLE_POP = "pop";
    public static final String TABLE_ENLACE = "enlance";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando tabelas (pop)");
        String sql1 = "create table if not exists " + TABLE_POP
                + " (_id varchar(3) PRIMARY KEY, name varchar(15), latitude double, longitude double); ";
        String sql2 = "create table if not exists " + TABLE_ENLACE
                + " (_id_pop_a varchar(3), _id_pop_b varchar(3), metrica1 int(3), metrica2 int(3), metrica3 int(3),"
                + " FOREIGN KEY (_id_pop_a) REFERENCES " + TABLE_POP + " (_id), FOREIGN KEY (_id_pop_b) REFERENCES " + TABLE_POP + " (_id), primary key( _id_pop_a, _id_pop_b));";
        db.execSQL(sql1);
        db.execSQL(sql2);
        Log.d(TAG, "Tabelas Criadas");
        for (String inserts : GraphInserts.GraphInserts) {
            db.execSQL(inserts);
        }
        Log.d(TAG, "Inserts ok");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
