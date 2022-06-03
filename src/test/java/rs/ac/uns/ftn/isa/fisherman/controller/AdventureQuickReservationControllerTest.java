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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdventureQuickReservationControllerTest {

    private static final String URL_PREFIX = "/quickReservationAdventure";

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
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testMakeQuickReservationSuccessful() throws Exception {
        AdventureDto adventureDto = new AdventureDto(1L,"Gepard fish adventure", new AddressDTO(21.1158834777415, 44.74496215218308, "Serbia","Kovin ", "Dunavska 3"),
                "The best adventure.","Licensed fishing instructor with 30 years of experience.",
                null,5, 200.0, "No non-swimmers.","Hooks, lines, sinkers, floats, rods, reels, baits, lures, spears, nets.",
                null,"NOT FREE","fi@gmail.com");
        LocalDateTime startDate = LocalDateTime.of(2022,7,12,12,0,0);
        LocalDateTime endDate = LocalDateTime.of(2022,7,12,13,0,0);
        QuickReservationAdventureDto adventureReservationDto = new QuickReservationAdventureDto(1L, startDate,endDate,
                "cl@gmail.com", "cl@gmail.com", new PaymentInformationDto(500.0,0.0,0.0),false,
                false,"fi@gmail.com",adventureDto, null,10, false);

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(adventureReservationDto);
        mockMvc.perform(post(URL_PREFIX + "/makeQuickReservation").contentType(contentType).content(json))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testMakeQuickReservationBadRequestCancellation() throws Exception {
        AdventureDto adventureDto = new AdventureDto(1L,"Gepard fish adventure", new AddressDTO(21.1158834777415, 44.74496215218308, "Serbia","Kovin ", "Dunavska 3"),
                "The best adventure.","Licensed fishing instructor with 30 years of experience.",
                null,5, 200.0, "No non-swimmers.","Hooks, lines, sinkers, floats, rods, reels, baits, lures, spears, nets.",
                null,"NOT FREE","fi@gmail.com");
        LocalDateTime startDate = LocalDateTime.of(2022,7,12,12,0,0);
        LocalDateTime endDate = LocalDateTime.of(2022,7,12,13,0,0);
        QuickReservationAdventureDto adventureReservationDto = new QuickReservationAdventureDto(1L, startDate,endDate,
                "jana@gmail.com", "jana@gmail.com", new PaymentInformationDto(500.0,0.0,0.0),false,
                false,"fi@gmail.com",adventureDto, null,10, false);

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(adventureReservationDto);
        mockMvc.perform(post(URL_PREFIX + "/makeQuickReservation").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = "ROLE_CLIENT")
    public void testMakeQuickReservationBadRequestPenalties() throws Exception {
        AdventureDto adventureDto = new AdventureDto(1L,"Gepard fish adventure", new AddressDTO(21.1158834777415, 44.74496215218308, "Serbia","Kovin ", "Dunavska 3"),
                "The best adventure.","Licensed fishing instructor with 30 years of experience.",
                null,5, 200.0, "No non-swimmers.","Hooks, lines, sinkers, floats, rods, reels, baits, lures, spears, nets.",
                null,"NOT FREE","fi@gmail.com");
        LocalDateTime startDate = LocalDateTime.of(2022,7,12,12,0,0);
        LocalDateTime endDate = LocalDateTime.of(2022,7,12,13,0,0);
        QuickReservationAdventureDto adventureReservationDto = new QuickReservationAdventureDto(1L, startDate,endDate,
                "miticrajko@gmail.com", "miticrajko@gmail.com", new PaymentInformationDto(500.0,0.0,0.0),false,
                false,"fi@gmail.com",adventureDto, null,10, false);

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(adventureReservationDto);
        mockMvc.perform(post(URL_PREFIX + "/makeQuickReservation").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());
    }
}
