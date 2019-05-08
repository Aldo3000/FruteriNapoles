package aplicacion.android.app.betusto.fruterinapoles;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Activity_Inventario extends AppCompatActivity {

    private PieChart pieChart1, pieChart2, pieChart3, pieChart4, pieChart5, pieChart6, pieChart7, pieChart8, pieChart9, pieChart10, pieChart11, pieChart12, pieChart13, pieChart14,
            pieChart15, pieChart16;
    private DatabaseReference Database;
    private boolean noMasDataChanges = false;
    private List CantidadDeSnapshots = new ArrayList();
    public String Cantidad;


    SharedPreferences sharedPreferences;
    VariablesEstaticas VE = new VariablesEstaticas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__inventario);
        pieChart1 = findViewById(R.id.cebollablancaprimera);
        pieChart2 = findViewById(R.id.cebollablancasegunda);
        pieChart3 = findViewById(R.id.cebollaamarilla);
        pieChart4 = findViewById(R.id.limon);
        pieChart5 = findViewById(R.id.jalapeno);
        pieChart6 = findViewById(R.id.papa);
        pieChart7 = findViewById(R.id.aguacate);
        pieChart8 = findViewById(R.id.tomate);
        pieChart9 = findViewById(R.id.cilantro);
        pieChart10 = findViewById(R.id.lechuga);
        pieChart11 = findViewById(R.id.galeana);
        pieChart12 = findViewById(R.id.mango);
        pieChart13 = findViewById(R.id.platano);
        pieChart14 = findViewById(R.id.manzana);
        pieChart15 = findViewById(R.id.chilejapones);
        pieChart16 = findViewById(R.id.chilearbol);

        Database = FirebaseDatabase.getInstance().getReference("Entradas");

        Database.keepSynced(true);
        sharedPreferences = getSharedPreferences(VariablesEstaticas.SHARED_PREFS, Context.MODE_PRIVATE);
        VE.CargarDatos(sharedPreferences);
        /*ArrayList NoOfEmp = new ArrayList();

        NoOfEmp.add(new Entry(945, 0));
        NoOfEmp.add(new Entry(1040, 1));
        /*NoOfEmp.add(new Entry(1133f, 2));
        NoOfEmp.add(new Entry(1240f, 3));
        NoOfEmp.add(new Entry(1369f, 4));
        NoOfEmp.add(new Entry(1487f, 5));
        NoOfEmp.add(new Entry(1501f, 6));
        NoOfEmp.add(new Entry(1645f, 7));
        NoOfEmp.add(new Entry(1578f, 8));
        NoOfEmp.add(new Entry(1695f, 9));
        PieDataSet dataSet = new PieDataSet(NoOfEmp, "");

        ArrayList year = new ArrayList();

        pieChart1.setDescription("Cebolla");

        year.add("");
        year.add("Stock");
        /*year.add("2010");
        year.add("2011");
        year.add("2012");
        year.add("2013");
        year.add("2014");
        year.add("2015");
        year.add("2016");
        year.add("2017");
        PieData data = new PieData(year, dataSet);
        pieChart1.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart1.animateXY(1000, 1000);*/

        LecturaBaseDeDatos();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void LecturaBaseDeDatos() {
        Database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final int CantidadTotalDeSnaps = (int) dataSnapshot.getChildrenCount();
                if (dataSnapshot.getValue() != null) { //*No se puede usar equals en null aunque sea string*
                    for (final DataSnapshot snapshot : dataSnapshot.getChildren()) //Recorremos cada campo de la tabla Usuarios
                    {
                        Database.child(snapshot.getKey()).child("NombreProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final String datochido = dataSnapshot.getValue().toString();
                                if (datochido.equals("Cebolla Blanca Primera")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 8 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart1.setDescription("Ceb. Blanca. Prim.");
                                                    year.add("Usado");
                                                    year.add("Disp.");

                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart1.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart1.animateXY(1000, 1000);

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Cebolla Blanca Segunda")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 20 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart2.setDescription("Ceb. Blanca. Seg.");
                                                    year.add("Usado");
                                                    year.add("Disp.");
                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart2.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart2.animateXY(1000, 1000);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Cebolla Amarilla")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 20 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart3.setDescription("Ceb. Amarilla.");
                                                    year.add("Usado");
                                                    year.add("Disp.");
                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart3.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart3.animateXY(1000, 1000);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Limon")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 20 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart4.setDescription("Limon. Bulto.");
                                                    year.add("Usado");
                                                    year.add("Disp.");
                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart4.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart4.animateXY(1000, 1000);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Jalapeno")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 8 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart5.setDescription("Jalapeño");
                                                    year.add("Usado");
                                                    year.add("Disp.");
                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart5.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart5.animateXY(1000, 1000);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Papa")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 12 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart6.setDescription("Papa.");
                                                    year.add("Usado");
                                                    year.add("Disp.");
                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart6.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart6.animateXY(1000, 1000);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Aguacate")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 8 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart7.setDescription("Aguacate.");
                                                    year.add("Disp.");
                                                    year.add("Usado");

                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart7.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(128, 128, 128), Color.rgb(255, 0, 0)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(128, 128, 128), Color.rgb(255, 255, 0)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(128, 128, 128), Color.rgb(0, 143, 57)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart7.animateXY(1000, 1000);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Tomate")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 20 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();

                                                    year.add("Usado");
                                                    year.add("Disp.");
                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart8.setData(data);
                                                    pieChart8.setDescription("Tomate.");
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart8.animateXY(1000, 1000);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Cilantro")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 7 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart9.setDescription("Cilantro.");
                                                    year.add("Usado");
                                                    year.add("Disp.");
                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart9.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart9.animateXY(1000, 1000);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Lechuga")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 3 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart10.setDescription("Lechuga.");
                                                    year.add("Usado");
                                                    year.add("Disp.");
                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart10.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart10.animateXY(1000, 1000);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Papa Galeana")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 16 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart11.setDescription("Papa Geleana.");
                                                    year.add("Usado");
                                                    year.add("Disp.");
                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart11.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart11.animateXY(1000, 1000);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Mango")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 60 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart12.setDescription("Mango. Kg.");
                                                    year.add("Usado");
                                                    year.add("Disp.");
                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart12.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart12.animateXY(1000, 1000);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Platano")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 60 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart13.setDescription("Platano. Kg");
                                                    year.add("Usado");
                                                    year.add("Disp.");
                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart13.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart13.animateXY(1000, 1000);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Chile Japones")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 5 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart14.setDescription("Chile Jap. Kg");
                                                    year.add("Usado");
                                                    year.add("Disp.");
                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart14.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart14.animateXY(1000, 1000);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Manzana")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 60 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart15.setDescription("Manzana. Kg");
                                                    year.add("Usado");
                                                    year.add("Disp.");
                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart15.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart15.animateXY(1000, 1000);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Chile de Arbol")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    int resultado = 60 - num1;
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    NoOfEmp.add(new Entry(num1, 0));
                                                    NoOfEmp.add(new Entry(resultado, 1));
                                                    PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
                                                    ArrayList year = new ArrayList();
                                                    pieChart15.setDescription("Manzana. Kg");
                                                    year.add("Usado");
                                                    year.add("Disp.");
                                                    PieData data = new PieData(year, dataSet);
                                                    pieChart15.setData(data);
                                                    if (Madurez.equals("Red")) {

                                                        final int[] MY_COLORS = {Color.rgb(255, 0, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Yellow")) {
                                                        final int[] MY_COLORS = {Color.rgb(255, 255, 0), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    } else if (Madurez.equals("Green")) {
                                                        final int[] MY_COLORS = {Color.rgb(0, 143, 57), Color.rgb(128, 128, 128)};
                                                        ArrayList<Integer> colors = new ArrayList<Integer>();

                                                        for (int c : MY_COLORS) colors.add(c);

                                                        dataSet.setColors(colors);
                                                    }
                                                    pieChart15.animateXY(1000, 1000);


                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else {
                                    Toast.makeText(Activity_Inventario.this, "Chale", Toast.LENGTH_SHORT).show();
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                //Toast.makeText(RegisterActivity.this, "Ocurrio un error al intentar acceder a la base de datos", Toast.LENGTH_SHORT).show();
                            }
                        });
                        //Si no hay ningun usuario en la base de datos
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Activity_Inventario.this, "Ocurrio un error al intentar acceder a la base de datos", Toast.LENGTH_SHORT).show();
            }
        });


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
        //Detener deteccion de merma y grado de madurez
        BaseDeDatos BD = new BaseDeDatos();
        BD.DetenerContadorMerma();
        super.onPause();
    }

}
