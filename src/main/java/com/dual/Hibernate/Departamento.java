package com.dual.Hibernate;

import java.util.HashSet;
import java.util.Set;

public class Departamento implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private Set<Empleado> empleados = new HashSet<Empleado>(0);

	public Departamento() {
		super();
	}

	public Departamento(int id, String nombre, HashSet<Empleado> empleados) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.empleados = empleados;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Set<Empleado> empleados) {
		this.empleados = empleados;
	}

	@Override
	public String toString() {
		return "(" + id + ") Departamento de " + nombre + "\n";
	}

}
