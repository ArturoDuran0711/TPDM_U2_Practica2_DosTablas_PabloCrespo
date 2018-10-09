package mx.edu.ittepic.tpdm_u2_practica2_dostablas_pabloarturocrespoduran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText id, nombre, domicilio, telefono;
    Button guardar,regresar;
    Dueño dueño;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        id = findViewById(R.id.id);
        nombre = findViewById(R.id.nombre);
        domicilio = findViewById(R.id.domicilio);
        telefono = findViewById(R.id.telefono);
        guardar = findViewById(R.id.guardar);
        regresar = findViewById(R.id.regresar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dueño = new Dueño(Main2Activity.this);

                boolean res = dueño.insertar(new Dueño(id.getText().toString(),nombre.getText().toString(),
                        domicilio.getText().toString(), telefono.getText().toString()));

                if(res) {
                    Toast.makeText(Main2Activity.this,"Se pudo insertar",
                            Toast.LENGTH_LONG).show();
                    id.setText("");
                    nombre.setText("");
                    domicilio.setText("");
                    telefono.setText("");
                } else {
                    Toast.makeText(Main2Activity.this,"ERROR AL INSERTAR",
                            Toast.LENGTH_LONG).show();
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
