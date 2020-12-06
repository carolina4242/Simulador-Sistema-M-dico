package negocio;

/**
 * Clase Persona del modelo de negocio. Representa a todas las personas del
 * sistema.
 *
 * @author fnang
 */
public class Persona {

    private int documento;
    private String nombreApellido;

    public Persona(int documento, String nombreApellido) {
        this.documento = documento;
        this.nombreApellido = nombreApellido;
    }

    public int getDocumento() {
        return documento;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    @Override
    public String toString() {
        return "Documento: " + documento + ", Nombre y apellido: " + nombreApellido;
    }
}
