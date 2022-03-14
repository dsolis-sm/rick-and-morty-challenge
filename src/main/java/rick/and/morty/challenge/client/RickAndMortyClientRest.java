package rick.and.morty.challenge.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import rick.and.morty.challenge.models.Location;
import rick.and.morty.challenge.models.Root;

@FeignClient(name = "rickandmortyapi", url="${rickandmortyapi.url}")
public interface RickAndMortyClientRest {
	
	/**
	 * Método que consulta una petición con el id  de algún personaje de la serie.
	 *
	 * @param id del personaje
	 * @return Retorna la información mediante el objeto Root.
	 */
	@GetMapping("/character/{id}")
	public Root findById(@PathVariable int id);
	
	/**
	 * Método que consulta una petición con el id  de algún localización.
	 *
	 * @param id de la localización
	 * @return Retorna la información mediante el objeto Location.
	 */
	@GetMapping("/location/{id}")
	public Location findByLocation(@PathVariable int id);

}
