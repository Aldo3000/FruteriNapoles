package aplicacion.android.app.betusto.fruterinapoles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activity_Salida extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Elementos de los spinners
    String[] SPINNER_DATA = {"Cebolla Blanca Primera", "Cebolla Blanca Segunda", "Cebolla Amarilla", "Limon","Jalapeno"
            , "Papa", "Aguacate", "Tomate", "Cilantro", "Lechuga", "Papa Galeana", "Mango",
            "Platano", "Manzana", "Chile Japones", "Chile de Arbol"};
    String[] FECHAS = {};
    List<String> fechas = new ArrayList<>();

    private Button fecha;
    private TextView cantidadEntrada;
    private Button retirar;
    private EditText cantidad;
    public String Cantidad, Producto, Fecha;
    MetodosUtiles MU = new MetodosUtiles();
    BaseDeDatos BD = new BaseDeDatos();
    private DatabaseReference Database;
    SharedPreferences sharedPreferences;
    VariablesEstaticas VE = new VariablesEstaticas();
    private List CantidadDeSnapshots = new ArrayList();
    private Spinner spinner; //Spinner de productos
    private Spinner spinner2; //Spinner de Fechas


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__salida);

        spinner = findViewById(R.id.activity__salida_spinner);
        spinner2 = findViewById(R.id.activity__salida_spinner2);
        retirar = findViewById(R.id.activity__salida_retirarbutton);
        cantidadEntrada = findViewById(R.id.activity__salida_cantidadentradatext);
        cantidad = findViewById(R.id.activity__salida_edittext);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity_Salida.this,
                android.R.layout.simple_dropdown_item_1line, SPINNER_DATA);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //Persistencia de datos y referencia
        Database = FirebaseDatabase.getInstance().getReference();
        Database.keepSynced(true);
        //Persistencia de variables
        sharedPreferences= getSharedPreferences(VariablesEstaticas.SHARED_PREFS, Context.MODE_PRIVATE);
        VE.CargarDatos(sharedPreferences);


        retirar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fecha=fechaText.getText().toString();
                Cantidad = cantidad.getText().toString().trim();
                boolean numeric = true;
                int detectorErrores = 0;
                //TODO: REVISAR QUE NO SEAN DECIMALES
                numeric = Cantidad.matches("-?\\d+(\\.\\d+)?");
                if(!numeric) {
                    cantidad.setError("Escriba un número");
                    detectorErrores=1;
                }
                //if(Fecha == null){
                //    MU.MostrarToast(Activity_Salida.this, "Escoja una fecha");
                //    detectorErrores=1;
                //}else if(Fecha.isEmpty()){
                //    MU.MostrarToast(Activity_Salida.this, "Escoja una fecha");
                //    detectorErrores=1;
                //}
                //Si no hay errores almacenamos
                if(detectorErrores==0){
                    //BD.AlAñadirSalida(Producto, Cantidad, Fecha);
                    MU.MostrarToast(Activity_Salida.this, "La cantidad que marcó fue removida exitosamente");
                    Activity_Salida.this.finish();
                    Intent reebot = new Intent(Activity_Salida.this, Activity_Salida.class);
                    //Engañar al usuario, no pude encontrar una mejor solucion, el recycler no se actualiza
                    //hasta que se cambie de activity
                    //VINCULO
                    //Quitar animaciones
                    Activity_Salida.this.overridePendingTransition(0, 0);
                    Activity_Salida.this.startActivity(reebot);
                    Activity_Salida.this.overridePendingTransition(0, 0);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Producto = parent.getItemAtPosition(position).toString();
        //ExisteCorreo = false;
        //Recorremos las entradas buscando productos que concuerden con el seleccionado
        Database.child("Entradas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("TEST2", "TODO BIEN");
                final int CantidadTotalDeSnaps = (int) dataSnapshot.getChildrenCount();
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) //Recorremos cada campo de la tabla Entradas
                {
                    if(snapshot.getValue() != null) { //*No se puede usar equals en null aunque sea string*
                        Database.child("Entradas").child(snapshot.getKey()).child("NombreProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                CantidadDeSnapshots.add(snapshot.child("NombreProducto").getValue().toString());
                                if(snapshot.child("NombreProducto").getValue().toString().equals(Producto)){
                                    fechas.add(snapshot.child("FechaEntrada").getValue().toString());
                                }
                                if(CantidadTotalDeSnaps == CantidadDeSnapshots.size()) { //Ultimo ciclo del for ejecutar lo siguiente
                                    //La lista la convertimos en Array
                                    FECHAS = Arrays.copyOf(fechas.toArray(),
                                            fechas.toArray().length,
                                            String[].class);
                                    CantidadDeSnapshots.clear();
                                    fechas.clear();
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity_Salida.this,
                                            android.R.layout.simple_dropdown_item_1line, FECHAS);
                                    spinner2.setAdapter(adapter);
                                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            Producto = parent.getItemAtPosition(position).toString();
                                            MU.MostrarToast(Activity_Salida.this, Producto);
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
