package controlador;

import modelo.AlumnosDAO;
import vista.IVista;

public class Controlador {
	AlumnosDAO modelo;
	IVista vista;
	
	

	public Controlador(AlumnosDAO modelo, IVista vista) {
		super();
		this.modelo = modelo;
		this.vista = vista;
	}

	public void ejecutar() {

		int opcion;
		do {
			vista.menu();
			opcion = vista.pedirInt();
			
			switch (opcion) {
			case 1:
				insertarAlumno();
				break;
			case 2:
				insertarGrupo();
				break;
			case 3:
				mostrarAlumno();
				break;
			case 4:
				mostrarGrupo();
				break;
			case 5:
				modificarNombrePorPK();
				break;
			case 6:
				eliminarAlumnoPorPK();
				break;
			case 7:
				cambiarGrupo();
				break;
			case 8:
				guardarEnFicheros();
				break;
			case 9:
				
				break;

			default:
				break;
			}

		} while (opcion != 0);

	}
	
	public void insertarAlumno() {
		modelo.insertarAlumno(vista.datosAlumno());
	}
	
	public void insertarGrupo() {
		modelo.insertarGrupo(vista.datosGrupo());
		
	}
	
	public void mostrarAlumno() {
		vista.mostrarAlumnos(modelo.recogerAlumnos());
	}
	
	public void mostrarGrupo() {
		vista.mostrarGrupos(modelo.recogerGrupos());
	}
	
	public void modificarNombrePorPK() {
		modelo.modificarNombrePorPK(vista.pedirString(),vista.pedirInt());
		vista.mostrarAlumnos(modelo.recogerAlumnos());
	}
	
	public void eliminarAlumnoPorPK() {
		modelo.eliminarPorPK(vista.pedirInt());
		vista.mostrarAlumnos(modelo.recogerAlumnos());
	
	}
	
	public void cambiarGrupo() {
		modelo.cambiarGrupo(vista.pedirInt(), vista.pedirInt());
		vista.mostrarAlumnos(modelo.recogerAlumnos());
		
	}
	
	public void guardarEnFicheros() {
		
		
	}

}
