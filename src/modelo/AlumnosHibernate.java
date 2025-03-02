package modelo;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Alumno;
import pojo.Grupo;
import util.HibernateUtil;

public class AlumnosHibernate implements AlumnosDAO{
	
	private static final Logger Logger= LogManager.getLogger(AlumnosHibernate.class);

	@Override
	public void insertarAlumno(Alumno a1) {

		Transaction transaction = null;
		
		try (Session session=HibernateUtil.getSessionFactory().openSession();){
			transaction=session.beginTransaction();
			
	        // 🔴 Verificar si el grupo existe antes de asignarlo al alumno
	        Grupo grupoExistente = session.createQuery("FROM Grupo WHERE nombre = :nombre", Grupo.class)
	                                      .setParameter("nombre", a1.getGrupo().getNombre())
	                                      .uniqueResult();

	        if (grupoExistente == null) {
	            Logger.warn("Error: No existe un grupo con el nombre '" + a1.getGrupo().getNombre() + "'. No se insertará el alumno.");
	            return; // 🚨 No se inserta el alumno si el grupo no existe
	        }

	        // Asignar el grupo correcto al alumno
	        a1.setGrupo(grupoExistente);
	        
			session.persist(a1);
			transaction.commit();
			Logger.info("Alumno introducido");
			
		} catch (Exception e) {
			Logger.error("Error al introducir alumno");
		}
		
		
		
		
	}

	@Override
	public void insertarGrupo(Grupo g1) {

		Transaction transaction = null;
		
		try (Session session= HibernateUtil.getSessionFactory().openSession();){
			transaction= session.beginTransaction();
			session.persist(g1);
			transaction.commit();
			Logger.info("Grupo introducido");

			
		} catch (Exception e) {
			Logger.error("Error al introducir Grupo");
		}
	}

	@Override
	public ArrayList<Alumno> recogerAlumnos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Grupo> recogerGrupos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarNombrePorPK(String nombre, int pk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPorPK(int pk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cambiarGrupo(int nia, int grupo_id) {
		// TODO Auto-generated method stub
		
	}

}
