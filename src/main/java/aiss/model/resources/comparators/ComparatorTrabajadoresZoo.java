package aiss.model.resources.comparators;

import java.util.Comparator;

import aiss.model.Zoo;

public class ComparatorTrabajadoresZoo implements Comparator<Zoo>{
	
	public int compare(Zoo z1, Zoo z2) {
		return z1.getTrabajadores().compareTo(z2.getTrabajadores());
	}

}
