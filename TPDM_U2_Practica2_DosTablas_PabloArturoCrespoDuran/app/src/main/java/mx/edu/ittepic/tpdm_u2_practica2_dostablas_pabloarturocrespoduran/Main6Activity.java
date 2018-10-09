package mx.edu.ittepic.tpdm_u2_practica2_dostablas_pabloarturocrespoduran;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Main6Activity extends AppCompatActivity {

    TextView titulo;
    EditText modelop, marcap, añop, fechainiciop, preciop, tipopolizap;
    Spinner iddueñosp;
    Button actualizar, eliminar, regresar;
    Dueño [] dueños;
    Poliza poliza;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        titulo = findViewById(R.id.titulo);
        modelop = findViewById(R.id.modelo);
        marcap = findViewById(R.id.marca);
        añop = findViewById(R.id.año);
        fechainiciop = findViewById(R.id.fechainicio);
        preciop = findViewById(R.id.precio);
        tipopolizap = findViewById(R.id.tipopoliza);
        iddueñosp = findViewById(R.id.dueños);
        actualizar = findViewById(R.id.actualizar);
        eliminar = findViewById(R.id.eliminar);
        regresar = findViewById(R.id.regresar);

        final int iden = getIntent().getExtras().getInt("iden");
        String modelo = getIntent().getExtras().getString("modelo");
        String marca = getIntent().getExtras().getString("marca");
        int fechaaño = getIntent().getExtras().getInt("fechaaño");
        String fechainicio = getIntent().getExtras().getString("fechainicio");
        Float costo = getIntent().getExtras().getFloat("costo");
        final String tipopoliza = getIntent().getExtras().getString("tipopoliza");
        final String iddueño = getIntent().getExtras().getString("iddueño");

        titulo.setText("POLIZA No."+iden);
        modelop.setText(modelo);
        marcap.setText(marca);
        añop.setText(""+fechaaño);
        fechainiciop.setText(fechainicio);
        preciop.setText(""+costo);
        tipopolizap.setText(tipopoliza);
        iddueñosp.setPrompt(""+iddueño);

        dueños = new Dueño(this).consulta();
        if(dueños.length==0){
            Toast.makeText(this,"NO HAY DUEÑOS, CAPTURE PRIMERO",Toast.LENGTH_LONG).show();
            actualizar.setEnabled(false);
            eliminar.setEnabled(false);
            return;
        }
        final String[] nombres = new String[dueños.length];
        for(int i=0; i<nombres.length; i++){
            nombres[i] = dueños[i].nombre;
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        iddueñosp.setAdapter(adaptador);
        poliza = new Poliza(this);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean respuesta = poliza.eliminar(new Poliza(iden,modelop.getText().toString(),marcap.getText().toString(),
                        Integer.parseInt(añop.getText().toString()),fechainiciop.getText().toString(),
                        Float.parseFloat(preciop.getText().toString()),tipopolizap.getText().toString(),iddueño));
                if(respuesta){
                    Toast.makeText(Main6Activity.this,"Se elimino con exito",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Main6Activity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    AlertDialog.Builder a = new AlertDialog.Builder(Main6Activity.this);
                    a.setTitle("ERROR").setMessage("no se pudo eliminar").show();
                }

            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean respuesta = poliza.actualizar(new Poliza(iden,modelop.getText().toString(),marcap.getText().toString(),
                        Integer.parseInt(añop.getText().toString()),fechainiciop.getText().toString(),
                        Float.parseFloat(preciop.getText().toString()),tipopolizap.getText().toString(),iddueño));
                if(respuesta){
                    AlertDialog.Builder a = new AlertDialog.Builder(Main6Activity.this);
                    a.setTitle("Exito").setMessage("se actualizó")
                            .setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                } else {
                    AlertDialog.Builder a = new AlertDialog.Builder(Main6Activity.this);
                    a.setTitle("ERROR").setMessage("no se pudo actualizar").show();
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
