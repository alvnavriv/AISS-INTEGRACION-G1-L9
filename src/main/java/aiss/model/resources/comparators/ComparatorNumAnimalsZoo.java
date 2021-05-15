package aiss.model.resources.comparators;

import java.util.Comparator;

import aiss.model.Zoo;

public class ComparatorNumAnimalsZoo implements Comparator<Zoo> {
	
	public int compare(Zoo z1, Zoo z2) {
		return z1.getNumAnimales().compareTo(z2.getNumAnimales());
	}
	

}
