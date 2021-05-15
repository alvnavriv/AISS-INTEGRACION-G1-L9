package aiss.api.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Animal;
import aiss.model.Zoo;
import aiss.model.repository.MapZooRepository;
import aiss.model.repository.ZooRepository;

@Path("/zoo")
public class ZooResource {
	
	/* Singleton */
	private static ZooResource _instance=null;
	ZooRepository repository;
	
	private ZooResource() {
		repository=MapZooRepository.getInstance();

	}
	
	public static ZooResource getInstance()
	{
		if(_instance==null)
				_instance=new ZooResource();
		return _instance;
	}

	@GET
	@Produces("application/json")
	public Collection<Zoo> getAll()
	{
		return repository.getAllZoo();
	}
	
	@GET
	@Path("/{nombreZoo}")
	@Produces("application/json")
	public Zoo get(@PathParam("nombreZoo") String name)
	{
		Zoo zoo = repository.getZoo(name);
		
		if (zoo == null) {
			throw new NotFoundException("The zoo with name="+ name +" was not found");			
		}
		
		return zoo;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addZoo(@Context UriInfo uriInfo, Zoo zoo) {
		if (zoo.getNombre() == null || "".equals(zoo.getNombre()))
			throw new BadRequestException("The name of the zoo must not be null");
		
		if (zoo.getAnimales()!=null)
			throw new BadRequestException("The animals property is not editable.");

		repository.addZoo(zoo);

		// Builds the response. Returns the playersList the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(zoo.getNombre());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(zoo);			
		return resp.build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response updateZoo(Zoo zoo) {
		Zoo oldzoo = repository.getZoo(zoo.getNombre());
		if (oldzoo == null) {
			throw new NotFoundException("The zoo with name="+ zoo.getNombre() +" was not found");			
		}
		
		if (zoo.getAnimales()!=null)
			throw new BadRequestException("The animals property is not editable.");
		
		// Update name
		if (zoo.getNombre()!=null)
			oldzoo.setNombre(zoo.getNombre());
		
		// Update tranajdores
		if (zoo.getTrabajadores()!=null)
			zoo.setTrabajadores(zoo.getTrabajadores());
		
		// Update numero animales
		if (zoo.getNumAnimales()!=null)
			zoo.setNumAnimales(zoo.getNumAnimales());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{nombreZoo}")
	public Response removeZoo(@PathParam("nombreZoo") String name) {
		Zoo toberemoved=repository.getZoo(name);
		if (toberemoved == null)
			throw new NotFoundException("The zoo with name="+ name +" was not found");
		else
			repository.deleteZoo(name);
		
		return Response.noContent().build();
	}
	
	@POST	
	@Path("/{nombreZoo}/{animalZoo}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addAnimal(@Context UriInfo uriInfo,@PathParam("nombreZoo") String nombreZoo, @PathParam("nombreAnimal") String nombreAnimal)
	{				
		
		Zoo zoo = repository.getZoo(nombreZoo);
		Animal animal = repository.getAnimal(nombreAnimal);
		
		if (zoo==null)
			throw new NotFoundException("The zoo with name=" + nombreZoo + " was not found");
		
		if (animal == null)
			throw new NotFoundException("The animal with name=" + nombreAnimal + " was not found");
		
		if (zoo.getAnimal(nombreAnimal)!=null)
			throw new BadRequestException("The animal is already included in the zoo.");
			
		repository.addAnimal(nombreZoo,nombreAnimal);	

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(nombreZoo);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(nombreZoo);			
		return resp.build();
	}
	
	@DELETE
	@Path("/{nombreZoo}/{nombreAnimal}")
	public Response removeAnimal(@PathParam("nombreZoo") String nombreZoo, @PathParam("nombreAnimal") String nombreAnimal) {
		Zoo zoo = repository.getZoo(nombreZoo);
		Animal animal = repository.getAnimal(nombreAnimal);
		
		if (zoo==null)
			throw new NotFoundException("The zoo with name=" + nombreZoo + " was not found");
		
		if (animal == null)
			throw new NotFoundException("The animal with name=" + nombreAnimal + " was not found");
		
		
		repository.removeAnimal(nombreZoo, nombreAnimal);		
		
		return Response.noContent().build();
	}
	
	
	
}
