


package taller4app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import ucn.ArchivoEntrada;
import ucn.Registro;
import ucn.StdIn;
import ucn.StdOut;


public class App implements IApp {
 
    
    private ListaInscripciones listaInscripciones;
    private ListaMensajes listaMensajes;
    private LinkedList listaAsignaturas;
    private ListaPersonas listaPersonas;
    private LinkedList registroErrores;
    

    public App() {
        
        listaInscripciones = new ListaInscripciones();
        listaAsignaturas = new LinkedList();
        listaPersonas = new ListaPersonas();
        registroErrores = new LinkedList();
        listaMensajes = new ListaMensajes();
        
    }

   // Método de lectura de los archivos .txt

    
    @Override 
    public void leerAsignaturas() {
       
            try {
            
            FileReader f = new FileReader("asignaturas.txt");
            BufferedReader  br = new BufferedReader(f);
            
            String linea;
            
            while((linea=br.readLine())!=null){
                
                String[] campos = linea.split("\\.");
   
                String Cod = campos[0];
                String Nombre = campos[1];
                int CantPersona = Integer.parseInt(campos[2]);
                
                Asignatura a = new Asignatura(Cod,Nombre,CantPersona);

                if (!Cod.matches("[0-9]*")){
                    registroErrores.add(new RegistroError("Asignatura","codigo",Cod,"2"));
                } else {               
                    listaAsignaturas.add(a);                  
                } 
            } 
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }
   } 
     
    @Override 
    public void leerPersonas() {
       
        try {
            
            FileReader f = new FileReader("personas.txt");
            BufferedReader  br = new BufferedReader(f);
            
            String linea;
            
            while((linea=br.readLine())!=null){
                String[] campos = linea.split("\\\\");
   
                String Rut = campos[0];
                String Nombre = campos[1];
                String Apellido = campos[2];
                String Correo = campos[3];
                String Estudio = campos[4];
                int Dato1 = Integer.parseInt(campos[5]);
                int Dato2 = Integer.parseInt(campos[6]); 
                
                // Contador, que si llega al final del metodo conservando el valor 0 se ingresara a la lista de personas
                int ValidadorIngreso = 0;
                int n = Correo.indexOf("@");
                String Alias =  Correo.substring(0,n); 


                // Verificación Datos
                
                // entrega el digito codificador 
                String ultimoCaracter = Rut.substring(Rut.length()-1);
 
                // entrega una cadena a partir de @
                
                String Dominio =  Correo.substring(0,n);
                
                // Entrega el rut sin el digito verificador
                String RutSinDigito = Rut.substring(0, Rut.length()-2);
                   
              //  Long.parseLong(Dominio);
                
                // si esta bien se ingresa
                
                // verificar primero de que sean solo numeros
                if ((!RutSinDigito.matches("[0-9]+"))||(!ultimoCaracter.matches("[0-9]+"))){
                    registroErrores.add(new RegistroError("Persona","Rut",Rut,"1"));
                    ValidadorIngreso++;
                }
                     
                // Validador Dominio Mail, si no coincide se ingresa a la lista de registro de errores
             
             String auxm = Correo;
             String[] pars = auxm.split("@");
             auxm = (pars[0].trim()).substring(0, 1);  
             if ((!Correo.matches("[-\\w\\.]+@gmail.com"))||(auxm.matches("[0-9]+"))){
                    registroErrores.add(new RegistroError("Persona","Correo",Correo,"3"));
                    ValidadorIngreso++;
                }          
             
             //Validador nombre
             if(!Nombre.matches(".*[a-zA-Z]+.*[a-zA-Z]")){
             registroErrores.add(new RegistroError("Persona","Nombre",Nombre,"6"));
                ValidadorIngreso++;}
             
             //Validador apellido
             if(!Apellido.matches(".*[a-zA-Z]+.*[a-zA-Z]")){
             registroErrores.add(new RegistroError("Persona","Apellido",Apellido,"7"));
             ValidadorIngreso++;}
             
             if(ValidadorIngreso == 0){
                if(Estudio.equals("alumno")){
                Alumno alumno = new Alumno(Rut, Nombre, Apellido, Correo, Alias, Dato1, Dato2);
                StdOut.println(Rut+Nombre+Apellido+Correo+Alias+Dato1+Dato2);
                alumno.setAlias(Alias);
                listaPersonas.insertarOrdenado(alumno);
                }
             if(Estudio.equals("profesor")){
                 Profesor profesor = new Profesor(Rut, Nombre, Apellido, Correo, Alias, Dato1, Dato2);
                 StdOut.println(Rut+Nombre+Apellido+Correo+Alias+Dato1+Dato2);
                 profesor.setAlias(Alias);
                 listaPersonas.insertarOrdenado(profesor); 
                 }
            }             
          }
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }       
   } 
    
        @Override        
   public void leerInscripciones() {
             
        try {
            ArchivoEntrada in = new ArchivoEntrada("inscripciones.txt");
            while(!in.isEndFile()){
                Registro reg = in.getRegistro();
                
                String Alias = reg.getString();
                String CodigoA = reg.getString();
                
                Inscripcion i = new Inscripcion(Alias,CodigoA);
                Iterator ite = listaAsignaturas.iterator();
                Persona p = listaPersonas.buscarI(Alias);
                listaInscripciones.ingresar(i);  
                // asociar Asginaturas con personas
                 while(ite.hasNext()){
                    Asignatura Asig = (Asignatura) ite.next();
                    if(Asig.getCodigo().equals(CodigoA)){
                        Asig.getListaPersonas().insertarPrincipio(p);                        
                    }  
                   }
                }
     
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }
    }
 
   // Errores de Registro 
   @Override 
   public void RF1() {
       
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
         fichero = new FileWriter("ErroresDeRegistro.txt");
         pw = new PrintWriter(fichero);  
         
         pw.println("-------------------------------------------------------------");
         pw.println("                     Errores de Registro                   ");
         pw.println("-------------------------------------------------------------");
         pw.println("\r\n Categoria         Campo no Valido       Valor campo        Error encontrado");
         pw.println("-------------------------------------------------------------");
         
         // Se lee de la lista de registro de errores y se imprimen sus atributos
         Iterator ite = registroErrores.iterator();  
         while(ite.hasNext()){
             
            RegistroError re = (RegistroError) ite.next();
          
            pw.print(re.getCategoria() + "        ");
            pw.print(re.getCampo() + "        ");
            pw.print(re.getValorCampo() + "        ");  
            pw.print(re.errorEncontrato() + "        ");

            pw.println();
            
            }       
            StdOut.println("\nEl archivo 'ErroresDeRegistro.txt' se ha creado exitosamente.");
                   
        } catch (Exception e) {
            e.printStackTrace();
        // Se asegura de cerrar el archivo    
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }  
   }  
   
   // Despliegue datos de una asignatura
   @Override 
   public void RF2() {
       
       StdOut.println("Ingrese código de la asignatura para mostrar sus datos: ");
       StdOut.println("---------------------");
       String cod = StdIn.readString();
       
       // se recorre la lista de asignaturas hasta que concida el codigo dado
       Iterator ite = listaAsignaturas.iterator();
        while(ite.hasNext()){
             Asignatura a = (Asignatura) ite.next();
             if(a.getCodigo().equals(cod));{
            
                   // Se accede a la lista particular de personas de una asignatura
                   NodoPersona personaAsig = a.getListaPersonas().getFirst();
                   while(personaAsig != null){

                       StdOut.println(" Nombre: " + personaAsig.persona.getNombre());
                       StdOut.println(" Apellido: " + personaAsig.persona.getApellido());
                       StdOut.println(" Rut: " + personaAsig.persona.getRut());
                       StdOut.println(" Correo: " + personaAsig.persona.getCorreo());
                       StdOut.println(" Alias: " + personaAsig.persona.getAlias());
                       StdOut.println(" Nota: " );
                    
                       if(personaAsig.persona instanceof Alumno){
                           Alumno alum = (Alumno)personaAsig.persona;
                           
                          StdOut.println(" Cantidad Mensajes enviados: " +alum.getCantMsjEnviadosProfe());
                          StdOut.println(" Cantidad Asignaturas: " + alum.getCantAsignaturas());                   
                       }   
                       else{
                           Profesor profe = (Profesor)personaAsig.persona;
                          
                          StdOut.println(" Cantidad Mensajes enviados: " + profe.getCantMsjEnviados());
                          StdOut.println(" Cantidad Mensajes recibidos: " + profe.getCantMsjRecibidos()); 
                     }
                    

                    personaAsig.getNext();
                    StdOut.println("----------------------");
                   }
            }
        }                      
   } 
   // Enviar un mensaje
   @Override 
   public void RF3() {
       
      // Funcion para obtener la fecha/hora actual del computador
      Date fechaActual = new Date(); 
      DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
      DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
      int validador = 0;
      
      StdOut.println("-----ENVIAR UN MENSAJE----");
      StdOut.println("Ingrese un alias valido: " );
      String alias = StdIn.readString();
      StdOut.println("Ingrese un alias valido: " + listaPersonas.getFirst().persona.getAlias());
      
      // Si existe el alias ingresado se procedera a preguntar el codigo de la asignatura
      if(listaPersonas.buscar(alias) == true){
            StdOut.println("Ingrese el código de la asignatura del destinatario: ");
            String CodDestino = StdIn.readString();
            
            Iterator ite = listaAsignaturas.iterator();
            // Si concide el codigo dentro del listado se procedera a preguntar el destinatario       
            while(ite.hasNext()){
               Asignatura a = (Asignatura) ite.next();
              
               if(a.getCodigo().equals(CodDestino)){
                  validador++;
                   
                  NodoPersona curr = a.getListaPersonas().getFirst();
                  StdOut.println("Seleccione Destinatario:");
                  StdOut.println("---------------------");
                  
                  // Muestra por pantalla el listado de personas segun la asignatura
                  int i = 0;
                  while(curr != null ) {
                  
                     StdOut.println("["+i+"] "+ curr.persona.getAlias());
                     curr = curr.getNext();
                     i++; 
                  }
                  
                  StdOut.println("Ingrese el alias del destinatario (Aprete 0 para cancelar el mensaje)");
                  String seleccionD = StdIn.readString();
                  int verificadorMensaje = 2;
                  
                  if(!seleccionD.equals(0) && listaPersonas.buscar(seleccionD)){
                  do{    
                      StdOut.println("Ingrese asunto: ");
                      String asunto = StdIn.readString();
                      StdOut.println("Ingrese el mensaje: ");
                      String mensaje = StdIn.readString();
                      
                      String hora = formatoHora.format(fechaActual);
                      String fecha = formatoFecha.format(fechaActual);
                      Mensaje m = new Mensaje(asunto,mensaje,alias,seleccionD,hora,fecha);
                      
                      Persona Emisor = listaPersonas.buscarI(alias);
                      Persona Destinatario = listaPersonas.buscarI(seleccionD);
         
                      // si el mensaje tiene menos de 100 caracteres
                      if(mensaje.length() <= 100){
                          listaMensajes.ingresar(m);    
                          
                          // se agrega uno al contador de mensajes recibidos,enviados segun sea el tipo de persona
                          if(Emisor instanceof Profesor){     
                             int msg = ((Profesor) Emisor).getCantMsjEnviados();
                             ((Profesor) Emisor).setCantMsjEnviados(msg+1);
                          }
                          if(Emisor instanceof Alumno && Destinatario instanceof Profesor ){
                              int msg = ((Alumno) Emisor).getCantMsjEnviadosProfe();
                              ((Alumno) Emisor).setCantMsjEnviadosProfe(msg+1);
                              int msgRe = ((Profesor) Destinatario).getCantMsjRecibidos();
                              ((Profesor) Destinatario).setCantMsjRecibidos(msgRe+1);
                          }
                          StdOut.println("Mensaje enviado con éxito");
                          StdOut.println("---------------------");
                      }    
                      else{
                          StdOut.println("---SU MENSAJE TIENE MÁS DE 100 CARACTERES---");
                          StdOut.println("Presione cualquier número para volver a intentar");
                          StdOut.println("Presione [2] para salir.");
                          verificadorMensaje = StdIn.readInt();
                      }
                      }while(verificadorMensaje != 2);
                  }
                  else{
                      StdOut.println("SE HA CANCELADO EL ENVIO DEL MENSAJE");
                      StdOut.println("---------------------");
                      break;      
                  }     
               }         
            } if(validador == 0){
                StdOut.println("El código ingresado no se encuentra en el listado\n-----------------------");
            }
     }else{
          StdOut.println("EL ALIAS INGRESADO NO SE ENCUENTRA REGISTRADO"); 
          StdOut.println("---------------------------");
     } 
   }    
   
   //Registrar una nueva persona en una asignatura.
   @Override 
   public void RF4() {
       // ingresar a la lista de personas y tambien al .txt de inscripcion y persona
       StdOut.println("-----Ingresar Persona----");
       StdOut.println("----------------------------");
       StdOut.println("Ingrese la asignatura : ");
       String codasig = StdIn.readString();
       if(codasig.matches("[0-9]+") && codasig.length() > 2){
       
       StdOut.println("-----Ingresar Persona----");
       StdOut.println("Ingrese un Rut con - : ");
       String rut = StdIn.readString();
       
       //validar rut
       boolean validacion = false;
       try {
           rut =  rut.toUpperCase();
           rut = rut.replace(".", "");
           rut = rut.replace("-", "");
           int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
           char dv = rut.charAt(rut.length() - 1);
           int m = 0, s = 1;
           for (; rutAux != 0; rutAux /= 10) {
              s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
           }
           if (dv == (char) (s != 0 ? s + 47 : 75)) {
              validacion = true;
           }
           } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }

       if (validacion == true){
       StdOut.println("Ingrese un Nombre Valido: ");
       String nombre = StdIn.readString();
       
       //solo ingresar letras
       if(nombre.matches(".*[a-zA-Z]+.*[a-zA-Z]")){
       StdOut.println("Ingrese un Apellido Valido: ");
       String apellido = StdIn.readString();
             
       //solo ingresar letras
       if(apellido.matches(".*[a-zA-Z]+.*[a-zA-Z]"))
          {
       
       StdOut.println("Ingrese un Correo Gmail (No puede empezar con un numero): ");
       String correo = StdIn.readString();
       String auxm = correo;
       String[] pars = auxm.split("@");
       auxm = (pars[0].trim()).substring(0, 1);
       StdOut.println(auxm);
       
       
       //verificador correo
       if((correo.matches("[-\\w\\.]+@gmail.com")&&(!auxm.matches("[0-9]+")))){       
       StdOut.println("Ingrese 1 si es profesor o 2 si es Alumno: ");
       String tipo = StdIn.readString();
       
       if(tipo.equals("1")){
          tipo = "profesor"; 
          int cantm = 0; int cantasig = 0;
          Alumno al = new Alumno(rut, nombre, apellido, correo, tipo, cantm, cantasig);
          String alias = correo;
          String[] parts = alias.split("@");
          alias = parts[0].trim();
          
          Inscripcion ins = new Inscripcion(alias,codasig);
          listaPersonas.ingresaAlumno(rut, nombre, apellido, correo, tipo, cantm, cantasig);
          listaInscripciones.ingresar(ins);
         
          try
            {   String FILE1 = "personas.txt";
                FileWriter fw = new FileWriter(FILE1,true);
                fw.write("\r\n"+rut+"\\"+nombre+"\\"+
                        apellido+"\\"+correo+"\\"+tipo+"\\"+cantm+"\\"+cantasig);
                String FILE2 = "inscripciones.txt";
                FileWriter fw2 = new FileWriter(FILE2,true);
                fw2.write("\r\n"+alias+","+codasig);
                fw.close();
                fw2.close();
            }
            catch(IOException ioe)
            {System.err.println("IOException: " + ioe.getMessage());
            }
       
        } 
          
       if(tipo.equals("2")){
          tipo = "profesor"; 
          int cantenv = 0; int cantrec = 0;
          Profesor prof = new Profesor(rut, nombre, apellido, correo, tipo, cantrec, cantrec);
          String alias = correo;
          String[] parts = alias.split("@");
          alias = parts[0].trim();
          Inscripcion ins = new Inscripcion(alias,codasig);
          listaPersonas.ingresaProfesor(rut, nombre, apellido, correo, tipo, cantrec, cantrec);
          listaInscripciones.ingresar(ins);
       
          try
            {   String FILE1 = "personas.txt";
                FileWriter fw = new FileWriter(FILE1,true);
                fw.write("\r\n"+rut+"\\"+nombre+"\\"+
                        apellido+"\\"+correo+"\\"+tipo+"\\"+cantenv+"\\"+cantrec);
                String FILE2 = "inscripciones.txt";
                FileWriter fw2 = new FileWriter(FILE2,true);
                fw2.write("\r\n"+alias+","+codasig);
                fw.close();
                fw2.close();
            }
            catch(IOException ioe)
            {System.err.println("IOException: " + ioe.getMessage());
            }
          
          }else{
          StdOut.println("tipo no valido");
          registroErrores.add(new RegistroError("Ingreso Persona","Tipo",tipo,"9"));//para el tipo
          }
          }else{
          StdOut.println("Correo no valido");
          registroErrores.add(new RegistroError("Ingreso Correo","Correo",correo,"3"));}//para el correo
          }else{
          StdOut.println("Apellido no valido");
          registroErrores.add(new RegistroError("Ingreso Apellido","Apellido",apellido,"7"));}//para el apellido
          }else{
          StdOut.println("Nombre no valido");
          registroErrores.add(new RegistroError("Ingreso Nombre","Nombre",nombre,"6"));}//para el nombre
          }else{
          StdOut.println("Rut no valido");
          registroErrores.add(new RegistroError("Ingreso Rut","Rut",rut,"1"));}// para el rut
          }else{
          StdOut.println("Codigo Asignatura no valida");
          registroErrores.add(new RegistroError("Ingreso Asignatura","Asignatura",codasig,"5"));}// para la asignatura

   } 
   
   // Elimina una persona de una asignatura
   @Override 
   public void RF5() {
       // pedir alias y eliminar la inscripcion
       StdOut.println("\n-----Eliminar Persona----");
       StdOut.println("----------------------------");
       StdOut.println("-----Ingresar Asignatura----");
       StdOut.println("\nIngrese la asignatura : ");
       String codAsig = StdIn.readString();       
       
       Iterator ite = listaAsignaturas.iterator();
       // Si concide el codigo dentro del listado se procedera a preguntar el destinatario       
       while(ite.hasNext()){
               Asignatura a = (Asignatura) ite;
               if(a.getCodigo().equals(codAsig)){
                     StdOut.println("-----Ingresar Persona----");
                     StdOut.println("Ingrese el alias de la persona a eliminar: ");
                     String alias = StdIn.readString();
                     if(listaPersonas.buscar(alias)){
                         
                         NodoPersona curr = listaPersonas.getFirst();
                         // para obtener el objeto persona
                         while(curr != null && !curr.persona.getAlias().equals(alias)) {
                             curr = curr.getNext();
                         }
                         // Se crea la persona y solo se procedera a eliminarla si es alumno
                         Persona p = curr.getPersona();
                         if(p instanceof Alumno && listaInscripciones.verificacionCantRamos(alias)){
                             Alumno al = (Alumno)p; 
                             String elim = al.getAlias();
                             // Se elimina de la lista de inscripciones
                             listaInscripciones.eliminar(elim); 
                             // Se elimina de la lista particular de personas de la asignatura dada arriba
                             // Con esto, no estaría disponible para mandar mensajes al usar el RF1
                             a.getListaPersonas().eliminar(elim);      
                         }
                          listaPersonas.eliminar(alias);
                     }
                     else{
                     StdOut.println("Alias Ingresado No Valido");       
                     }    
               }
           }    
   } 
   
   // Accede al registro de mensajes de una persona
   @Override 
   public void RF6() {
       // buscar mensajes segun emisor y receptor
       // pedir alias y eliminar la inscripcion
       StdOut.println("----------------------Registro de mensajes----------------------");
       StdOut.println("----------------------------------------------------------------");
       StdOut.println("Ingrese un alias: ");
       String alias = StdIn.readString();
       int salir = 0;
       // Si lo encontro
       do{
       if(listaPersonas.buscar(alias) == true){
                         
            StdOut.println("----------------------------------------------------------------");
            StdOut.println("Lista de mensajes enviados y recibidos por: "+alias);
            StdOut.println("----------------------------------------------------------------");
            StdOut.println("----------------------------------------------------------------");
            //  Nodos creados para recorrer un nodo circular de un nexo
            NodoMensaje NodoFirst = listaMensajes.getFirst();
            if (NodoFirst.getNext() != null){
            NodoMensaje curr = listaMensajes.getFirst().getNext();  
           
            int cont = 0;
            // si solo tiene un dato
            if(NodoFirst != null && NodoFirst == curr){
                  if(curr.getMensaje().getEmisor().equals(alias)){
                   cont++;
                   StdOut.println("Mensaje " + cont);
                   StdOut.println("----------------------------------------------------------------");
                   StdOut.println("Fecha: " + curr.getMensaje().getFecha());
                   StdOut.println("Hora: " + curr.getMensaje().getHora() + "\n\n");
                   StdOut.println("Emisor: " + curr.getMensaje().getEmisor());
                   StdOut.println("Receptor: " + curr.getMensaje().getDestinatario() + "\n\n");
                   StdOut.println("Asunto: " + curr.getMensaje().getAsunto()+ "\n");
                   StdOut.println("Mensaje: " + curr.getMensaje().getMensaje()+ "\n");   
               }
               // Si el alias ingresado coincide con el parametro de Destinatario del objeto mensaje
               if(curr.getMensaje().getDestinatario().equals(alias)){
                   cont++;
                   StdOut.println("Mensaje " + cont);
                   StdOut.println("----------------------------------------------------------------");
                   StdOut.println("Fecha: " + curr.getMensaje().getFecha());
                   StdOut.println("Hora: " + curr.getMensaje().getHora() + "\n\n");
                   StdOut.println("Emisor: " + curr.getMensaje().getEmisor());
                   StdOut.println("Receptor: " + curr.getMensaje().getDestinatario() + "\n\n");
                   StdOut.println("Asunto: " + curr.getMensaje().getAsunto()+ "\n");
                   StdOut.println("Mensaje: " + curr.getMensaje().getMensaje()+ "\n");           
               } 
  
            }
            // si tiene mas de un dato
            else if(NodoFirst != null) {
                
               while( NodoFirst.getNext() != NodoFirst ) {
               NodoFirst = curr.getNext();
               // Si el alias ingresado coincide con el parametro de emisor del objeto mensaje
               if(curr.getMensaje().getEmisor().equals(alias)){
                   cont++;
                   StdOut.println("Mensaje " + cont);
                   StdOut.println("----------------------------------------------------------------");
                   StdOut.println("Fecha: " + curr.getMensaje().getFecha());
                   StdOut.println("Hora: " + curr.getMensaje().getHora() + "\n\n");
                   StdOut.println("Emisor: " + curr.getMensaje().getEmisor());
                   StdOut.println("Receptor: " + curr.getMensaje().getDestinatario() + "\n\n");
                   StdOut.println("Asunto: " + curr.getMensaje().getAsunto()+ "\n");
                   StdOut.println("Mensaje: " + curr.getMensaje().getMensaje()+ "\n");   
               }
               // Si el alias ingresado coincide con el parametro de Destinatario del objeto mensaje
               if(curr.getMensaje().getDestinatario().equals(alias)){
                   cont++;
                   StdOut.println("Mensaje " + cont);
                   StdOut.println("----------------------------------------------------------------");
                   StdOut.println("Fecha: " + curr.getMensaje().getFecha());
                   StdOut.println("Hora: " + curr.getMensaje().getHora() + "\n\n");
                   StdOut.println("Emisor: " + curr.getMensaje().getEmisor());
                   StdOut.println("Receptor: " + curr.getMensaje().getDestinatario() + "\n\n");
                   StdOut.println("Asunto: " + curr.getMensaje().getAsunto()+ "\n");
                   StdOut.println("Mensaje: " + curr.getMensaje().getMensaje()+ "\n");           
               } 
              }  
            }
 
           if (cont == 0){
                StdOut.println("----------No hay Mensajes Asociados de: "+alias +"--------\n" );
            }
            }else{
                StdOut.println("[1] Volver a ingresar alias de una persona");
                StdOut.println("[2] Cancelar/Salir");
                salir = StdIn.readInt();
            }
        }
        }while(salir != 2);
       // Si no lo encontro, se pregunta si quiere realizar de nuevo la operación, se saldra si ingresa un 2.
    
      }
}       

