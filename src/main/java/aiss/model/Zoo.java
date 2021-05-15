package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class Zoo {
	private String nombre;
	private Integer NumAnimales;
	private Integer Trabajadores;
	private List<Animal> animales;
	
	public Zoo() {}

	public Zoo(String nombre, Integer numAnimales, Integer trabajadores) {
		super();
		this.nombre = nombre;
		NumAnimales = numAnimales;
		Trabajadores = trabajadores;
	}

	protected void setAnimales(List<Animal> a) {
		animales= a;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumAnimales() {
		return NumAnimales;
	}

	public void setNumAnimales(Integer numAnimales) {
		NumAnimales = numAnimales;
	}

	public Integer getTrabajadores() {
		return Trabajadores;
	}

	public void setTrabajadores(Integer trabajadores) {
		Trabajadores = trabajadores;
	}

	public List<Animal> getAnimales() {
		return animales;
	}
	
	public Animal getAnimal(String nombre) {
		
		if (animales == null)
			return null;
		
		Animal animal= null;
		for(Animal a: animales)
			if (a.getNombre().equals(nombre))
			{
				animal = a;
				break;
			}
		return animal;
	}

	public void addAnimal(Animal a) {
		if(animales==null)
			animales= new ArrayList<Animal>();
		animales.add(a);
	}
	
	public void deleteAnimal(String nombre) {
		Animal a = getAnimal(nombre);
		if (a!=null)
			animales.remove(a);
	}

}
