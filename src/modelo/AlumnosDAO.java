package modelo;

import java.util.ArrayList;

import pojo.Alumno;
import pojo.Grupo;

public interface AlumnosDAO {
	
	void insertarAlumno(Alumno a1);
	
	void insertarGrupo(Grupo g1);
	
	ArrayList<Alumno> recogerAlumnos();
	
	ArrayList<Grupo> recogerGrupos();
	
	void modificarNombrePorPK(String nombre, int pk);
	
	void eliminarPorPK(int pk);
	
	void cambiarGrupo(int nia, int grupo_id);
	

}
