package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;

import aiss.model.Vacaciones;

public class VacacionesResource {
	
	private static final String VACACIONES_API_KEY = "6c0586e424b8c7c1c4ae9725a7fd197b663198f3";
	private static final Logger log = Logger.getLogger(VacacionesResource.class.getName());
	
	public Vacaciones getHeadLines(String count,Integer y) throws UnsupportedEncodingException{
		
		String country = URLEncoder.encode(count,"UTF-8");
		
		String uri = "https://calendarific.com/api/v2/holidays"+"?&api_key="+VACACIONES_API_KEY+"&country="+country+"&year="+y;
		
		log.log(Level.FINE, "VACACIONES URI:"+ uri);
		
		ClientResource cr = new ClientResource(uri);
		Vacaciones vacaciones = cr.get(Vacaciones.class);
		
		return vacaciones;
	}
}
