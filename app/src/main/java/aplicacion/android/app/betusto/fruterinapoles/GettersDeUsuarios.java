package aplicacion.android.app.betusto.fruterinapoles;

public class GettersDeUsuarios {
    public String Usuario;
    public String Correo;
    public String Contraseña;
    public String CuentaAbierta;
    public String Administrador;

    public GettersDeUsuarios() {

    }

    //Esto mas de ser un getter, es un setter en realidad
    public GettersDeUsuarios(String Usuario, String Correo, String Contraseña, String CuentaAbierta, String Administrador) {
        this.Usuario = Usuario;
        this.Correo = Correo;
        this.Contraseña = Contraseña;
        this.CuentaAbierta = CuentaAbierta;
        this.Administrador = Administrador;
    }
}
class GettersDeEntradas{
    public  String Timestamp;
    public String NombreProducto;
    public String GradoMadurez;
    public String FechaEntrada;
    //public String FechaMerma;
    public String CantidadProducto;
    public String GradoMadurezOriginal;

    public GettersDeEntradas(){}

    public GettersDeEntradas(String Timestamp, String NombreProducto, String GradoMadurez, String FechaEntrada/*, String FechaMerma*/, String CantidadProducto,
    String GradoMadurezOriginal){
        this.Timestamp = Timestamp;
        this.NombreProducto = NombreProducto;
        this.GradoMadurez = GradoMadurez;
        this.FechaEntrada = FechaEntrada;
        //this.FechaMerma = FechaMerma;
        this.CantidadProducto = CantidadProducto;
        this.GradoMadurezOriginal = GradoMadurezOriginal;
    }
}

class GettersDeMerma{
    public  String Timestamp;
    public String NombreProducto;
    public String FechaDeEntradaDelProducto;
    public String FechaMerma;
    public String CantidadDelProductoTirado;

    public GettersDeMerma(){}

    public GettersDeMerma(String Timestamp, String NombreProducto, String FechaDeEntradaDelProducto, String FechaMerma, String CantidadDelProductoTirado){
        this.Timestamp = Timestamp;
        this.NombreProducto = NombreProducto;
        this.FechaDeEntradaDelProducto = FechaDeEntradaDelProducto;
        this.FechaMerma = FechaMerma;
        this.CantidadDelProductoTirado = CantidadDelProductoTirado;
    }
}
