package rick.and.morty.challenge.service.impl;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feign.FeignException;
import rick.and.morty.challenge.client.RickAndMortyClientRest;
import rick.and.morty.challenge.exception.BadRequestException;
import rick.and.morty.challenge.models.Location;
import rick.and.morty.challenge.models.Origin;
import rick.and.morty.challenge.models.Root;
import rick.and.morty.challenge.service.RickAndMortyService;

@Service
public class RickAndMortyServiceImpl implements RickAndMortyService {

	private static final Log LOGGER = LogFactory.getLog(RickAndMortyServiceImpl.class);

	private RickAndMortyClientRest rickAndMortyClientRest;

	@Autowired
	public RickAndMortyServiceImpl(RickAndMortyClientRest rickAndMortyClientRest) {
		this.rickAndMortyClientRest = rickAndMortyClientRest;
	}

	@Override
	public Optional<Root> findById(int id) {
		LOGGER.info("Inicio findById(" + id + ")");
		try {

			Root root = rickAndMortyClientRest.findById(id);

			root.setEpisode_count(root.getEpisode().size());
			Optional<Location> location = findByLocation(id);
			
			if(location.isPresent()) {
				
				Origin origin = Origin.builder()
						.name(root.getOrigin().getName())
						.url(root.getOrigin().getUrl())
						.dimension(location.get().getDimension())
						.residents(location.get().getResidents())
						.build();
				root.setOrigin(origin);
				
			}
			
			return Optional.ofNullable(root);

		} catch (FeignException e) {
			LOGGER.error("Error en findById(" + id + ")");
		    LOGGER.error(e.getMessage(), e.getCause());
		    throw new BadRequestException("Ha ocurrido el siguiente error:" + e.getLocalizedMessage());
		}
	}

	@Override
	public Optional<Location> findByLocation(int id) {
		LOGGER.info("Inicio findByLocation(" + id + ")");
		try {
			return Optional.ofNullable(rickAndMortyClientRest.findByLocation(id));
		} catch (FeignException e) {
			LOGGER.error("Error en findByLocation(" + id + ")");
			LOGGER.error(e.getMessage(), e.getCause());
			throw new BadRequestException("Ha ocurrido el siguiente error:" + e.getLocalizedMessage());
		}
	}

}
