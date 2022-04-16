package rs.ac.uns.ftn.isa.fisherman.controller;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
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
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoatReservationControllerTest {
    private static final String URL_PREFIX = "/reservationBoat";

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
    @WithMockUser(authorities = "ROLE_BOATOWNER")

    public void testCreateReservationUnsuccessfullBecauseBoatIsOccupied() throws Exception {

        BoatDto boatDto=new BoatDto(3L,"bo@gmail.com","Jarrett Bay 46","Jarrett Bay 46",18,"A578","12333","3213",
                "GPS, gyro compass, ata",new AddressDTO(20.933783791650026,44.673293602502106,
                "Serbia","Smederevo","Djure Strugara 13"),"The best.",null,6,"No non-swimmers.",
                "Spears, nets, gaffs, traps, waders and tackle boxes.",200.0,null,"NOT FREE",0);
        LocalDateTime startDate= LocalDateTime.of(2022,7,13,7,30);
        LocalDateTime endDate= LocalDateTime.of(2022,7,15,7,30);
        BoatReservationDto boatReservationDto=new BoatReservationDto(7L,startDate,endDate,"cl@gmail.com",
                "Rasta Nikolic",new PaymentInformationDto(500.0,0.0,0.0),true,false,
                "bo@gmail.com",boatDto, null,false,false);

        ObjectMapper objectMapper=new ObjectMapper();

        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(boatReservationDto);
        mockMvc.perform(post(URL_PREFIX + "/ownerCreates/"+"bo@gmail.com"+"/").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

    }
    @Test
    @WithMockUser(authorities = "ROLE_BOATOWNER")
    public void testOwnerCreatesReportAboutPastReservation() throws Exception {

        OwnersReportDto ownersReportDto=new OwnersReportDto(1L,true,"Client didn't show up.",false,"bo@gmail.com","cl@gmail.com",false);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(ownersReportDto);
        mockMvc.perform(post(URL_PREFIX + "/ownerCreatesReview/"+"4").contentType(contentType).content(json))
                .andExpect(status().isOk());

    }
    @Test
    @WithMockUser(authorities = "ROLE_BOATOWNER")
    public void testFindFutureReservations() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getByBoatId/"+"1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(0)));
    }

}
