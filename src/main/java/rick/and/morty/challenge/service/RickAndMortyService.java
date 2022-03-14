package rick.and.morty.challenge.service;

import java.util.Optional;

import rick.and.morty.challenge.models.Location;
import rick.and.morty.challenge.models.Root;

public interface RickAndMortyService {
	
	/**
	 * Método que consulta una petición con el id  de algún personaje de la serie.
	 *
	 * @param id del personaje
	 * @return Retorna la información mediante el objeto Root.
	 */
	public Optional<Root> findById(int id);
	
	/**
	 * Método que consulta una petición con el id  de algún localización.
	 *
	 * @param id de la localización
	 * @return Retorna la información mediante el objeto Location.
	 */
	public Optional<Location> findByLocation(int id);

}
