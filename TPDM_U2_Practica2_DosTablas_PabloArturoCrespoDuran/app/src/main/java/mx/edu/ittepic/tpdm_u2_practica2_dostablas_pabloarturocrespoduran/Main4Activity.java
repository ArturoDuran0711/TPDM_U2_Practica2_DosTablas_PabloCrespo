package mx.edu.ittepic.tpdm_u2_practica2_dostablas_pabloarturocrespoduran;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    EditText idD, nombreD, domicilioD, telefonoD;
    Button modificarD, eliminarD, regresarD;
    Dueño dueño;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        dueño = new Dueño(this);


        final String id = getIntent().getExtras().getString("id");
        final String nombre = getIntent().getExtras().getString("nombre");
        String domicilio = getIntent().getExtras().getString("domicilio");
        final String telefono = getIntent().getExtras().getString("telefono");

        idD = findViewById(R.id.idDueño);
        nombreD = findViewById(R.id.nombre);
        domicilioD = findViewById(R.id.domicilio);
        telefonoD = findViewById(R.id.telefono);
        modificarD = findViewById(R.id.modificar);
        eliminarD = findViewById(R.id.borrar);
        regresarD = findViewById(R.id.regresar);

        idD.setText(id);
        nombreD.setText(nombre);
        domicilioD.setText(domicilio);
        telefonoD.setText(telefono);

        modificarD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean respuesta = dueño.actualizar(new Dueño(id,nombreD.getText().toString(),domicilioD.getText().toString(),telefonoD.getText().toString()));
                if(respuesta){
                    AlertDialog.Builder a = new AlertDialog.Builder(Main4Activity.this);
                        a.setTitle("Exito").setMessage("se actualizó")
                         .setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialogInterface, int i) {
                                 dialogInterface.dismiss();
                             }
                         })
                         .show();
                } else {
                    AlertDialog.Builder a = new AlertDialog.Builder(Main4Activity.this);
                    a.setTitle("ERROR").setMessage("no se pudo actualizar").show();
                }
            }
        });

        regresarD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        eliminarD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean respuesta = dueño.eliminar(new Dueño(id,nombreD.getText().toString(),domicilioD.getText().toString(),telefonoD.getText().toString()));
                if(respuesta){
                    //AlertDialog.Builder a = new AlertDialog.Builder(Main4Activity.this);
                    //a.setTitle("Exito").setMessage("se eliminó").show();
                    Toast.makeText(Main4Activity.this,"Se elimino con exito",Toast.LENGTH_LONG).show();
                } else {
                    AlertDialog.Builder a = new AlertDialog.Builder(Main4Activity.this);
                    a.setTitle("ERROR").setMessage("no se pudo eliminar")
                            .setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                }
                finish();
            }
        });
    }
}
