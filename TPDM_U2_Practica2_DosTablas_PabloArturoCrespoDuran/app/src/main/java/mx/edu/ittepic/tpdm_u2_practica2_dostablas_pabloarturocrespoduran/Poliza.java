package mx.edu.ittepic.tpdm_u2_practica2_dostablas_pabloarturocrespoduran;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class Poliza {
    int id,año;
    String modelo, marca, fechainicio,tipopoliza,iddueño;
    Float precio;
    BaseDatos db;

    public Poliza(Activity activity){
        db = new BaseDatos(activity,"segurocoche",null,1);
    }

    public Poliza(int idcoche,String modelo,String marca,int año, String fechainicio,Float precio, String tipopoliza, String iddueño){
        this.id=idcoche;
        this.modelo=modelo;
        this.marca=marca;
        this.año=año;
        this.fechainicio=fechainicio;
        this.precio=precio;
        this.tipopoliza=tipopoliza;
        this.iddueño=iddueño;
    }

    public boolean insertar(Poliza poliza){
        try{
            SQLiteDatabase tabla = db.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("MODELO",poliza.modelo);
            data.put("MARCA",poliza.marca);
            data.put("AÑO",poliza.año);
            data.put("FECHAINICIO",poliza.fechainicio);
            data.put("PRECIO",poliza.precio);
            data.put("TIPOPOLIZA",poliza.tipopoliza);
            data.put("IDDUEÑO",poliza.iddueño);
            long res = tabla.insert("POLIZA","IDCOCHE",data);
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

    public Poliza[] consulta(){
        Poliza[] resultado=null;
        try{
            SQLiteDatabase tabla = db.getReadableDatabase();
            String SQL = "SELECT * FROM POLIZA";

            Cursor c = tabla.rawQuery(SQL,null);
            if(c.moveToFirst()){
                resultado = new Poliza[c.getCount()];
                int i=0;
                do {
                    resultado[i++] = new Poliza(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3),c.getString(4),c.getFloat(5),c.getString(6),c.getString(7));
                }while(c.moveToNext());
            }
            tabla.close();
        }catch (SQLiteException e){
            return null;
        }
        return resultado;
    }

    public boolean eliminar(Poliza poliza){
        try{
            SQLiteDatabase tabla = db.getWritableDatabase();
            String[] data = {""+poliza.id};
            long res = tabla.delete("POLIZA","IDCOCHE=?",data);
            tabla.close();
            if(res==0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

    public boolean actualizar(Poliza poliza){
        try{
            SQLiteDatabase tabla = db.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("MODELO",poliza.modelo);
            data.put("MARCA",poliza.marca);
            data.put("AÑO",poliza.año);
            data.put("FECHAINICIO",poliza.fechainicio);
            data.put("PRECIO",poliza.precio);
            data.put("TIPOPOLIZA",poliza.tipopoliza);
            data.put("IDDUEÑO",poliza.iddueño);
            String[] clave = {""+poliza.id};
            long res = tabla.update("POLIZA",data,"IDCOCHE=?",clave);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }
}
