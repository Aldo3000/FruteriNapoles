package aplicacion.android.app.betusto.fruterinapoles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_Acerca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__acerca);
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
