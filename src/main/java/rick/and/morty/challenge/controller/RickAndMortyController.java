package rick.and.morty.challenge.controller;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import rick.and.morty.challenge.models.Location;
import rick.and.morty.challenge.models.Root;
import rick.and.morty.challenge.service.RickAndMortyService;

@RestController
@RequestMapping("rick-and-morty")
public class RickAndMortyController {
	
	private static final Log LOGGER = LogFactory.getLog(RickAndMortyController.class);
	
	private RickAndMortyService rickAndMortyService;

	@Autowired
	public RickAndMortyController(RickAndMortyService rickAndMortyService) {
		this.rickAndMortyService = rickAndMortyService;
	}
	
	/**
	 * Método que consulta una petición con el id de algún personaje de la serie.
	 *
	 * @param id del personaje
	 * @return Retorna la información mediante el objeto Root.
	 */
	@GetMapping("/character/{id}")
	@ApiOperation(value = "findById", notes = "Se encarga de consultar una peticion con el id  de algun personaje de la serie")
	public Optional<Root> findById(
			@ApiParam(name = "id", value = "id del personaje", type = "int", required = true)
			@PathVariable int id) {
		LOGGER.trace("Peticion para obtener información del personaje por id");
		return rickAndMortyService.findById(id);
	
	}
	
	/**
	 * Método que consulta una petición con el id de alguna localización.
	 *
	 * @param id de la localización
	 * @return Retorna la información mediante el objeto Location.
	 */
	@GetMapping("/location/{id}")
	@ApiOperation(value = "findByLocation", notes = "Se encarga de consultar una peticion con el id de alguna localizacion.")
	public Optional<Location> findByLocation(
			@ApiParam(name = "id", value = "id de la localizacion", type = "int", required = true)
			@PathVariable int id) {
		LOGGER.trace("Peticion para obtener informacion de la localización por id");
		return rickAndMortyService.findByLocation(id);
	
	}
	
	

}
