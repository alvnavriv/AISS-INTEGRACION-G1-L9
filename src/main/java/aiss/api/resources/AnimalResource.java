package aiss.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Animal;
import aiss.model.repository.MapZooRepository;
import aiss.model.repository.ZooRepository;
import aiss.model.resources.comparators.ComparatorNombreAnimal;
import aiss.model.resources.comparators.ComparatorNombreAnimalReversed;
import aiss.model.resources.comparators.ComparatorTipoAnimal;
import aiss.model.resources.comparators.ComparatorTipoAnimalReversed;
@Path("/animals")
public class AnimalResource {

	public static AnimalResource _instance = null;
	ZooRepository repository;

	private AnimalResource(){
		repository=MapZooRepository.getInstance();
	}
	
	public static AnimalResource getInstance()
	{
		if(_instance==null)
			_instance=new AnimalResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Animal> getAll(@QueryParam("order") String order, @QueryParam("q") String q, @QueryParam("limit") Integer limit,@QueryParam("offset") Integer offset)
	{
		List<Animal> result = new ArrayList<Animal>();
		
		for (Animal a: repository.getAllAnimals()) {
			if(q == null
					|| a.getNombre().toLowerCase().contains(q.toLowerCase())
					|| a.getTipo().toLowerCase().contains(q.toLowerCase())
					) {
				result.add(a);
			}
		}
		if(order != null) {
			switch(order) {
			case "nombre":
				Collections.sort(result,new ComparatorNombreAnimal());
				break;
			case "-nombre":
				Collections.sort(result, new ComparatorNombreAnimalReversed());
				break;
			case "tipo":
				Collections.sort(result, new ComparatorTipoAnimal());
				break;
			case "-tipo":
				Collections.sort(result, new ComparatorTipoAnimalReversed());
				break;
			default:
				throw new BadRequestException("The order parameter must be: 'nombre','-nombre', 'tipo','-tipo'");
			}
		}
		
		int size = repository.getAllAnimals().size();
		List<Animal> resultpagination = new ArrayList<Animal>();
		
		if(offset != null && offset > 0 && offset < size) {
			if(limit != null && limit>0 && (offset + limit)<=size) {
				for(int i=offset; i<(offset +limit); i++)
					resultpagination.add(result.get(i));
			}else if(limit==null || (offset + limit)> size) {
				for(int i=offset; i<size;i++)
					resultpagination.add(result.get(i));
			}else {
				throw new BadRequestException("The limit parameter must be greater than 0 and lower than " + result.size() + ".");
			}
		}else if (offset == null) {
			if(limit != null && limit < size) {
				for(int i=0; i<limit; i++)
					resultpagination.add(result.get(i));
			}else {
				for(Animal animal: result)
					resultpagination.add(animal);
			}
		}else {
			throw new BadRequestException("The offset parameter must be greater than 0 abd lower than " + result.size()+".");
		}
		
		if(offset != null || limit != null) {
			return resultpagination;
		}else {
			return result;
		}
	}
	
	@GET
	@Path("/{nombreAnimal}")
	@Produces("application/json")
	public Animal get(@PathParam("nombreAnimal") String animalName)
	{
		Animal animal = repository.getAnimal(animalName);
		
		if(animal==null) {
			throw new NotFoundException("The animal with name="+ animalName +" was not found");
	}
		return animal;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addAnimal(@Context UriInfo uriInfo, Animal animal) {
	if (animal.getNombre() == null || "".equals(animal.getNombre()))
		throw new BadRequestException("The name of the animal must not be null");

	repository.addAnimal(animal);

// Builds the response. Returns the animal the has just been added.
	UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
	URI uri = ub.build(animal.getNombre());
	ResponseBuilder resp = Response.created(uri);
	resp.entity(animal);			
	return resp.build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response updateAnimal(Animal animal) {
	Animal oldAnimal = repository.getAnimal(animal.getNombre());
	if(oldAnimal==null) {
		throw new NotFoundException("The animal with name="+ animal.getNombre() +" was not found");
	}
		
	repository.updateAnimal(animal);

	return Response.noContent().build();
	}

	@DELETE
	@Path("/{nombreAnimal}")
	public Response removeFootballer(@PathParam("nombreAnimal") String animalName) {
		Animal toberemoved=repository.getAnimal(animalName);
		if (toberemoved == null)
			throw new NotFoundException("The animal with name="+ animalName +" was not found");
		else
			repository.deleteAnimal(animalName);
		
	return Response.noContent().build();
	}
	
	

}
