package rs.ac.uns.ftn.isa.fisherman.controller;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CabinControllerTest {

    private static final String URL_PREFIX = "/cabins";

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
    public void testGetAllCabinsByOwnerUsername() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/findCabinsByOwnersUsername/"+"co@gmail.com"+"/")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(1)))
                .andExpect(jsonPath("$.[*].name").value(hasItem("Scandinavian cabin")))
                .andExpect(jsonPath("$.[*].price").value(hasItem(50.0)))
                .andExpect(jsonPath("$.[*].cancellingConditions").value(hasItem("NOT FREE")));
    }
    @Test
    @WithMockUser(authorities = "ROLE_CABINOWNER")
    public void testCanBeEditedOrDeleted() throws Exception {
        mockMvc.perform(post(URL_PREFIX + "/canBeEditedOrDeleted/"+"3")).andExpect(status().isOk())
                .andExpect(jsonPath("$",is(true)));
    }


}
