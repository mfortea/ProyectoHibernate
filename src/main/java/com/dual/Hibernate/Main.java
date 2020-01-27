package com.dual.Hibernate;

import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import dao.InterfaceHibernateDAO;
import dao.impl.HibernateDAO;

public class Main {
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;
	static InterfaceHibernateDAO inDAO = new HibernateDAO();

	public Main() {
		super();
	}

	public static void main(String[] args) {
		conexionBD();
		mostrarMenu();
	}

	public static void conexionBD() {
		Configuration config = new Configuration();
		config.configure();
		config.addAnnotatedClass(Empleado.class);
		config.addAnnotatedClass(Departamento.class);
		setServiceRegistry(new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build());
		setFactory(config.buildSessionFactory(serviceRegistry));
		JOptionPane.showMessageDialog(null, "Conexión a BD establecida");
	}

	public static void mostrarMenu() {

		int entrada = -1;

		do {
			try {
				entrada = Integer.parseInt(JOptionPane.showInputDialog("" + "BASE DE DATOS EMPRESA\n"
						+ "========================\n" + "OPERACIONES SOBRE LOS DEPARTAMENTOS \n"
						+ "1) Insertar un nuevo departamento\n" + "2) Actualizar un departamento\n"
						+ "3) Borrar un departamento\n" + "\n" + "OPERACIONES SOBRE LOS EMPLEADOS \n"
						+ "4) Insertar un nuevo empleado\n" + "5) Actualizar un empleado\n" + "6) Borrar un empleado\n"
						+ "\n" + "7) Consultar empleados de un departamento (por ID)\n" + "0) SALIR\n\n"));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "¡Error! No se pueden introducir letras");
			}

			switch (entrada) {

			case 1:

				insertarDepartamento();
				break;

			case 2:
				actualizarDepartamento();
				break;

			case 3:
				borrarDepartamento();
				break;

			case 4:
				insertarEmpleado();
				break;

			case 5:
				actualizarEmpleado();
				break;

			case 6:
				borrarEmpleado();
				break;

			case 7:
				consultarEmpleados();
				break;

			default:
				break;
			}

		} while (entrada != 0);

	}

	private static void consultarEmpleados() {
		List<Departamento> departamentos = inDAO.listDepartamentos(getFactory());
		String cadenaDeps = "Escribe el ID del departamento del que quieras ver los empleados:\n\n";
		for (Departamento d : departamentos) {
			cadenaDeps += d;
		}
		int id = Integer.parseInt(JOptionPane
				.showInputDialog(cadenaDeps + "\nHay un total de " + departamentos.size() + " departamentos\n"));
		List<Empleado> empleados = inDAO.listEmpleadosById(id, getFactory());
		String cadenaEmps = "";
		for (Empleado e : empleados) {
			cadenaEmps += e;
		}
		JOptionPane.showMessageDialog(null, "Lista de empleados del departamento nº " + id + "\n\n" + cadenaEmps
				+ "\nHay un total de " + empleados.size() + " empleado/s en este departamento\n\n");

	}

	private static void borrarEmpleado() {
		JOptionPane.showMessageDialog(null, "SE VA A ELIMINAR UN EMPLEADO");
		int id = Integer.parseInt(JOptionPane.showInputDialog("ID del empleado que quieras eliminar:"));
		inDAO.deleteEmpleado(id, getFactory());
		JOptionPane.showMessageDialog(null, "Se ha eliminado el empleado nº " + id + " correctamente");
	}

	private static void actualizarEmpleado() {
		String nombre = "";
		String apellido = "";
		int id = 0;
		try {
			List<Empleado> empleados = inDAO.listEmpleados(getFactory());
			String cadenaEmps = "Escribe el ID del empleado que quieras actualizar:\n\n";

			for (Empleado e : empleados) {
				cadenaEmps += e;
			}

			id = Integer.parseInt(JOptionPane
					.showInputDialog(cadenaEmps + "\nHay un total de " + empleados.size() + " empleados\n\n"));
			nombre = JOptionPane.showInputDialog("Nuevo nombre para el empleado:");
			apellido = JOptionPane.showInputDialog("Nuevo apellido para el empleado:");
			inDAO.actualizaEmpleadoWhere(id, nombre, apellido, getFactory());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"No se ha podido actualizar el empleado, asegúrate de que el ID insertado existe en la BD");
		}

		JOptionPane.showMessageDialog(null, "El empleado nº " + id + " se ha cambiado a " + nombre + " correctamente.");

	}

	private static void insertarEmpleado() {
		String nombre = "";
		String apellido = "";
		try {
			JOptionPane.showMessageDialog(null, "SE VA A INSERTAR UN NUEVO EMPLEADO");
			nombre = JOptionPane.showInputDialog("Nombre:");
			apellido = JOptionPane.showInputDialog("Apellido/s:");
			int id_dep = Integer.parseInt(JOptionPane.showInputDialog("ID del Departamento al que pertenece:"));
			Departamento dep = new Departamento(id_dep, null, null);
			Empleado empleado = new Empleado(0, nombre, apellido, dep);
			inDAO.insertEmpleado(empleado, getFactory());
		} catch (HeadlessException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido insertar el departamento");
		}
		JOptionPane.showMessageDialog(null, "El empleado " + nombre + " " + apellido + " se ha creado correctamente.");
	}

	private static void borrarDepartamento() {
		JOptionPane.showMessageDialog(null, "SE VA A ELIMINAR UN DEPARTAMENTO");
		int id = Integer.parseInt(JOptionPane.showInputDialog("ID del departamento que quieras eliminar:"));
		if (inDAO.deleteDepartamento(id, getFactory())) {
			JOptionPane.showMessageDialog(null, "Se ha eliminado el departamento nº " + id + " correctamente");
		}
	}

	private static void actualizarDepartamento() {
		String nombre = "";
		int id = 0;
		try {
			List<Departamento> departamentos = inDAO.listDepartamentos(getFactory());
			String cadenaDeps = "Escribe el ID del departamento que quieras actualizar:\n\n";

			for (Departamento d : departamentos) {
				cadenaDeps += d;
			}

			id = Integer.parseInt(JOptionPane
					.showInputDialog(cadenaDeps + "\nHay un total de " + departamentos.size() + " departamentos\n\n"));

			nombre = JOptionPane.showInputDialog("Nuevo nombre para el departamento:");
			inDAO.actualizaDepartamentoWhere(id, nombre, getFactory());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"No se ha podido actualizar el departamento, asegúrate de que el ID insertado existe en la BD");
		}
		JOptionPane.showMessageDialog(null,
				"El departamento nº " + id + " se ha cambiado a " + nombre + " correctamente.");
	}

	private static void insertarDepartamento() {
		String nombre = "";
		try {
			JOptionPane.showMessageDialog(null, "SE VA A INSERTAR UN NUEVO DEPARTAMENTO");
			nombre = JOptionPane.showInputDialog("Nombre para el departamento:");
			Departamento dep = new Departamento(0, nombre, null);
			inDAO.insertDepartamento(dep, getFactory());
		} catch (HeadlessException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido insertar el departamento");
		}
		JOptionPane.showMessageDialog(null, "El departamento " + nombre + " se ha creado correctamente.");
	}

	// GETTERS Y SETTERS
	public static SessionFactory getFactory() {
		return factory;
	}

	public static void setFactory(SessionFactory factory) {
		Main.factory = factory;
	}

	public static ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public static void setServiceRegistry(ServiceRegistry serviceRegistry) {
		Main.serviceRegistry = serviceRegistry;
	}

}
