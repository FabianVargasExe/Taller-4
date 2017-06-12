

package taller4app;


public class ListaPersonas {
    
    private NodoPersona first;
    private NodoPersona last;

    public ListaPersonas() {
        
        this.first = null;
        this.last = null;
    }
      
    public void insertarOrdenado(Persona persona){
            
            NodoPersona nuevo = new NodoPersona(persona);
            NodoPersona aux = first;
            // si es mas chico avanza si se inserta un Z palabra mas grande es lo mismo
            // que insertarla a final
            while(aux != null && aux.getPersona().getNombre().compareTo(persona.getNombre()) < 0){
               aux = aux.getNext();     
            }
            if (aux != null){
                NodoPersona ant = aux.getPrev();
                if (aux == first){
                    first = nuevo;
                   /* aux.setPrev(nuevo);
                    nuevo.setNext(aux);  */    
                }else{
                ant.setNext(nuevo);
                nuevo.setPrev(ant);
            //    aux.setPrev(nuevo);
                }

            }else{
                if(first == null){
                   first = nuevo;
                }
                else{
                   last.setNext(nuevo);
                   nuevo.setPrev(last);   
                }
            last = nuevo;
        }
    
    }
    
    public boolean eliminar(String alias){
        NodoPersona aux = first;
        while(aux != null && !aux.getPersona().getNombre().equals(alias)){
            aux = aux.getNext();
        }
        if (aux != null){
            if (first == aux){
                first = first.getNext();
            }else{
               aux.getPrev().setNext(aux.getNext()); 
            }
            if (last == aux){
                last = last.getPrev();
            }else{
                aux.getNext().setPrev(aux.getPrev());
            }
            return true;
        }
        return false;
    }    
    
    
}
