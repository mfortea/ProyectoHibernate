package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.dual.Hibernate.Departamento;
import com.dual.Hibernate.Empleado;

import dao.InterfaceHibernateDAO;

public class HibernateDAO implements InterfaceHibernateDAO {

	// ------- MÉTODOS DE LAS OPERACIONES SOBRE LA BD

	// MÉTODOS PARA INSERTAR

	// Método para insertar departamentos
	public boolean insertDepartamento(Departamento u, SessionFactory factory) {
		boolean resultado = false;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(u);
			tx.commit();
			resultado = true;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return resultado;
	}

	// Método para insertar empleados
	public boolean insertEmpleado(Empleado u, SessionFactory factory) {
		boolean resultado = false;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(u);
			tx.commit();
			resultado = true;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return resultado;
	}

	// MÉTODOS PARA ACTUALIZAR

	// Método para actualizar departamentos por ID
	public boolean actualizaDepartamentoWhere(int id, String nombre, SessionFactory factory) {
		boolean resultado = false;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String sql = "UPDATE Departamento set nombre=:nombre where id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			query.setParameter("nombre", nombre);
			query.executeUpdate();
			tx.commit();
			resultado = true;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return resultado;
	}

	public boolean actualizaEmpleadoWhere(int id, String nombre, String apellido, SessionFactory factory) {
		boolean resultado = false;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String sql = "UPDATE Empleado set nombre=:nombre, apellido=:apellido where id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			query.setParameter("nombre", nombre);
			query.setParameter("apellido", apellido);
			query.executeUpdate();
			tx.commit();
			resultado = true;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return resultado;
	}

	// MÉTODOS PARA BORRAR

	// Método para borrar departamentos
	public boolean deleteDepartamento(int id, SessionFactory factory) {
		boolean resultado = false;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String sql = "DELETE FROM Departamento where id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			query.executeUpdate();
			tx.commit();
			resultado = true;
		} catch (ConstraintViolationException ex) {
			if (tx != null)
				tx.rollback();
			resultado = false;
			JOptionPane.showMessageDialog(null,
					"ERROR. No se puede eliminar el departamento, porque hay empleados asociados a él");
		} finally {
			session.close();
		}
		return resultado;
	}

	// Método para borrar empleados
	public boolean deleteEmpleado(int id, SessionFactory factory) {
		boolean resultado = false;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String sql = "DELETE FROM Empleado where id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			query.executeUpdate();
			tx.commit();
			resultado = true;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			JOptionPane.showMessageDialog(null, "Error en la BD: " + ex);
		} finally {
			session.close();
		}
		return resultado;
	}

	// MÉTODOS DE SELECT

	// Método para listar departamentos
	public List<Departamento> listDepartamentos(SessionFactory factory) {
		Session sesn = factory.openSession();
		Transaction tx = null;
		List<Departamento> departamentos = new ArrayList();
		try {
			tx = sesn.beginTransaction();
			departamentos = sesn.createQuery("From Departamento").list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}
		return departamentos;
	}

	// Método para listar empleados
	public List<Empleado> listEmpleados(SessionFactory factory) {
		Session sesn = factory.openSession();
		Transaction tx = null;
		List<Empleado> empleados = new ArrayList();
		try {
			tx = sesn.beginTransaction();
			empleados = sesn.createQuery("From Empleado").list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}
		return empleados;
	}

	// Método para listar empleados de un departameno
	public List<Empleado> listEmpleadosById(int id, SessionFactory factory) {
		Session sesn = factory.openSession();
		Transaction tx = null;
		List<Empleado> empleados = new ArrayList();
		try {
			tx = sesn.beginTransaction();
			empleados = sesn.createQuery("From Empleado WHERE id_dep=" + id).list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}
		return empleados;
	}

}
