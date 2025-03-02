package Ejecutador;

import modelo.AlumnosHibernate;
import modelo.AlumnosDAO;
import controlador.Controlador;
import vista.*;

public class EjecutadorHibernate {
	public static void main(String[] args) {
		AlumnosDAO modelo= new AlumnosHibernate();
		IVista vista= new VistaConsola();
		
		Controlador controlador= new Controlador(modelo, vista);
		controlador.ejecutar();
				
	}

}
