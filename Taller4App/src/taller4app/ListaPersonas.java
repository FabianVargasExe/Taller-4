

package taller4app;


public class ListaPersonas {
    
    private NodoPersona first;
    private NodoPersona last;

    public ListaPersonas() {
        
        this.first = null;
        this.last = null;
    }

    public NodoPersona getFirst() {
        return first;
    }

    public void setFirst(NodoPersona first) {
        this.first = first;
    }

    public NodoPersona getLast() {
        return last;
    }

    public void setLast(NodoPersona last) {
        this.last = last;
    }
       
    public void insertarOrdenado(Persona persona){
            
            NodoPersona nuevo = new NodoPersona(persona);
            NodoPersona aux = first;

            while(aux != null && aux.getPersona().getNombre().compareTo(persona.getNombre()) < 0){
               aux = aux.getNext();     
            }
            if (aux != null){
                NodoPersona ant = aux.getPrev();
                if (aux == first){
                    first = nuevo;

                }else{
                ant.setNext(nuevo);
                nuevo.setPrev(ant);

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
        
    public void ingresaAlumno(String rut, String nombre, String apellido, String correo, String Alias, int da1, int da2){
    Persona a = new Alumno(rut, nombre, apellido, correo, Alias, da1, da2);
    this.insertarOrdenado(a);
    }
    
    public void ingresaProfesor(String rut, String nombre, String apellido, String correo, String Alias, int da1, int da2){
    Persona p = new Profesor(rut, nombre, apellido, correo, Alias, da1, da2);
    this.insertarOrdenado(p);
    }
    
    public boolean buscar(String a) { 
        NodoPersona curr = first;
       
        while(curr != null && !curr.getPersona().getAlias().equals(a)) {
            curr = curr.getNext();
        }
        if( curr != null) {
            return true;
        } else {
            return false;
        }
    }
    
     public Persona buscarI(String a) { 
        NodoPersona curr = first;
        
        while(curr != null && !curr.getPersona().getAlias().equals(a)) {
            curr = curr.getNext();
        }
        if( curr != null) {
            return curr.getPersona();
        } 
        else{
        return null;
        }
        
    }   
     
    public void insertarPrincipio(Persona p ) { 
        NodoPersona curr = new NodoPersona (p);
        if( first == null ) {
            last = curr;
        } else {
            first.setPrev(curr);
        }
        curr.setNext(first);
        first = curr;
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
