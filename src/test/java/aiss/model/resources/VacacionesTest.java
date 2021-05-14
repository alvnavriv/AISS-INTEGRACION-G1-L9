package aiss.model.resources;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.Test;

import aiss.model.Holiday;
import aiss.model.Response;
import aiss.model.Vacaciones;
import aiss.model.resources.VacacionesResource;

public class VacacionesTest {

	@Test
	public void testGetHeadLines() throws UnsupportedEncodingException {
		String country = "US";
		Integer year =2019;
		VacacionesResource v = new VacacionesResource();
		Vacaciones vResult= v.getHeadLines(country, year);
		List<Holiday> h =vResult.getResponse().getHolidays();
		
		
		assertNotNull("El resultado devuelto es null", vResult);
		assertNotNull("El resultado devuelto es null", h);
		
		System.out.println("La busqueda de vacaciones en " + country + " en el año " + year + " devuelve " + h.size());
		
			for(Holiday hol: h)

				System.out.println("Nombre del dia festivo en "+ country +" : "+ hol.getName());
	}

}
