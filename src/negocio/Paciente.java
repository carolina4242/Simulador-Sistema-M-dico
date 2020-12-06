package negocio;

/**
 * Clase Paciente del modelo de negocio Representa a todos los pacientes del
 * sistema. Implementa un compareTo() que ordena por documento, tal cual como se
 * pidio en el enunciado.
 *
 * @author fnang
 */
public class Paciente extends Persona implements Comparable<Paciente> {

    private int nroObraSocial;

    public Paciente(int nroObraSocial, int documento, String nombreApellido) {
        super(documento, nombreApellido);
        this.nroObraSocial = nroObraSocial;
    }

    public int getNroObraSocial() {
        return nroObraSocial;
    }

    public void setNroObraSocial(int nroObraSocial) {
        this.nroObraSocial = nroObraSocial;
    }

    @Override
    public String toString() {
        return "Paciente: Nro. ObraSocial: " + nroObraSocial + "\t" + super.toString();
    }

    @Override
    public int compareTo(Paciente t) {
        if (getNroObraSocial() < t.getNroObraSocial()) {
            return 1;
        } else if (this.getNroObraSocial() > t.getNroObraSocial()) {
            return (-1);
        } else {
            return 0;
        }
    }

}
