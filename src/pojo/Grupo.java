package pojo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Grupo")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ident;

    @Column(length = 50, nullable = false)
    private String nombre;

    // Relación 1 Grupo -> Muchos Alumnos
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alumno> alumnos;  // CORREGIDO ✅

    public Grupo() {}

    public Grupo(String nombre) {  // CORREGIDO ✅ (No asignar ID manualmente)
        this.nombre = nombre;
    }

    public int getIdent() { return ident; }
    public void setIdent(int ident) { this.ident = ident; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<Alumno> getAlumnos() { return alumnos; }
    public void setAlumnos(List<Alumno> alumnos) { this.alumnos = alumnos; }

    @Override
    public String toString() {
        return "Grupo [ident=" + ident + ", nombre=" + nombre + ", alumnos=" + alumnos + "]";
    }
}
