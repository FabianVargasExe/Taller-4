

package taller4app;


public class NodoInscripcion {
    
    // malo
    Inscripcion inscripcion; 
    
    private NodoInscripcion next; 
    private NodoInscripcion previo; 

    public NodoInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
        next = null;
        previo = null;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public NodoInscripcion getNext() {
        return next;
    }

    public void setNext(NodoInscripcion next) {
        this.next = next;
    }

    public NodoInscripcion getPrevio() {
        return previo;
    }

    public void setPrevio(NodoInscripcion previo) {
        this.previo = previo;
    }         
}
