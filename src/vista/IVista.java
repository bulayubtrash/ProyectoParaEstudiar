package vista;

import java.util.ArrayList;

import pojo.Alumno;
import pojo.Grupo;

public interface IVista {
	
	void menu();
	
	Alumno datosAlumno();
	
	Grupo datosGrupo();
	
	int pedirInt();
	
	String pedirString();
	
	void mostrarAlumnos(ArrayList<Alumno> aLista);
	
	void mostrarGrupos(ArrayList<Grupo> aLista);

}
