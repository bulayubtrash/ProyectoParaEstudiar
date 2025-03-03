package modelo;

import java.util.ArrayList;
import java.util.List;

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
			Logger.error("Error al introducir alumno",e);
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
			Logger.error("Error al introducir Grupo",e);
		}
	}

	@Override
	public ArrayList<Alumno> recogerAlumnos() {

		try (Session se=HibernateUtil.getSessionFactory().openSession();){
			List<Alumno>Lista = se.createQuery("FROM Alumno",Alumno.class).list();
			Logger.info("Lista de alumnos recuperada");
			
			return new ArrayList<>(Lista);	
			
		} catch (Exception e) {
	        Logger.error("Error al obtener la lista de alumnos", e);
			return new ArrayList<>();

		}
		
	}

	@Override
	public ArrayList<Grupo> recogerGrupos() {

		try(Session se=HibernateUtil.getSessionFactory().openSession();) {
			List<Grupo>Lista= se.createQuery("FROM Grupo",Grupo.class).list();
			Logger.info("Lista de grupos recuperada");

			return new ArrayList<>(Lista);
			
		} catch (Exception e) {
	        Logger.error("Error al obtener la lista de grupos", e);
	        return new ArrayList<>();
		}
	}

	@Override
	public void modificarNombrePorPK(String nombre, int pk) {
		
		Transaction tr=null;
		try(Session se=HibernateUtil.getSessionFactory().openSession();) {
			tr=se.beginTransaction();

			Alumno alumno= se.get(Alumno.class, pk);
			
			if (alumno!=null) {
				alumno.setNombre(nombre);
				se.merge(alumno);
				tr.commit();
				Logger.info("Nombre actualizado");
			}else{
				Logger.error("No se encontro el alumno");
			}
		} catch (Exception e) {
			Logger.error("Error al actualizar alumno",e);

		}
		
	}

	@Override
	public void eliminarPorPK(int pk) {
		Transaction tr= null;
		try(Session se= HibernateUtil.getSessionFactory().openSession();) {
			tr=se.beginTransaction();
			
			Alumno alumno=se.get(Alumno.class, pk);
			if(alumno!=null) {
				se.remove(alumno);
				tr.commit();
				Logger.info("Alumno eliminado");
			}else {
				Logger.error("El alumno no ha podido ser encontrado");
			}
			
		} catch (Exception e) {
			Logger.error("El alumno no ha podido ser eliminado",e);
		}
		
	}

	@Override
	public void cambiarGrupo(int nia, int grupo_id) {

		Transaction tr= null;
		try(Session se=HibernateUtil.getSessionFactory().openSession();) {
			tr=se.beginTransaction();
			
			Alumno alumno=se.get(Alumno.class, nia);
			if(alumno==null) {
				Logger.warn("Alumno no encontrado");
				return;
			}
			
			Grupo nuevoGrupo=se.get(Grupo.class, grupo_id);
			if (nuevoGrupo==null) {
				Logger.warn("Grupo no encontrado");
				return;
			}
			
			alumno.setGrupo(nuevoGrupo);
			se.merge(alumno);
			tr.commit();
			
			Logger.info("Alumno cambiado de grupo");
		} catch (Exception e) {
			Logger.error("Error al cambiar de grupo",e);
		}
		
	}

}
