package aiss.model.resources.comparators;

import java.util.Comparator;

import aiss.model.Zoo;

public class ComparatorTrabajadoresZooReversed implements Comparator<Zoo> {
	
	public int compare(Zoo z1, Zoo z2) {
		return z2.getTrabajadores().compareTo(z1.getTrabajadores());
	}

}
