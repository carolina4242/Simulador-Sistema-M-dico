package negocio;

/**
 * Clase Doctor del modelo de negocio. Representa todo objeto que sea un doctor.
 * Implementa un compareTo() donde ordena por nro. de matricula, tal cual se
 * pidio en el enunciado.
 *
 * @author fnang
 */
public class Doctor extends Persona implements Comparable<Doctor> {

    private int matricula;

    public Doctor(int matricula, int documento, String nombreApellido) {
        super(documento, nombreApellido);
        this.matricula = matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Doctor: Matricula: " + matricula + "\t" + super.toString();
    }

    @Override
    public int compareTo(Doctor t) {
        int result = this.getNombreApellido().compareTo(t.getNombreApellido()); {
            if (result > 0) {return 1;}
            else if (result < 0) {return (-1);}
            else {return 0;}
    
    }
    }

}
