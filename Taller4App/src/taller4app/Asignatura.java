


package taller4app;


public class Asignatura {
    
    private String Codigo;
    private String Nombre;
    private int CantidadPersonas;
    private ListaPersonas listaPersonas;

    public Asignatura(String Codigo, String Nombre, int CantidadPersonas) {
        this.Codigo = Codigo;
        this.Nombre = Nombre;
        this.CantidadPersonas = CantidadPersonas;
        listaPersonas = new ListaPersonas();
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getCantidadPersonas() {
        return CantidadPersonas;
    }

    public void setCantidadPersonas(int CantidadPersonas) {
        this.CantidadPersonas = CantidadPersonas;
    }

    public ListaPersonas getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(ListaPersonas listaPersonas) {
        this.listaPersonas = listaPersonas;
    }
    
    
    
    
    
    
}
