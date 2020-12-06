package jint_act07;

import dao.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import negocio.*;

/**
 * Clase Principal donde se encuentra el metodo main(). Encontraremos todo lo
 * que el usuario verá por consola.
 *
 * @author fnang
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Creamos una instancia de la interfaz DAO a traves del patron Factory.
        PersonaDAOFactory factory = new PersonaDAOFactory();
        PersonaDAO dao = factory.crearPersona();

        //Hago un menu de opciones que pase como argumento un dao para que pueda utilizarlo
        menuDeOpciones(dao);

    }

    public static void menuDeOpciones(PersonaDAO dao) {
        int opciones;

        do {
            System.out.println("================================");
            System.out.println("1 - Cargar personas");
            System.out.println("2 - Modificar persona");
            System.out.println("3 - Dar de baja a persona");
            System.out.println("4 - Buscar persona por documento");
            System.out.println("5 - Listar personas");
            System.out.println("6 - Filtrar personas");
            System.out.println("7 - Salir");
            System.out.println("================================");
            System.out.println("Ingresar opcion: ");
            opciones = Consola.readInt();

            switch (opciones) {
                case 1:
                    Persona nuevaPersona = cargarPersona();
                    if (nuevaPersona != null) {
                        dao.agregar(nuevaPersona);
                        System.out.println("¡" + nuevaPersona.getNombreApellido() + " cargado con exito!");
                    }
                    break;
                case 2:
                    Persona modificar = existePersona(dao);
                    if (modificar != null) {
                        if (modificarPersona(modificar)) {
                            dao.modificar(modificar);
                        }
                    } else {
                        System.out.println("No existe la persona");
                    }
                    break;
                case 3:
                    Persona borrar = existePersona(dao);
                    if (borrar != null) {
                        dao.borrar(borrar.getDocumento());
                    } else {
                        System.out.printf("Lo siento, la persona con el documento ingresado no se encontro%n");
                    }
                    break;
                case 4:
                    Persona buscar = existePersona(dao);
                    if (buscar != null) {
                        System.out.printf("¡Persona encontrada! %nSus datos son: %n%s%n", buscar.toString());
                    } else {
                        System.out.printf("Lo siento, la persona con el documento ingresado no se encontro%n");
                    }
                    break;
                case 5:
                    System.out.println("___Listado___");
                    Persona[] personas = dao.obtenerTodasPersonas();
                    for (Persona persona : personas) {
                        System.out.println(persona + "\n");
                    }
                    System.out.println("_____________");
                    break;
                case 6: 
                   String encontradas = filtrar(dao);
                    if (encontradas != " ") {
                        System.out.println("Se encontraron las siguientes personas con las iniciales ingresadas: " + encontradas);    
                    }
                    else {
                        System.out.println("No se encontraron coincidencias con las iniciales ingresadas.");
                    }
                    break;
                   
            }

        } while (opciones != 7);
    } //Fin del metodo menuDeOpciones()

    public static Persona cargarPersona() {

        System.out.println("Ingrese el documento: ");
        int documento = Consola.readInt();

        System.out.println("Ingrese nombre y apellido: ");
        String nomApe = Consola.readLine();

        System.out.println("¿Es un (P)aciente o un (D)octor?");
        String letra = Consola.readLine();
        switch (letra.toUpperCase()) {
            case "P":
                System.out.println("Ingrese el nro. de obra social: ");
                int obraSocial = Consola.readInt();
                return new Paciente(obraSocial, documento, nomApe);
            case "D":
                System.out.println("Ingrese la matricula: ");
                int mat = Consola.readInt();
                return new Doctor(mat, documento, nomApe);
            default:
                System.out.println("ERROR. Por favor, cargue correctamente los datos.");
                return null;
        }

    } //Fin de cargarPersona()

    public static Persona existePersona(PersonaDAO dao) {
        System.out.println("Ingresa un documento: ");

        int documento = Consola.readInt();

        Persona personaBuscada = dao.buscarPorDNI(documento);

        return personaBuscada;
    } //Fin existePersona()

    public static boolean modificarPersona(Persona personaOriginal) {
        boolean recibioModificaciones = false;
        System.out.println("¿Qué desea modificar?");
        System.out.println("1 - Nombre y apellido");
        System.out.println("2 - Obra social (Si es paciente)");
        System.out.println("3 - Matricula (si es doctor)");
        System.out.println("Ingresar una opcion: ");
        int opcion = Consola.readInt();

        switch (opcion) {
            case 1:
                System.out.println("Ingrese un nuevo nombre y apellido (Enter para omitir): ");
                String nuevoNombre = Consola.readLine();
                if (!nuevoNombre.equals("")) {
                    personaOriginal.setNombreApellido(nuevoNombre);
                    recibioModificaciones = true;
                }
                break;
            case 2:
                if (personaOriginal instanceof Paciente) {
                    System.out.println("Ingrese un nuevo nro. de Obra Social (0 para omitir): ");
                    int nuevoNroOS = Consola.readInt();
                    if (nuevoNroOS != 0) {
                        ((Paciente) personaOriginal).setNroObraSocial(nuevoNroOS);
                        recibioModificaciones = true;
                    }
                } else {
                    System.out.println("Lo siento, la persona cargada no es un paciente.");
                }
                break;
            case 3:
                if (personaOriginal instanceof Doctor) {
                    System.out.println("Ingrese un nuevo nro. de matricula (0 para omitir): ");
                    int nuevaMat = Consola.readInt();
                    if (nuevaMat != 0) {
                        ((Doctor) personaOriginal).setMatricula(nuevaMat);
                        recibioModificaciones = true;
                    }
                } else {
                    System.out.println("Lo siento, la persona cargada no es un doctor.");
                }
                break;
        }

        return recibioModificaciones;
    } //Finaliza de modificarPersona()
    
     public static String filtrar (PersonaDAO dao) {
        System.out.println("Ingrese las iniciales de la persona que busca: ");
        String iniciales = Consola.readLine();
        Persona[] per = dao.obtenerTodasPersonas();
        String coincidencias = " ";
        for (Persona elem : per) {  
        String nom = elem.getNombreApellido();
        Pattern pat = Pattern.compile("^".concat(iniciales));
        Matcher match = pat.matcher(nom);
        if (match.find()) { 
            coincidencias += ("\n" + elem);
            }
        
        }return coincidencias;
        
    } //Fin del metodo filtrar() 
         

} //Fin de la clase
