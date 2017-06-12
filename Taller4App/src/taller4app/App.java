/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller4app;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import ucn.ArchivoEntrada;
import ucn.Registro;
import ucn.StdIn;
import ucn.StdOut;



public class App implements IApp {
 
    
    private ListaInscripciones listaInscripciones;
    private ListaMensajes listaMensajes;

    private ListaPersonas listaPersonas;
    

    public App() {
        
        listaPersonas = new ListaPersonas();
        listaInscripciones = new ListaInscripciones();
        LinkedList listaAsignaturas = new LinkedList();
        
    }
    
    
    
    // Método de lectura de los archivos .txt
    @Override        
    public void leerInscripciones() {
             
        try {
            ArchivoEntrada in = new ArchivoEntrada("Inscripciones.txt");
            while(!in.isEndFile()){
                Registro reg = in.getRegistro();
                
                String nombre = reg.getString();
                int edad = reg.getInt();


                   listaInscripciones();
                }
     
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }

    }
    
   @Override 
   public void leerAsignaturas() {
       
            try {
            
            FileReader f = new FileReader("Asignaturas.txt");
            BufferedReader  br = new BufferedReader(f);
            
            String linea;
            
            while((linea=br.readLine())!=null){
                String[] campos = linea.split(".");
   
                int Cod = Integer.parseInt(campos[0]);
                String Nombre = campos[1];
                int CantPersona = Integer.parseInt(campos[2]);
                
                Asignatura a = new Asignatura(Cod,Nombre,CantPersona);

                listaAsignaturas.add(a);     
                
            }
            
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }
       
   
   } 
   
   
   
   @Override 
   public void leerPersonas() {
       
        try {
            
            FileReader f = new FileReader("creditos.txt");
            BufferedReader  br = new BufferedReader(f);
            
            String linea;
            
            while((linea=br.readLine())!=null){
                String[] campos = linea.split(";");
   
                String Rut = campos[0];
                String Nombre = campos[1];
                String Apellido = campos[2];
                String Correo = campos[3];
                String Estudio = campos[4];
                int Dato1 = Integer.parseInt(campos[5]);
                int Dato2 = Integer.parseInt(campos[6]);         
                
                if(Estudio.equals("alumno")){
                
                }
                if(Estudio.equals("profesor")){
                
                }
               
                int cantcuotas = Integer.parseInt(campos[4]);

                             
            }

            
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }       
   
   }    
   
   
   // Errores de Registro 
   @Override 
   public void RF1() {
   
   }  
   
   // Despliegue datos de una asignatura
   @Override 
   public void RF2() {
   
   } 
   
   // Enviar un mensaje
   @Override 
   public void RF3() {
   
   }    
   
   //Registrar una nueva persona en una asignatura.
   @Override 
   public void RF4() {
   
   } 
   
   // Elimina una persona de una asignatura
   @Override 
   public void RF5() {
   
   } 
   
   // Accede al registro de mensajes de una persona
   @Override 
   public void RF6() {
   
   }    
    
}
 
    



    
    
    

