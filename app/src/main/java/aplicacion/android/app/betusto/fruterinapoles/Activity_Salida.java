package aplicacion.android.app.betusto.fruterinapoles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Activity_Salida extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Elementos de los spinners
    String[] SPINNER_DATA = {"Cebolla Blanca Primera", "Cebolla Blanca Segunda", "Cebolla Amarilla", "Limon","Jalapeno"
            , "Papa", "Aguacate", "Tomate", "Cilantro", "Lechuga", "Papa Galeana", "Mango",
            "Platano", "Manzana", "Chile Japones", "Chile de Arbol"};
    List<String> FECHAS = new ArrayList<>();
    List<String> fechas = new ArrayList<>();
    List<String> cantidadProducto = new ArrayList<>();
    List<String> estampas = new ArrayList<>();

    ArrayAdapter<String> adaptador;

    private Button fecha;
    private TextView cantidadEntrada;
    private Button retirar;
    private EditText cantidad;
    public String CantidadEscrita, Producto, Fecha, TimeStamp, CantidadDeLaEntrada;
    MetodosUtiles MU = new MetodosUtiles();
    BaseDeDatos BD = new BaseDeDatos();
    private DatabaseReference Database;
    SharedPreferences sharedPreferences;
    VariablesEstaticas VE = new VariablesEstaticas();
    private List CantidadDeSnapshots = new ArrayList();
    private Spinner spinner; //Spinner de productos
    private Spinner spinner2; //Spinner de Fechas
    private int posicion;
    private int posicionFruta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__salida);

        spinner = findViewById(R.id.activity__salida_spinner);
        spinner2 = findViewById(R.id.activity__salida_spinner2);
        retirar = findViewById(R.id.activity__salida_retirarbutton);
        cantidadEntrada = findViewById(R.id.activity__salida_cantidadentradatext);
        cantidad = findViewById(R.id.activity__salida_edittext);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity_Salida.this,
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
                CantidadEscrita = cantidad.getText().toString().trim();
                boolean numeric = true;
                int detectorErrores = 0;
                numeric = CantidadEscrita.matches("-?\\d+(\\.\\d+)?");
                if(!numeric) {
                    cantidad.setError("Escriba un número");
                    detectorErrores=1;
                }
                if(Fecha == null){
                    MU.MostrarToast(Activity_Salida.this, "Escoja un producto que tenga fechas de entradas");
                    detectorErrores=1;
                }else if(Fecha.isEmpty()){
                    MU.MostrarToast(Activity_Salida.this, "Escoja un producto que tenga fechas de entradas");
                    detectorErrores=1;
                }
                //Si no hay errores almacenamos
                if(detectorErrores==0){
                    int resta = BD.AlRetirarProducto(TimeStamp, CantidadDeLaEntrada, CantidadEscrita);
                    //BD.AlAñadirSalida(Producto, Cantidad, Fecha);
                    MU.MostrarToast(Activity_Salida.this, "La cantidad que marcó fue removida exitosamente");
                    cantidad.setText("");
                    if(resta <= 0){
                        cantidadProducto.remove(posicion);
                        FECHAS.remove(posicion);
                        adaptador.notifyDataSetChanged();
                        cantidadEntrada.setText("");
                    }else{
                        cantidadProducto.set(posicion, resta+"");
                        cantidadEntrada.setText(cantidadProducto.get(posicion));
                        adaptador.notifyDataSetChanged();
                    }
                   /* int item_postion=posicionFruta;// item which you want to click
                    spinner.setSelection(item_postion, true);
                    View item_view = (View)spinner.getChildAt(item_postion);
                    long item_id = spinner.getAdapter().getItemId(item_postion);
                    spinner.performItemClick(item_view, posicionFruta, item_id);*/
                 /*   Activity_Salida.this.finish();
                    Intent reebot = new Intent(Activity_Salida.this, Activity_Salida.class);
                    //Engañar al usuario, no pude encontrar una mejor solucion, el recycler no se actualiza
                    //hasta que se cambie de activity
                    //Quitar animaciones
                    Activity_Salida.this.overridePendingTransition(0, 0);
                    Activity_Salida.this.startActivity(reebot);
                    Activity_Salida.this.overridePendingTransition(0, 0);*/
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        posicionFruta = position;
        cantidad.setEnabled(false);
        Fecha = null;
        estampas.clear();
        FECHAS.clear();
        cantidadProducto.clear();//Reiniciamos la lista que contiene la cantidad de producto de cada fecha
        cantidadEntrada.setText("");//Limpiamos el texto que muestra la cantidad de producto de la fecha
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
                                    cantidadProducto.add(snapshot.child("CantidadProducto").getValue().toString());
                                    estampas.add(snapshot.child("Timestamp").getValue().toString()); //Timestamp
                                    Log.e("TEST",estampas.toString());
                                }
                                if(CantidadTotalDeSnaps == CantidadDeSnapshots.size()) { //Ultimo ciclo del for ejecutar lo siguiente
                                    //La lista la convertimos en Array
                                    FECHAS.addAll(fechas);
                                    //FECHAS = Arrays.copyOf(fechas.toArray(),
                                    //        fechas.toArray().length,
                                     //       String[].class);
                                    CantidadDeSnapshots.clear();
                                    fechas.clear();
                                    adaptador = new ArrayAdapter<String>(Activity_Salida.this,
                                            android.R.layout.simple_dropdown_item_1line, FECHAS);
                                    spinner2.setAdapter(adaptador);
                                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            if (estampas.size() != 0) {
                                                cantidad.setEnabled(true); //El edittext ahora se puede escribir en el, porque encontro fechas disponibles
                                                Fecha = parent.getItemAtPosition(position).toString();
                                                TimeStamp = estampas.get(position);
                                                cantidadEntrada.setText(cantidadProducto.get(position));
                                                CantidadDeLaEntrada = cantidadProducto.get(position);
                                                posicion = position;
                                                //No se podra escribir un numero mayor al que se vea en el textview
                                                int valorEscrito;
                                                if (!cantidad.getText().toString().trim().equals("")) {
                                                    valorEscrito = Integer.parseInt(cantidad.getText().toString().trim());
                                                } else {
                                                    valorEscrito = 0;
                                                }
                                                switch (Producto) {
                                                    case "Cebolla Blanca Primera":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Cebolla Blanca Segunda":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Cebolla Amarilla":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Limon":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Jalapeno":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Papa":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Aguacate":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Tomate":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Cilantro":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Lechuga":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Papa Galeana":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Mango":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Platano":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Manzana":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Chile Japones":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                    case "Chile de Arbol":
                                                        if (valorEscrito > Integer.parseInt(cantidadProducto.get(position))) {
                                                            cantidad.setText("");
                                                        }
                                                        cantidad.setFilters(new InputFilter[]{new MetodosUtiles.InputFilterMinMax("1", cantidadProducto.get(position))});
                                                        break;
                                                }
                                            }
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

    //Revisar conexion internet
    @Override
    protected void onResume() {
        //Metodo para revisar merma y grado de madurez
        BaseDeDatos BD = new BaseDeDatos();
        BD.RevisarCada15SegundosEstadoGrados(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        BaseDeDatos BD = new BaseDeDatos();
        BD.DetenerContadorMerma();
        super.onPause();
    }
}
