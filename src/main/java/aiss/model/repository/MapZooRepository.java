package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Animal;
import aiss.model.Zoo;

public class MapZooRepository implements ZooRepository {
	Map<String, Zoo> animalsListMap;
	Map<String, Animal> animalMap;
	private static MapZooRepository instance=null;
	private int index=0;			// Index to create zoo and animals' identifiers.

public static MapZooRepository getInstance() {
		
		if (instance==null) {
			instance = new MapZooRepository();
			instance.init();
		}
		
		return instance;
	
	}

public void init() {
	
	animalsListMap = new HashMap<String,Zoo>();
	animalMap = new HashMap<String,Animal>();
	
	// Create animals
	Animal leon=new Animal();
	leon.setNombre("León");
	leon.setEdad(4);
	leon.setAlimentacion("Carnívoro");
	leon.setTipo("Mamífero");
	addAnimal(leon);
	
	Animal koala=new Animal();
	koala.setNombre("Koala");
	koala.setEdad(6);
	koala.setAlimentacion("Herbívoro");
	koala.setTipo("Mamífero");
	addAnimal(koala);
	
	Animal jirafa=new Animal();
	jirafa.setNombre("Jirafa");
	jirafa.setEdad(2);
	jirafa.setAlimentacion("Herbívoro");
	jirafa.setTipo("Mamíforo");
	addAnimal(jirafa);
	
	Animal avestruz=new Animal();
	avestruz.setNombre("Avestruz");
	avestruz.setEdad(3);
	avestruz.setAlimentacion("Omnívoro");
	avestruz.setTipo("Ave");
	addAnimal(avestruz);

	Animal coco=new Animal();
	coco.setNombre("Cocodrilo");
	coco.setEdad(15);
	coco.setAlimentacion("Carnívoro");
	coco.setTipo("Reptil");
	addAnimal(coco);

	
	// Create zoo
	Zoo list=new Zoo();
	list.setNombre("Zoo de Jerez");
	list.setNumAnimales(10);
	list.setTrabajadores(15);
	addZoo(list);
	
	Zoo list2 = new Zoo();
	list2.setNombre("Zoo del Castillo de las Guardas");
	list2.setNumAnimales(18);
	list2.setTrabajadores(25);
	addZoo(list2);
	
	// Add animales to Zoo
	addAnimal(list.getNombre(), leon.getNombre());
	addAnimal(list.getNombre(), koala.getNombre());
	addAnimal(list.getNombre(), avestruz.getNombre());
	addAnimal(list.getNombre(), coco.getNombre());
	addAnimal(list.getNombre(), jirafa.getNombre());
	
	addAnimal(list2.getNombre(), leon.getNombre());
	addAnimal(list2.getNombre(), jirafa.getNombre());
	
	}

@Override
public void addAnimal(Animal a) {
	String name = "a" + index++;
	a.setNombre(name);
	animalMap.put(name,a);
	
}

@Override
public Collection<Animal> getAllAnimals() {
	// TODO Auto-generated method stub
	return animalMap.values();
}

@Override
public Animal getAnimal(String animalName) {
	// TODO Auto-generated method stub
	return animalMap.get(animalName);
}

@Override
public void updateAnimal(Animal a) {
	// TODO Auto-generated method stub
	Animal animal = animalMap.get(a.getNombre());
	animal.setNombre(a.getNombre());
	animal.setEdad(a.getEdad());
	animal.setAlimentacion(a.getAlimentacion());
	animal.setTipo(a.getTipo());
}

@Override
public void deleteAnimal(String animalName) {
	// TODO Auto-generated method stub
	animalMap.remove(animalName);
}

@Override
public void addZoo(Zoo z) {
	// TODO Auto-generated method stub
	String name= "z" + index++;
	z.setNombre(name);
	animalsListMap.put(name,z);
	
}

@Override
public Collection<Zoo> getAllZoo() {
	// TODO Auto-generated method stub
	return animalsListMap.values();
}

@Override
public Zoo getZoo(String zooName) {
	// TODO Auto-generated method stub
	return animalsListMap.get(zooName);
}

@Override
public void updateZoo(Zoo z) {
	// TODO Auto-generated method stub
	animalsListMap.put(z.getNombre(), z);
}

@Override
public void deleteZoo(String zooName) {
	// TODO Auto-generated method stub
	animalsListMap.remove(zooName);
}

@Override
public Collection<Animal> getAll(String zooName) {
	// TODO Auto-generated method stub
	return animalMap.values();
}

@Override
public void addAnimal(String zooName, String animalName) {
	// TODO Auto-generated method stub
	Zoo zoo = getZoo(zooName);
	zoo.addAnimal(animalMap.get(animalName));
	
}

@Override
public void removeAnimal(String zooName, String animalName) {
	// TODO Auto-generated method stub
	getZoo(zooName).deleteAnimal(animalName);
	
}



}
