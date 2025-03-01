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
		
		try(Connection conn=DBCPDataSource.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql);) {
			
			ps.setString(1, a1.getNombre());
			ps.setString(2, a1.getApellido());
			ps.setString(3, String.valueOf(a1.getGenero()));
			
			java.sql.Date fecha = new java.sql.Date(a1.getFechaNac().getTime());
			
			ps.setDate(4, fecha);
			
			ps.setInt(5, 1);
			
			
			int filas=ps.executeUpdate();
			
			if (filas>0) {
				logger.info("Alumno insertado");
			}
			
		} catch (Exception e) {
			logger.error("El alumnos no ha podido ser insertado");
		}
	}

	@Override
	public void insertarGrupo(Grupo g1) {
		
		String sql="INSERT INTO grupo (nombre) VALUES (?)";
		
		try(Connection conn=DBCPDataSource.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql);) {
			
			ps.setString(1, g1.getNombre());
			
			int filas= ps.executeUpdate();
			
			if (filas>0) {
				logger.info("Grupo insertado");
				
			}
			
		} catch (Exception e) {
			logger.error("El Alumno no se ha podido insertar");
		}
	}

	@Override
	public ArrayList<Alumno> recogerAlumnos() {
		ArrayList<Alumno>aLista;

		String sql="SELECT * FROM alumno"; //prohibido *
		
		try (Connection conn=DBCPDataSource.getConnection();
				PreparedStatement ps= conn.prepareStatement(sql);
				ResultSet rs= ps.executeQuery();) {
			
			while(rs.next()) {
				
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@Override
	public ArrayList<Grupo> recogerGrupos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarNombrePorPK(int pk) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarPorPK(int pk) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cambiarGrupo(int nia, String nombre) {
		// TODO Auto-generated method stub

	}

}
