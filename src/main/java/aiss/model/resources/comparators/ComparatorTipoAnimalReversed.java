package aiss.model.resources.comparators;

import java.util.Comparator;

import aiss.model.Animal;

public class ComparatorTipoAnimalReversed implements Comparator<Animal>{
	
	public int compare(Animal a1,Animal a2) {
		return a2.getTipo().compareTo(a1.getNombre());
	}
}
