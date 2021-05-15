package aiss.model.resources.comparators;

import java.util.Comparator;

import aiss.model.Animal;

public class ComparatorTipoAnimal implements Comparator<Animal> {
	
	public int compare(Animal a1, Animal a2) {
		return a1.getTipo().compareTo(a2.getTipo());
	}
	
}
