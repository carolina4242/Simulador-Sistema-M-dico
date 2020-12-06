/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author fnang
 */
public class PersonaDAOFactory {
    
    public PersonaDAO crearPersona(){
        return new PersonaDAOEnTreeMap();
    }
}
