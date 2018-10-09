package mx.edu.ittepic.tpdm_u2_practica2_dostablas_pabloarturocrespoduran;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    ListView lista;
    Dueño dueño;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lista = findViewById(R.id.listadueños);
        dueño = new Dueño(this);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int fi, long l) {
                final Dueño[] s = dueño.consulta();
                final int i = fi;
                AlertDialog.Builder alerta = new AlertDialog.Builder(Main3Activity.this);
                alerta.setTitle("Detalle de "+s[i].nombre)
                        .setMessage("ID: "+s[i].id+"\nDomicilio: "+s[i].domicilio+"\nTelefono: "+s[i].telefono+"\n\n¿Deseas modificar/Eliminar registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int ift) {
                                //Toast.makeText(Main3Activity.this,""+s[i].numero+" "+s[i].nombre+" "+s[i].telefono, Toast.LENGTH_LONG).show();
                                Intent datos = new Intent(Main3Activity.this, Main4Activity.class);
                                datos.putExtra("id",s[i].id);
                                datos.putExtra("nombre",s[i].nombre);
                                datos.putExtra("telefono",s[i].telefono);
                                datos.putExtra("domicilio",s[i].domicilio);
                                startActivity(datos);
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });

    }

    protected void onStart(){
        super.onStart();
        try {
            String nombres[] = {"No hay dueños capturados aun"};
            Dueño[] s = dueño.consulta();
            if (s!=null) {
                nombres = new String[s.length];
                for (int i = 0; i < nombres.length; i++) {
                    nombres[i] = s[i].nombre;
                }
            }
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    this, android.R.layout.simple_list_item_1, nombres);
            lista.setAdapter(adaptador);
        } catch (Exception e){

        }
    }
}
