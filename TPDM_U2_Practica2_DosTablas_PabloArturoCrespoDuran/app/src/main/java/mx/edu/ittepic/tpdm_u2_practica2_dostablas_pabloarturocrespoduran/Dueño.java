package mx.edu.ittepic.tpdm_u2_practica2_dostablas_pabloarturocrespoduran;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Created by Usuario on 08/10/2018.
 */

public class Dueño {
    String id,nombre,domicilio,telefono;
    BaseDatos db;

    public Dueño (String id,String nombre, String domicilio, String telefono){
        this.id=id;
        this.nombre=nombre;
        this.domicilio=domicilio;
        this.telefono=telefono;
    }

    public Dueño(Activity activity){
        db = new BaseDatos(activity,"segurocoche",null,1);
    }

    public boolean insertar(Dueño dueño){
        try{
            SQLiteDatabase tabla = db.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("ID",dueño.id);
            data.put("NOMBRE",dueño.nombre);
            data.put("DOMICILIO",dueño.domicilio);
            data.put("TELEFONO",dueño.telefono);
            long res = tabla.insert("DUEÑO",null,data);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            Log.e("ERROR: ",e.getMessage());
            return false;
        }
        return true;
    }

    public Dueño[] consulta(){
        Dueño[] resultado=null;
        try{
            SQLiteDatabase tabla = db.getReadableDatabase();
            String SQL = "SELECT * FROM DUEÑO";


            Cursor c = tabla.rawQuery(SQL,null);
            if(c.moveToFirst()){
                resultado = new Dueño[c.getCount()];
                int i=0;
                do {
                    resultado[i++] = new Dueño(c.getString(0), c.getString(1), c.getString(2), c.getString(3));
                }while(c.moveToNext());
            }
            tabla.close();
        }catch (SQLiteException e){
            return null;
        }
        return resultado;
    }

    public boolean actualizar(Dueño dueño){
        try{
            SQLiteDatabase tabla = db.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("NOMBRE",dueño.nombre);
            data.put("DOMICILIO",dueño.domicilio);
            data.put("TELEFONO",dueño.telefono);
            String[] clave = {dueño.id};
            long res = tabla.update("DUEÑO",data,"ID=?",clave);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

    public boolean eliminar(Dueño dueño){
        try{
            SQLiteDatabase tabla = db.getWritableDatabase();
            String[] data = {dueño.id};
            long res = tabla.delete("DUEÑO","ID=?",data);
            tabla.close();
            if(res==0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

}
