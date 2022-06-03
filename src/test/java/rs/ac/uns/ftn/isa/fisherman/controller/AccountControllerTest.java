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
import rs.ac.uns.ftn.isa.fisherman.dto.MailDto;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerTest {

    private static final String URL_PREFIX = "/account";

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
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void testActiveAccount() throws Exception {
        UserRequestDTO userRequestDTO = new UserRequestDTO("isa.projekat.tim.15@gmail.com","Magdalena","Reljin","CABINOWNER","asdasdasd",0.0,"",0);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(userRequestDTO);
        mockMvc.perform(post(URL_PREFIX + "/acceptAccount").contentType(contentType).content(json))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void testDenyAccount() throws Exception {
        UserRequestDTO userRequestDTO = new UserRequestDTO("test@gmail.com","Rajko","Mitic","CLIENT","asdasdasd",0.0,"",0);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(userRequestDTO);
        mockMvc.perform(post(URL_PREFIX + "/denyAccount/"+"BadRequest.").contentType(contentType).content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Rollback(true)
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void testAcceptReasonForDeletingAccount() throws Exception {
        MailDto mailDto = new MailDto("Ok!","isa.testing.tim15@gmail.com");
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(mailDto);
        mockMvc.perform(post(URL_PREFIX + "/sendAcceptReason").contentType(contentType).content(json))
                .andExpect(status().isOk());
    }




}
