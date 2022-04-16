package rs.ac.uns.ftn.isa.fisherman.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import rs.ac.uns.ftn.isa.fisherman.dto.*;

import java.beans.Transient;
import java.nio.charset.Charset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationControllerTest {
    private static final String URL_PREFIX = "/auth";

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
    public void testLogIn() throws Exception {
        LogInDto logInDto = new LogInDto("dajanazlokapa1@gmail.com","123");
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(logInDto);
        mockMvc.perform(post(URL_PREFIX + "/login").contentType(contentType).content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void testLogInUnsuccessfull() throws Exception {
        LogInDto logInDto = new LogInDto("da@gmail.com","123gnh");
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(logInDto);
        mockMvc.perform(post(URL_PREFIX + "/login").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testsignUp() throws Exception {
       AddressDTO addressDTO = new AddressDTO(34,56,"Serbia","Novi Sad","Nikola Tesla 23");
        UserRequestDTO userRequestDTO = new UserRequestDTO(null,"bi@gmail.com","123","boki","bokica","568568845",addressDTO,"hhghg","FISHINGINSTRUCTOR",0.0);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(userRequestDTO);
        mockMvc.perform(post(URL_PREFIX + "/signUpFishingInstructor").contentType(contentType).content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void testsignUpUnsuccessfull() throws Exception {
        AddressDTO addressDTO = new AddressDTO(34,56,"Serbia","Novi Sad","Nikola Tesla 23");
        UserRequestDTO userRequestDTO = new UserRequestDTO(null,"dajanazlokapa1@gmail.com","123","boki","bokica","568568845",addressDTO,"hhghg","FISHINGINSTRUCTOR",0.0);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(userRequestDTO);
        mockMvc.perform(post(URL_PREFIX + "/signUpFishingInstructor").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());
    }


}
