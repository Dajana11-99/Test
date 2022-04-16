package rs.ac.uns.ftn.isa.fisherman.controller;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.api.client.googleapis.testing.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import rs.ac.uns.ftn.isa.fisherman.dto.*;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CabinQuickReservationControllerTest {
    private static final String URL_PREFIX = "/quickReservationCabin";

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
    @WithMockUser(authorities = "ROLE_CABINOWNER")
    public void testCreateQuickReservationUnsuccessfullBecauseAvailablePeriodIsNotDefined() throws Exception {
    AdditionalServicesDto additionalService1=new AdditionalServicesDto(4L,"WiFi",11);
    AdditionalServicesDto additionalService2=new AdditionalServicesDto(3L,"Parking",0);
    Set<AdditionalServicesDto> additionalServicesDtoList=new HashSet<>();
    additionalServicesDtoList.add(additionalService1);
    additionalServicesDtoList.add(additionalService2);
        CabinDto cabinDto=new CabinDto(2L,"Rustic river cabin","Riverside cabin. Fishing paradise.",
                1,3,"No rules.",100.0,new AddressDTO(20.286003649944078,44.9576790966988,"Serbia",
                "Stara Pazova","Ribarska 11"),additionalServicesDtoList,0.0,null,"co@gmail.com",
                "FREE");
        LocalDateTime startDate=LocalDateTime.now(); ///LocalDateTime.of(2022,7,13,7,30);
        LocalDateTime endDate= LocalDateTime.now();   ///LocalDateTime.of(2022,7,15,7,30);

        QuickReservationCabinDto quickReservationCabinDto=new QuickReservationCabinDto(1L,startDate,endDate,"jana@gmail.com",
                "Jana Todorovic",new PaymentInformationDto(200.0,0.0,0.0),true,false,
                "co@gmail.com",cabinDto,null,10,false);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(quickReservationCabinDto);
        mockMvc.perform(post(URL_PREFIX + "/ownerCreates").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = "ROLE_CABINOWNER")
    public void testCreateQuickReservationSuccessfull() throws Exception {
        AdditionalServicesDto additionalService1=new AdditionalServicesDto(4L,"WiFi",11);
        AdditionalServicesDto additionalService2=new AdditionalServicesDto(3L,"Parking",0);
        Set<AdditionalServicesDto> additionalServicesDtoList=new HashSet<>();
        additionalServicesDtoList.add(additionalService1);
        additionalServicesDtoList.add(additionalService2);
        CabinDto cabinDto=new CabinDto(2L,"Rustic river cabin","Riverside cabin. Fishing paradise.",
                1,3,"No rules.",100.0,new AddressDTO(20.286003649944078,44.9576790966988,"Serbia",
                "Stara Pazova","Ribarska 11"),additionalServicesDtoList,0.0,null,"co@gmail.com",
                "FREE");
        LocalDateTime startDate= LocalDateTime.of(2022,7,21,7,30);
        LocalDateTime endDate= LocalDateTime.of(2022,7,24,7,30);

        QuickReservationCabinDto quickReservationCabinDto=new QuickReservationCabinDto(1L,startDate,endDate,"jana@gmail.com",
                "Jana Todorovic",new PaymentInformationDto(200.0,0.0,0.0),true,false,
                "co@gmail.com",cabinDto,null,10,false);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
       String json=objectMapper.writeValueAsString(quickReservationCabinDto);

        mockMvc.perform(post(URL_PREFIX + "/ownerCreates").contentType(contentType).content(json))
                .andExpect(status().isCreated());
    }


}
