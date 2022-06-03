package rs.ac.uns.ftn.isa.fisherman.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import rs.ac.uns.ftn.isa.fisherman.dto.*;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdventureReservationControllerTest {

    private static final String URL_PREFIX = "/reservationAdventure";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(authorities = "ROLE_FISHING_INSTRUCTOR")
    public void testGetAllPastReservation() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getPastReservations/"+"fi@gmail.com"+"/")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(4)))
                .andExpect(jsonPath("$.[*].ownersUsername").value(hasItem("fi@gmail.com")));
    }


    @Test
    @WithMockUser(authorities = "ROLE_FISHING_INSTRUCTOR")
    public void testCreateReservationUnsuccessfull() throws Exception {
        AdventureDto adventureDto = new AdventureDto(1L,"Gepard fish adventure", new AddressDTO(21.1158834777415, 44.74496215218308, "Serbia","Kovin ", "Dunavska 3"),"The best adventure.","Licensed fishing instructor with 30 years of experience.",null,5, 200.0, "No non-swimmers.","Hooks, lines, sinkers, floats, rods, reels, baits, lures, spears, nets.",null,"NOT FREE","fi@gmail.com");
        AdventureReservationDto reservationDto = new AdventureReservationDto(null,LocalDateTime.of(2022,4,10,8,20,00),
                LocalDateTime.of(2022,4,10,18,10,00),"cl@gmail.com","Rasta Nikolic",
        new PaymentInformationDto(500.0,0.0,0.0),true,false,"fi@gmail.com",adventureDto,null,false);

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(reservationDto);
        mockMvc.perform(post(URL_PREFIX + "/instructorCreates").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testGetAvailableAdventures() throws Exception {
        SearchAvailablePeriodsBoatAndAdventureDto searchAvailablePeriodsAdventureDto = new SearchAvailablePeriodsBoatAndAdventureDto(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2),200, "cl@gmail.com",4.0,"","","",2);

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(searchAvailablePeriodsAdventureDto);
        mockMvc.perform(post(URL_PREFIX + "/getAvailableAdventures").contentType(contentType).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testMakeReservationSuccessful() throws Exception {
        AdventureDto adventureDto = new AdventureDto(1L,"Gepard fish adventure", new AddressDTO(21.1158834777415, 44.74496215218308, "Serbia","Kovin ", "Dunavska 3"),
                "The best adventure.","Licensed fishing instructor with 30 years of experience.",
                null,5, 200.0, "No non-swimmers.","Hooks, lines, sinkers, floats, rods, reels, baits, lures, spears, nets.",
                null,"NOT FREE","fi@gmail.com");
        LocalDateTime startDate = LocalDateTime.of(2022,7,1,15,48,11);
        LocalDateTime endDate = LocalDateTime.of(2022,7,3,15,48,11);
        AdventureReservationDto adventureReservationDto = new AdventureReservationDto(null, startDate,endDate,
                "cl@gmail.com", "cl@gmail.com", new PaymentInformationDto(500.0,0.0,0.0),false,
                false,"fi@gmail.com",adventureDto, null, false);

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(adventureReservationDto);
        mockMvc.perform(post(URL_PREFIX + "/makeReservation").contentType(contentType).content(json))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testMakeReservationBadRequestPenalties() throws Exception {
        AdventureDto adventureDto = new AdventureDto(1L,"Gepard fish adventure", new AddressDTO(21.1158834777415, 44.74496215218308, "Serbia","Kovin ", "Dunavska 3"),
                "The best adventure.","Licensed fishing instructor with 30 years of experience.",
                null,5, 200.0, "No non-swimmers.","Hooks, lines, sinkers, floats, rods, reels, baits, lures, spears, nets.",
                null,"NOT FREE","fi@gmail.com");
        LocalDateTime startDate = LocalDateTime.of(2022,7,1,15,48,11);
        LocalDateTime endDate = LocalDateTime.of(2022,7,3,15,48,11);
        AdventureReservationDto adventureReservationDto = new AdventureReservationDto(null, startDate,endDate,
                "miticrajko@gmail.com", "miticrajko@gmail.com", new PaymentInformationDto(500.0,0.0,0.0),false,
                false,"fi@gmail.com",adventureDto, null, false);

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(adventureReservationDto);
        mockMvc.perform(post(URL_PREFIX + "/makeReservation").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testMakeReservationBadRequestCancellation() throws Exception {
        AdventureDto adventureDto = new AdventureDto(1L,"Gepard fish adventure", new AddressDTO(21.1158834777415, 44.74496215218308, "Serbia","Kovin ", "Dunavska 3"),
                "The best adventure.","Licensed fishing instructor with 30 years of experience.",
                null,5, 200.0, "No non-swimmers.","Hooks, lines, sinkers, floats, rods, reels, baits, lures, spears, nets.",
                null,"NOT FREE","fi@gmail.com");
        LocalDateTime startDate = LocalDateTime.of(2022,7,3,15,48,11);
        LocalDateTime endDate = LocalDateTime.of(2022,7,5,15,48,11);
        AdventureReservationDto adventureReservationDto = new AdventureReservationDto(null, startDate,endDate,
                "jana@gmail.com", "jana@gmail.com", new PaymentInformationDto(500.0,0.0,0.0),false,
                false,"fi@gmail.com",adventureDto, null, false);

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(adventureReservationDto);
        mockMvc.perform(post(URL_PREFIX + "/makeReservation").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testCancelReservation() throws Exception {
        AdventureDto adventureDto = new AdventureDto(1L,"Gepard fish adventure", new AddressDTO(21.1158834777415, 44.74496215218308, "Serbia","Kovin ", "Dunavska 3"),
                "The best adventure.","Licensed fishing instructor with 30 years of experience.",
                null,5, 200.0, "No non-swimmers.","Hooks, lines, sinkers, floats, rods, reels, baits, lures, spears, nets.",
                null,"NOT FREE","fi@gmail.com");
        LocalDateTime startDate = LocalDateTime.of(2022,7,11,12,0,0);
        LocalDateTime endDate = LocalDateTime.of(2022,7,11,13,0,0);
        AdventureReservationDto adventureReservationDto = new AdventureReservationDto(7L, startDate,endDate,
                "cl@gmail.com", "cl@gmail.com", new PaymentInformationDto(500.0,0.0,0.0),false,
                false,"fi@gmail.com",adventureDto, null, false);

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(adventureReservationDto);
        mockMvc.perform(post(URL_PREFIX + "/cancelReservation").contentType(contentType).content(json))
                .andExpect(status().isOk());
    }
}
