package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.dual.Hibernate.Departamento;
import com.dual.Hibernate.Empleado;

public interface InterfaceHibernateDAO {

	// ------- MÉTODOS DE LAS OPERACIONES SOBRE LA BD

	// MÉTODOS PARA INSERTAR

	// Método para insertar departamentos
	public boolean insertDepartamento(Departamento u, SessionFactory factory);

	// Método para insertar empleados
	boolean insertEmpleado(Empleado u, SessionFactory factory);

	// MÉTODOS PARA ACTUALIZAR

	// Método para actualizar departamentos por ID
	boolean actualizaDepartamentoWhere(int id, String nombre, SessionFactory factory);

	boolean actualizaEmpleadoWhere(int id, String nombre, String apellido, SessionFactory factory);

	// MÉTODOS PARA BORRAR

	// Método para borrar departamentos
	boolean deleteDepartamento(int id, SessionFactory factory);

	// Método para borrar empleados
	boolean deleteEmpleado(int id, SessionFactory factory);

	// MÉTODOS DE SELECT

	// Método para listar departamentos
	List<Departamento> listDepartamentos(SessionFactory factory);

	// Método para listar empleados
	List<Empleado> listEmpleados(SessionFactory factory);

	// Método para listar empleados de un departameno
	List<Empleado> listEmpleadosById(int id, SessionFactory factory);

}
