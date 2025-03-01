package Ejecutador;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Grupo;
import util.HibernateUtil;
import vista.VistaConsola;

public class TestHibernate {
    private static final Logger logger = LogManager.getLogger(TestHibernate.class);

    public static void main(String[] args) {
        logger.info("Iniciando Hibernate...");

        // Inicializar la sesión
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        // Crear un objeto de prueba
        VistaConsola vista = new VistaConsola();
        Grupo g1 = vista.datosGrupo();

        try {
            if (g1.getIdent() == 0) { // Si el ID es 0, es un nuevo grupo
                session.persist(g1);  // Insertar nuevo grupo
                logger.info("Nuevo grupo insertado: " + g1);
            } else {
                Grupo grupoExistente = session.get(Grupo.class, g1.getIdent());
                if (grupoExistente != null) {
                    grupoExistente.setNombre(g1.getNombre()); // Actualiza el nombre
                    session.merge(grupoExistente); // Actualiza en la BD
                    logger.info("Grupo actualizado: " + grupoExistente);
                } else {
                    session.persist(g1); // Si no existe, inserta
                    logger.info("Grupo creado porque no existía: " + g1);
                }
            }

            // Confirmar la transacción
            transaction.commit();
            logger.info("Transacción confirmada.");

        } catch (Exception e) {
            transaction.rollback();
            logger.error("Error en la transacción: ", e);
        } finally {
            session.close();
            HibernateUtil.shutdown();
            logger.info("Sesión cerrada.");
        }
    }
}
