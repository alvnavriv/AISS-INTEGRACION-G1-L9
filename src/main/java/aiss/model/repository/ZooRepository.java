package aiss.model.repository;

import java.util.Collection;

import aiss.model.Animal;
import aiss.model.Zoo;



public interface ZooRepository {
		//Animales
			public void addAnimal(Animal a);
			public Collection<Animal> getAllAnimals();
			public Animal getAnimal(String animalName);
			public void updateAnimal(Animal a);
			public void deleteAnimal(String animalName);
			
		// ZooList
			public void addZoo(Zoo z);
			public Collection<Zoo> getAllZoo();
			public Zoo getZoo(String zooName);
			public void updateZoo(Zoo z);
			public void deleteZoo(String zooName);
			public Collection<Animal> getAll(String zooName);
			public void addAnimal(String zooName, String animalName);
			public void removeAnimal(String zooName, String animalName); 

}
