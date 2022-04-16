package rs.ac.uns.ftn.isa.fisherman.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.context.WebApplicationContext;
import rs.ac.uns.ftn.isa.fisherman.dto.AddressDTO;
import rs.ac.uns.ftn.isa.fisherman.dto.AdventureDto;

import javax.transaction.Transactional;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdventureControllerTest {


    private static final String URL_PREFIX = "/adventures";

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
    @Transactional
    @Rollback(true)
    @WithMockUser(authorities = "ROLE_FISHING_INSTRUCTOR")
    public void testDeleteAdventure() throws Exception {
        AdventureDto adventureDto = new AdventureDto(2L,"Teaching kids how to fish", new AddressDTO(21.1158834777415, 44.74496215218308, "Serbia","Kovin ", "Dunavska 3"),"The best adventure.","Licensed fishing instructor with 30 years of experience.",null,5, 200.0, "No non-swimmers.","Hooks, lines, sinkers, floats, rods, reels, baits, lures, spears, nets.",null,"NOT FREE","fi@gmail.com");
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(adventureDto);
        mockMvc.perform(post(URL_PREFIX + "/deleteAdventure").contentType(contentType).content(json)).andExpect(status().isOk());
    }

}
