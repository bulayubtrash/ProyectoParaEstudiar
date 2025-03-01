package pojo;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name ="alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nia;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String apellido;

    @Column(length = 1, nullable = false)
    private char genero;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaNac;

    @ManyToOne
    @JoinColumn(name = "grupo_id")  // CORREGIDO ✅
    private Grupo grupo;

    public Alumno() {}

    public Alumno(String nombre, String apellido, char genero, Date fechaNac, Grupo grupo) { // CORREGIDO ✅
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.fechaNac = fechaNac;
        this.grupo = grupo;
    }

    public int getNia() { return nia; }
    public void setNia(int nia) { this.nia = nia; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public char getGenero() { return genero; }
    public void setGenero(char genero) { this.genero = genero; }

    public Date getFechaNac() { return fechaNac; }
    public void setFechaNac(Date fechaNac) { this.fechaNac = fechaNac; }

    public Grupo getGrupo() { return grupo; }
    public void setGrupo(Grupo grupo) { this.grupo = grupo; }

    @Override
    public String toString() {
        return "Alumno [nia=" + nia + ", nombre=" + nombre + ", apellido=" + apellido + ", genero=" + genero
                + ", fechaNac=" + fechaNac + ", grupo=" + grupo + "]";
    }
}
