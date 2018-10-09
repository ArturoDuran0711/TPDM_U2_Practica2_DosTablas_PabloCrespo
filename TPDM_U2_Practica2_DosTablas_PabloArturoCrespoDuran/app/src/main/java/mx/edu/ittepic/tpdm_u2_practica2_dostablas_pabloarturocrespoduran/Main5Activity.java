package mx.edu.ittepic.tpdm_u2_practica2_dostablas_pabloarturocrespoduran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    EditText modelo,marca,año,fechainicio,precio,tipopoliza;
    Spinner iddueños;
    Button guardar,regresar;
    Dueño [] dueños;
    Poliza poliza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        modelo = findViewById(R.id.modelo);
        marca = findViewById(R.id.marca);
        año = findViewById(R.id.año);
        fechainicio = findViewById(R.id.fechainicio);
        precio = findViewById(R.id.precio);
        tipopoliza = findViewById(R.id.tipopoliza);
        iddueños = findViewById(R.id.dueños);
        guardar = findViewById(R.id.guardar);
        regresar = findViewById(R.id.regresar);

        dueños= new Dueño(this).consulta();
        poliza = new Poliza(this);

        /*if(dueños.length==0){
            Toast.makeText(this,"NO EXISTEN DUEÑOS",Toast.LENGTH_LONG).show();
            guardar.setEnabled(false);
            iddueños.setEnabled(false);
            return;
        }*/

        String[] nombres = new String[dueños.length];
        for(int i=0; i<nombres.length; i++){
            nombres[i] = dueños[i].nombre;
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        iddueños.setAdapter(adaptador);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = iddueños.getSelectedItemPosition();

                Poliza pol = new Poliza(0,modelo.getText().toString(),marca.getText().toString(),
                        Integer.parseInt(año.getText().toString()), fechainicio.getText().toString(),
                        Float.parseFloat(precio.getText().toString()),tipopoliza.getText().toString(), dueños[pos].id);
                boolean respuesta = poliza.insertar(pol);
                if(respuesta) {
                    Toast.makeText(Main5Activity.this,"Se pudo insertar", Toast.LENGTH_LONG).show();
                    modelo.setText("");
                    marca.setText("");
                    año.setText("");
                    fechainicio.setText("");
                    precio.setText("");
                    tipopoliza.setText("");
                } else {
                    Toast.makeText(Main5Activity.this,"ERROR AL INSERTAR", Toast.LENGTH_LONG).show();
                }
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}

