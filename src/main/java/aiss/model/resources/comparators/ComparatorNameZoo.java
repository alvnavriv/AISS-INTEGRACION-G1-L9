package aiss.model.resources.comparators;

import java.util.Comparator;

import aiss.model.Zoo;

public class ComparatorNameZoo implements Comparator<Zoo> {
	
	public int compare(Zoo z1, Zoo z2) {
		return z1.getNombre().compareTo(z2.getNombre());
	}
	

}
