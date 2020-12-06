
package dao;

import negocio.Persona;

/**
 * Interfaz que respeta el patron DAO.
 * Donde todas aquellas clases que lo implanten deberan cumplir
 * con los metodos y sus respectivas firmas.
 * @author fnang
 */
public interface PersonaDAO {
    
    public void agregar(Persona per); 
    
    public void modificar(Persona per);
    
    public void borrar(int documento);
    
    public Persona buscarPorDNI(int documento);
    
    public Persona[] obtenerTodasPersonas();
}
