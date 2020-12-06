/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import negocio.Persona;
import java.util.Set;
import java.util.TreeSet;
import negocio.Doctor;
import negocio.Paciente;
import java.util.Map;
import java.util.TreeMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Usuario
 */
public class PersonaDAOEnTreeMap implements PersonaDAO {
    private static Map<Integer,Persona> arregloPersona = new TreeMap<>();
    
  
    public void agregar (Persona per) {
        int dni = per.getDocumento();
        arregloPersona.put(dni, per);
        
        
    }
    
    public void agregarPer (int dni, Persona per) { 
        arregloPersona.put(dni, per);
    }
    
    public void modificar(Persona per) {
        Set<Integer> modificables = arregloPersona.keySet();
        for(Integer elem : modificables){
            if(elem == per.getDocumento()){
                arregloPersona.replace(elem, per);
                System.out.println("¡Modificado con éxito!");
                break;
            }
        }
        
    } 
    
    public void borrar(int documento) {
         Set<Integer> modificables = arregloPersona.keySet();
             for(Integer elem : modificables){
             if(elem == documento) {
                arregloPersona.remove(elem);
                System.out.println("¡Se dio de baja al paciente con éxito!");
                break;
            }
        }        
        
    }
    
    public Persona buscarPorDNI(int documento) {
        Persona buscada = null;
        Set<Integer> modificables = arregloPersona.keySet();
        for(Integer elemento : modificables) {
            if(elemento == documento){
                buscada = arregloPersona.get(documento); 
                break; 
            }
        }
        
        return buscada;
    }
    
    public Persona[] obtenerTodasPersonas() {
        //Lista ordenada de pacientes
        
        Set<Paciente> pacientesOrdenados = new TreeSet<>();
        for (Persona elem : arregloPersona.values()) {
            if(elem instanceof Paciente) 
                pacientesOrdenados.add((Paciente)elem);
        }
        
        //Lista ordenadas de doctores
      
        Set<Doctor> doctoresOrdenados = new TreeSet<>();
        for (Persona elem : arregloPersona.values()) {
            if(elem instanceof Doctor)
                doctoresOrdenados.add((Doctor)elem);
        }
        
        //Creo un arreglo del mismo tamanio del arreglo
        //Cada vez que se agregue uno nuevo, se llama a este metodo
        //y se crean nuevas instancias.
        Persona[] personas = new Persona[arregloPersona.size()];
        
        int i = 0;
        for(Persona pers : pacientesOrdenados){
            personas[i] = pers;
            i++;
        }        
        
        //Despues de cargas a los pacientes, se carga a los doctores
        //Notar que el ultimo indice de los pacientes, el proximo indice
        //corresponde al de doctores.
        int j = i++; 
        for(Persona pers: doctoresOrdenados){
            personas[j] = pers;
            j++;
        }
        
        
        return personas;
        
    }
    
  
    
}
    

