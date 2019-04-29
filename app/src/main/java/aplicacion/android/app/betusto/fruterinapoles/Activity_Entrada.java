package aplicacion.android.app.betusto.fruterinapoles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class Activity_Entrada extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    MaterialBetterSpinner materialBetterSpinner ;

   String[] SPINNER_DATA = {"Cebolla Blanca Primera", "Cebolla Blanca Segunda", "Cebolla Amarilla", "Limon","Jalapeno"
            , "Papa", "Aguacate", "Tomate", "Cilantro", "Lechuga", "Papa Galeana", "Mango",
           "Platano", "Manzana", "Chile Japones", "Chile de Arbol"};

   /* String[] SPINNER_DATA1 = {"Roja","Blanca"
            , "Negra", "Verde", "Naranja", "Azul"};*/

    private Button fecha;
    private TextView fechaText;
    private Button agregar;
    private Button gradoRojo, gradoAmarillo, gradoVerde;
    private EditText cantidad;
    public String Madurez, Cantidad, Producto, Fecha;
    private int CambioRojo = 0, CambioAmarillo = 0, CambioVerde = 0;
    MetodosUtiles MU = new MetodosUtiles();
    BaseDeDatos BD = new BaseDeDatos();

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__entrada);

        Spinner spinner = findViewById(R.id.spinner1);
        Spinner spinner1 = findViewById(R.id.spinner2);
        fecha = findViewById(R.id.activity__entrada_fechabutton);
        fechaText = findViewById(R.id.activity__entrada_fechatext);
        agregar = findViewById(R.id.activity__entrada_agregar_button);
        gradoRojo = findViewById(R.id.gradorojo);
        gradoAmarillo =findViewById(R.id.gradoamarillo);
        gradoVerde = findViewById(R.id.gradoverde);
        cantidad = findViewById(R.id.activity__entrada_edittext);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity_Entrada.this,
                android.R.layout.simple_dropdown_item_1line, SPINNER_DATA);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        gradoRojo.setAlpha(.5f);
        gradoVerde.setAlpha(.5f);
        gradoAmarillo.setAlpha(.5f);

        /*materialBetterSpinner = (MaterialBetterSpinner)findViewById(R.id.material_spinner1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity_Entrada.this,
                android.R.layout.simple_dropdown_item_1line, SPINNER_DATA);

        materialBetterSpinner.setAdapter(adapter);*/
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MU.MostrarDatePicker(Activity_Entrada.this, fechaText);
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fecha=fechaText.getText().toString();
                Cantidad = cantidad.getText().toString().trim();
                boolean numeric = true;
                int detectorErrores = 0;
                //TODO: REVISAR QUE NO SEAN DECIMALES
                numeric = Cantidad.matches("-?\\d+(\\.\\d+)?");
                if(!numeric) {
                    cantidad.setError("Escriba un número");
                    detectorErrores=1;
                }
                if(Fecha == null){
                    MU.MostrarToast(Activity_Entrada.this, "Escoja una fecha");
                    detectorErrores=1;
                }
                if(Madurez == null){
                    MU.MostrarToast(Activity_Entrada.this, "Escoja un grado de madurez");
                    detectorErrores=1;
                }else if(Madurez.isEmpty()){
                    MU.MostrarToast(Activity_Entrada.this, "Escoja un grado de madurez");
                    detectorErrores=1;
                }
                //Si no hay errores almacenamos
                if(detectorErrores==0){
                    BD.AlAñadirEntrada(Producto, Madurez, Cantidad, Fecha);
                    MU.MostrarToast(Activity_Entrada.this, "Entrada guardada");
                }
            }
        });

        gradoRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CambioRojo == 0) {
                    CambioVerde=0;
                    CambioAmarillo=0;
                    gradoVerde.setAlpha(0.3f);
                    gradoAmarillo.setAlpha(0.3f);

                    gradoRojo.setAlpha(1f);
                    CambioRojo=1;
                    Madurez = "Red";
                }else{
                    gradoRojo.setAlpha(0.3f);
                    CambioRojo=0;
                    Madurez = "";
                }
            }
        });

        gradoAmarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CambioAmarillo == 0) {
                    CambioVerde=0;
                    CambioRojo=0;
                    gradoVerde.setAlpha(0.3f);
                    gradoRojo.setAlpha(0.3f);
                    gradoAmarillo.setAlpha(1f);
                    CambioAmarillo=1;
                    Madurez = "Yellow";
                }else{
                    gradoAmarillo.setAlpha(0.3f);
                    CambioAmarillo=0;
                    Madurez = "";
                }            }
        });

        gradoVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CambioVerde == 0) {
                    CambioAmarillo=0;
                    CambioRojo=0;
                    gradoRojo.setAlpha(0.3f);
                    gradoAmarillo.setAlpha(0.3f);
                    gradoVerde.setAlpha(1f);
                    CambioVerde=1;
                    Madurez = "Green";
                }else{
                    gradoVerde.setAlpha(0.3f);
                    CambioVerde=0;
                    Madurez = "";
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Producto = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), Producto, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
