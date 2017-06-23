


package taller4app;


public class NodoPersona {
    
    Persona persona;
    private NodoPersona next;
    private NodoPersona prev;

    public NodoPersona(Persona persona) {
        this.persona = persona;
        next = null;
        prev = null;
    }

    public Persona getPersona() {
        return persona;
    }
    public NodoPersona getNext() {
        return next;
    }

    public void setNext(NodoPersona next) {
        this.next = next;
    }

    public NodoPersona getPrev() {
        return prev;
    }

    public void setPrev(NodoPersona prev) {
        this.prev = prev;
    }
    
    public double CalcularNota(int CantMsjRecibidos, int CantMsjEnviados){
        float X = ((CantMsjEnviados)/(CantMsjRecibidos))*100;
        if(X<33){
        return 2.5;}
        if(X>66){
        return 7.0;}
        else{return 5.0;}  
    }
    
}
