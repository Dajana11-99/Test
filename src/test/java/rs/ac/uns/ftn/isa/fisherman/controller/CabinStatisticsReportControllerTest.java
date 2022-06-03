package rs.ac.uns.ftn.isa.fisherman.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import rs.ac.uns.ftn.isa.fisherman.dto.*;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CabinStatisticsReportControllerTest {
    private static final String URL_PREFIX = "/cabinStatisticsReport";

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
    public void testCountSuccessfullReservations() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/countReservations/"+"co@gmail.com"+"/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2]",is(2))); //year, 0-week, 1-month
    }

    @Test
    @WithMockUser(authorities = "ROLE_CABINOWNER")
    public void testCreateIncomeReport() throws Exception {
        LocalDateTime startDate= LocalDateTime.of(2022,1,1,7,30);
        LocalDateTime endDate= LocalDateTime.of(2022,12,31,7,30);
        List<LocalDateTime> dateRange=new ArrayList<>();
        dateRange.add(startDate);
        dateRange.add(endDate);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(dateRange);

        mockMvc.perform(post(URL_PREFIX + "/sumProfit/"+"co@gmail.com"+"/").contentType(contentType).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]",is(48.5))) //res
                .andExpect(jsonPath("$[1]",is(0.0)));  // quick res
    }
}
