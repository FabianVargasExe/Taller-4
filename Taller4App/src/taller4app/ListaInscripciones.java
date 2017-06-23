
package taller4app;


public class ListaInscripciones {
    
    private NodoInscripcion first;
    
    public ListaInscripciones() {
        first = null;
    }
    
    public void ingresar(Inscripcion m) {
        NodoInscripcion curr = new NodoInscripcion(m);
        if(first == null) {
            first = curr;
        } else {
            NodoInscripcion prev = first;
            while( prev.getNext() != first) {
                prev = prev.getNext();
            }
            prev.setNext(curr);
        }
        curr.setNext(first);
    }
    
    public boolean verificacionCantRamos(String d) {
        NodoInscripcion curr = first;
        int cont = 0;
        while( curr != null ) {
            curr = curr.getNext();
            if(curr.getInscripcion().getAlias().equals(d)){
                cont++;   
            }
        }
        if (cont >= 2){
           return true;
        } else {
            return false;
        }
    }
    public boolean eliminar(String a) {
        NodoInscripcion curr = first;
        NodoInscripcion prev = first;
        while( curr!=null && !curr.getInscripcion().getAlias().equals(a)) {
            prev = curr;
            curr= curr.getNext();
        }
          
        if( curr != null) {
           if( curr == first) {
               first = first.getNext();
           } else {
               prev.setNext(curr.getNext()); // al anterior le seteo su siguiente al siguiente del current ( by pass )
           }
        }
        return true;
    }
    
    public NodoInscripcion getFirst() {
        return first;
    }
}
