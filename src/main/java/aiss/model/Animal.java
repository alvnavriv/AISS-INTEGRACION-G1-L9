package aiss.model;

public class Animal {
	private String nombre;
	private Integer edad;
	private String tipo;
	private String alimentacion;
	
	public Animal() {
	}

	public Animal(String nombre, Integer edad, String tipo, String alimentacion) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.tipo = tipo;
		this.alimentacion = alimentacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAlimentacion() {
		return alimentacion;
	}

	public void setAlimentacion(String alimentacion) {
		this.alimentacion = alimentacion;
	}

}
