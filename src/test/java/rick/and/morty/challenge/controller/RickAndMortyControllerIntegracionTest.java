package rick.and.morty.challenge.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import rick.and.morty.challenge.client.RickAndMortyClientRest;
import rick.and.morty.challenge.models.Location;
import rick.and.morty.challenge.models.Origin;
import rick.and.morty.challenge.models.Root;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class RickAndMortyControllerIntegracionTest {

	@MockBean
	private RickAndMortyClientRest rickAndMortyClientRest;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void findByIdOK() throws Exception {
		
		int id = 1;
		
		Location location = Location.builder()
				.id(1)
				.name("Earth (C-137)")
				.type("Planet")
				.dimension("Dimension C-137")
				.residents(residentsList())
				.build();
		
		Origin origin = Origin.builder()
				.name("Earth (C-137)")
				.url("https://rickandmortyapi.com/api/location/1")
				.dimension("Dimension C-137")
				.residents(residentsList())
				.build();

		Root root = Root.builder()
				.id(1)
				.name("Rick Sanchez")
				.status("Alive")
				.species("Human")
				.type("")
				.episode_count(51)
				.origin(origin)
				.episode(episodeList())
				.build();
		
		Mockito.when(rickAndMortyClientRest.findById(id)).thenReturn(root);
		Mockito.when(rickAndMortyClientRest.findByLocation(id)).thenReturn(location);

		this.mockMvc.perform(get("/rick-and-morty/character/{id}", 1))
					.andDo(print())
					.andExpect(status().isOk())
					.andReturn();

	}
	
	@Test
	public void findByLocationOK() throws Exception {

		int id = 1;

		Location location = Location.builder()
				.id(1)
				.name("Earth (C-137)")
				.type("Planet")
				.dimension("Dimension C-137")
				.residents(residentsList())
				.build();

		Mockito.when(rickAndMortyClientRest.findByLocation(id)).thenReturn(location);
		
		this.mockMvc.perform(get("/rick-and-morty/location/{id}", 1))
					.andDo(print())
					.andExpect(status().isOk())
					.andReturn();
	}
	
	private List<String> residentsList() {

		List<String> residentList = Arrays.asList("https://rickandmortyapi.com/api/character/38",
				"https://rickandmortyapi.com/api/character/45", "https://rickandmortyapi.com/api/character/71",
				"https://rickandmortyapi.com/api/character/82", "https://rickandmortyapi.com/api/character/83",
				"https://rickandmortyapi.com/api/character/92", "https://rickandmortyapi.com/api/character/112",
				"https://rickandmortyapi.com/api/character/114", "https://rickandmortyapi.com/api/character/116",
				"https://rickandmortyapi.com/api/character/117", "https://rickandmortyapi.com/api/character/120",
				"https://rickandmortyapi.com/api/character/127", "https://rickandmortyapi.com/api/character/155",
				"https://rickandmortyapi.com/api/character/169", "https://rickandmortyapi.com/api/character/175",
				"https://rickandmortyapi.com/api/character/179", "https://rickandmortyapi.com/api/character/186",
				"https://rickandmortyapi.com/api/character/201", "https://rickandmortyapi.com/api/character/216",
				"https://rickandmortyapi.com/api/character/239", "https://rickandmortyapi.com/api/character/271",
				"https://rickandmortyapi.com/api/character/302", "https://rickandmortyapi.com/api/character/303",
				"https://rickandmortyapi.com/api/character/338", "https://rickandmortyapi.com/api/character/343",
				"https://rickandmortyapi.com/api/character/356", "https://rickandmortyapi.com/api/character/394");

		return residentList;
	}

	private List<String> episodeList() {

		List<String> episodeList = Arrays.asList("https://rickandmortyapi.com/api/episode/1",
				"https://rickandmortyapi.com/api/episode/2", "https://rickandmortyapi.com/api/episode/3",
				"https://rickandmortyapi.com/api/episode/4", "https://rickandmortyapi.com/api/episode/5",
				"https://rickandmortyapi.com/api/episode/6", "https://rickandmortyapi.com/api/episode/7",
				"https://rickandmortyapi.com/api/episode/8", "https://rickandmortyapi.com/api/episode/9",
				"https://rickandmortyapi.com/api/episode/10", "https://rickandmortyapi.com/api/episode/11",
				"https://rickandmortyapi.com/api/episode/12", "https://rickandmortyapi.com/api/episode/13",
				"https://rickandmortyapi.com/api/episode/14", "https://rickandmortyapi.com/api/episode/15",
				"https://rickandmortyapi.com/api/episode/16", "https://rickandmortyapi.com/api/episode/17",
				"https://rickandmortyapi.com/api/episode/18", "https://rickandmortyapi.com/api/episode/19",
				"https://rickandmortyapi.com/api/episode/20", "https://rickandmortyapi.com/api/episode/21",
				"https://rickandmortyapi.com/api/episode/22", "https://rickandmortyapi.com/api/episode/23",
				"https://rickandmortyapi.com/api/episode/24", "https://rickandmortyapi.com/api/episode/25",
				"https://rickandmortyapi.com/api/episode/26", "https://rickandmortyapi.com/api/episode/27",
				"https://rickandmortyapi.com/api/episode/28", "https://rickandmortyapi.com/api/episode/29",
				"https://rickandmortyapi.com/api/episode/30", "https://rickandmortyapi.com/api/episode/31",
				"https://rickandmortyapi.com/api/episode/32", "https://rickandmortyapi.com/api/episode/33",
				"https://rickandmortyapi.com/api/episode/34", "https://rickandmortyapi.com/api/episode/35",
				"https://rickandmortyapi.com/api/episode/36", "https://rickandmortyapi.com/api/episode/37",
				"https://rickandmortyapi.com/api/episode/38", "https://rickandmortyapi.com/api/episode/39",
				"https://rickandmortyapi.com/api/episode/40", "https://rickandmortyapi.com/api/episode/41",
				"https://rickandmortyapi.com/api/episode/42", "https://rickandmortyapi.com/api/episode/43",
				"https://rickandmortyapi.com/api/episode/44", "https://rickandmortyapi.com/api/episode/45",
				"https://rickandmortyapi.com/api/episode/46", "https://rickandmortyapi.com/api/episode/47",
				"https://rickandmortyapi.com/api/episode/48", "https://rickandmortyapi.com/api/episode/49",
				"https://rickandmortyapi.com/api/episode/50", "https://rickandmortyapi.com/api/episode/51");

		return episodeList;
	}


}
