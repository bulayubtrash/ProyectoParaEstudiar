package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pojo.Alumno;
import pojo.Grupo;
import pool.DBCPDataSource;

public class AlumnosBD implements AlumnosDAO {

	private static final Logger logger = LogManager.getLogger(AlumnosBD.class);

	@Override
	public void insertarAlumno(Alumno a1) {

		String sql = "INSERT INTO alumno (nombre, apellido, genero, fechaNac, grupo_id) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DBCPDataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setString(1, a1.getNombre());
			ps.setString(2, a1.getApellido());
			ps.setString(3, String.valueOf(a1.getGenero()));

			java.sql.Date fecha = new java.sql.Date(a1.getFechaNac().getTime());

			ps.setDate(4, fecha);

			ps.setInt(5, 1); // ✅ Usa el grupo correcto

			int filas = ps.executeUpdate();

			if (filas > 0) {
				logger.info("Alumno insertado");
			}

		} catch (Exception e) {
		    logger.error("El alumno no ha podido ser insertado", e); // ✅ Ahora muestra el error
		}
	}

	@Override
	public void insertarGrupo(Grupo g1) {

		String sql = "INSERT INTO grupo (nombre) VALUES (?)";

		try (Connection conn = DBCPDataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setString(1, g1.getNombre());

			int filas = ps.executeUpdate();

			if (filas > 0) {
				logger.info("Grupo insertado");

			}

		} catch (Exception e) {
			logger.error("El Alumno no se ha podido insertar");
		}
	}

	@Override
	public ArrayList<Alumno> recogerAlumnos() {
		ArrayList<Alumno> aLista = new ArrayList<>();
		Alumno a1;
		Grupo g1;

		String sql = "SELECT * FROM alumno"; // prohibido *

		try (Connection conn = DBCPDataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {

				a1 = new Alumno();

				a1.setNia(rs.getInt("nia"));
				a1.setNombre(rs.getString("nombre"));
				a1.setApellido(rs.getString("apellido"));

				String genero = rs.getString("genero");
				a1.setGenero(genero.charAt(0));

				java.util.Date fecha = new java.util.Date(rs.getDate("fechaNac").getTime());
				a1.setFechaNac(fecha);

				g1 = new Grupo(detectarNombreDeGrupo(rs.getInt("grupo_id")));

				a1.setGrupo(g1);

				aLista.add(a1);

			}

		} catch (Exception e) {
			logger.error("Error al recoger alumno");
		}

		return aLista;
	}

	public String detectarNombreDeGrupo(int ident) {
		String nombre = "";
		String sql = "SELECT * FROM grupo WHERE ident = ?";

		try (Connection conn = DBCPDataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, ident);

			
			try(ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					nombre = rs.getString("nombre");
				}
			} catch (Exception e) {
				logger.error("Error al buscar grupo");
			}
	

		} catch (Exception e) {
			logger.error("Error al buscar grupo");
		}
		return nombre;

	}

	@Override
	public ArrayList<Grupo> recogerGrupos() {
		ArrayList<Grupo> gLista = new ArrayList<>();
		Grupo g1;

		String sql = "SELECT nombre FROM grupo";

		try (Connection conn = DBCPDataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {
				g1 = new Grupo();

				g1.setNombre(rs.getString("nombre"));

				gLista.add(g1); // ✅ Agrega los grupos correctamente
			}

		} catch (Exception e) {
			logger.error("Error al recoger grupo");
		}
		return gLista;
	}

	@Override
	public void modificarNombrePorPK(String nombre, int pk) {
		
		String sql="UPDATE alumno SET nombre = ? WHERE nia = ?";
		
		try(Connection conn=DBCPDataSource.getConnection();
				PreparedStatement ps= conn.prepareStatement(sql);) {
			
			ps.setString(1, nombre);
			ps.setInt(2, pk);
			
			int filas=ps.executeUpdate();
			
			if (filas>0) {
				logger.info("Alumno actualizado correctamente");
				
			}
		} catch (Exception e) {
			logger.error("error al actualizar alumno", e);		
			}

	}

	@Override
	public void eliminarPorPK(int pk) {
		
		String sql="DELETE FROM alumno WHERE nia = ?";
		
		try(Connection conn= DBCPDataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, pk);
			
			int filas=ps.executeUpdate();
			if (filas>0) {
				logger.info("Alumno eliminado correctamente");
				
			}
			
		} catch (Exception e) {
			logger.error("No se ha podido eliminar el alumno",e);
		}

	}

	@Override
	public void cambiarGrupo(int nia, int grupo_id) {

		String sql="UPDATE alumno SET grupo_id = ? WHERE nia = ?";
		
		try(Connection conn= DBCPDataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, grupo_id);
			ps.setInt(2, nia);
			
			int filas= ps.executeUpdate();
			if (filas>0) {
				logger.info("Alumno actualizado correctamente");
			}
		
				
			
		} catch (Exception e) {
			logger.error("El alumno no se ha podido eliminar",e);
		}
	}

}
