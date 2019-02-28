package mascotas;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public abstract class Mascota {
	/**
	 * Propiedades
	 */
	private String nombre;
	private String especie;
	LocalDate fechaNacimiento;
	private ArrayList<Revision> revisiones;
	
	public Mascota() {
		
	}

	public Mascota(String nombre, String especie, LocalDate fechaNacimiento) {
		setNombre(nombre);
		setEspecie(especie);
		setFechaNacimiento(fechaNacimiento);
	}

	/**
	 * Getters & Setters 
	 */

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public ArrayList<Revision> getRevisiones() {
		return revisiones;
	}

	public void setRevisiones(ArrayList<Revision> revisiones) {
		this.revisiones = revisiones;
	}
	
	
	public int calcularEdad() {
		LocalDate hoy = LocalDate.now();
		LocalDate nacimientoAnimal = this.getFechaNacimiento();
		
		Period periodo = Period.between(nacimientoAnimal, hoy);
		
		long l1= periodo.toTotalMonths();
		
		int years = (periodo.getYears() * 12) + periodo.getMonths();
		
		return years;
		
	}

}
