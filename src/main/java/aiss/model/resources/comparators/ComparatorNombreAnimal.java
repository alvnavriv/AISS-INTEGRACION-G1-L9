package aiss.model.resources.comparators;

import java.util.Comparator;

import aiss.model.Animal;

public class ComparatorNombreAnimal implements Comparator<Animal>{

    public int compare(Animal a1, Animal a2) {
        return a1.getNombre().compareTo(a2.getNombre());
    }

}