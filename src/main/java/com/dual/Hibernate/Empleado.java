package com.dual.Hibernate;

public class Empleado {
	private int id;
	private String nombre;
	private String apellido;
	private Departamento id_dep;

	public Empleado() {
		super();
	}

	public Empleado(int id, String nombre, String apellido, Departamento id_dep) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.id_dep = id_dep;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Departamento getId_dep() {
		return id_dep;
	}

	public void setId_dep(Departamento id_dep) {
		this.id_dep = id_dep;
	}

	@Override
	public String toString() {
		return "(" + id + ") " + nombre + " " + apellido + "\n";
	}

}
