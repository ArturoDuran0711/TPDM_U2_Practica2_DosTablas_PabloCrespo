package mx.edu.ittepic.tpdm_u2_practica2_dostablas_pabloarturocrespoduran;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.listaPoliza);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Main5Activity.class);
                startActivity(i);
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                Poliza pol = new Poliza(MainActivity.this);
                final Poliza polizas[] = pol.consulta();
                final int pos = i;


                alerta.setTitle("Detalle de la poliza: "+polizas[pos].id)
                        .setMessage("Modelo: "+polizas[pos].modelo+"\nMarca: "+polizas[pos].marca+"\nTipo de Poliza: "+polizas[pos].tipopoliza+"\n\n¿Deseas modificar/Eliminar registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int ift) {

                                Intent datos = new Intent(MainActivity.this, Main6Activity.class);
                                datos.putExtra("iden",polizas[pos].id);
                                datos.putExtra("modelo",polizas[pos].modelo);
                                datos.putExtra("marca",polizas[pos].marca);
                                datos.putExtra("fechaaño",polizas[pos].año);
                                datos.putExtra("fechainicio",polizas[pos].fechainicio);
                                datos.putExtra("costo",polizas[pos].precio);
                                datos.putExtra("tipopoliza",polizas[pos].tipopoliza);
                                datos.putExtra("iddueño",polizas[pos].iddueño);

                                startActivity(datos);
                                dialogInterface.dismiss();
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                    }
                }).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.agregarDueño) {
            Intent i = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.detalleDueño) {
            Intent i = new Intent(MainActivity.this, Main3Activity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onStart(){
        super.onStart();
        Poliza pol = new Poliza(this);
        Poliza polizas[] = pol.consulta();
        if(polizas==null){
            Toast.makeText(this,"NO HAY POLIZAS",Toast.LENGTH_LONG).show();
        } else {
            String NoPol[] = new String[polizas.length];
            for (int i = 0; i < NoPol.length; i++) {
                NoPol[i] = "Id: "+polizas[i].id + "\nMarca: " + polizas[i].marca+ "\nTipo Poliza: " + polizas[i].tipopoliza;
            }
            ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, NoPol);
            lista.setAdapter(adap);
        }
    }
}
