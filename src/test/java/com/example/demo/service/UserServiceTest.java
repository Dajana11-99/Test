package com.example.demo.service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.mail.AccountAcceptedInfo;
import com.example.demo.mail.AccountDeniedInfo;
import com.example.demo.mail.MailService;
import com.example.demo.model.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private MailService emailServiceImpl;

    @Autowired
    private Environment env;

    private JavaMailSender javaMailSender;

    private MimeMessage mimeMessage;

    @Before
    public void before() {
        mimeMessage = new MimeMessage((Session)null);
        javaMailSender = mock(JavaMailSender.class);
        env = mock(Environment.class);
        when(env.getProperty("spring.mail.username"))
                .thenReturn("spring.mail.username");
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        emailServiceImpl = new MailService(env,javaMailSender);

    }


    @Test
    public void testAcceptAccount() throws MessagingException {
        FishingInstructor fishingInstructor = new FishingInstructor(1L,"Instructor","Instructorcic","dajanazlokapa1@gmail.com","123",null,null,null,null,"dasdasdasd");

        when(userRepositoryMock.findAll()).thenReturn(Arrays.asList(fishingInstructor, new BoatOwner()));
        when(userRepositoryMock.save(fishingInstructor)).thenReturn(fishingInstructor);
        when(userRepositoryMock.findByUsername(fishingInstructor.getUsername())).thenReturn(fishingInstructor);

        userService.acceptAccount(fishingInstructor);

        User userResult = userService.findByUsername(fishingInstructor.getUsername());
        assertThat(userResult.isEnabled()).isTrue();

        assertThat(emailServiceImpl.sendMail(userResult.getUsername(),userResult.getUsername(),new AccountAcceptedInfo())).isTrue();
    }

    @Test
    public void testDenyAccount() throws MessagingException {
        FishingInstructor fishingInstructor = new FishingInstructor(1L,"Instructor","Instructorcic","milos12@gmail.com","123",null,null,null,null,"dasdasdasd");
        String reason= "Because he is bad man.";
        when(userRepositoryMock.findAll()).thenReturn(Arrays.asList(fishingInstructor, new BoatOwner(),new FishingInstructor()));
        doNothing().when(userRepositoryMock).delete(fishingInstructor);

        userService.denyAccount(fishingInstructor,reason);

        User userResult = userService.findByUsername(fishingInstructor.getUsername());
        assertThat(userResult).isNull();
        assertThat(emailServiceImpl.sendMail(fishingInstructor.getUsername(),reason,new AccountDeniedInfo())).isTrue();

    }
    @Test
    @Transactional
    @Rollback(true)
    public void testEditCabinOwnerPersonalInformation() {
        UserRequestDTO userRequestDTO=new UserRequestDTO(1L,"milica@gmail.com","123",
                "Milica","Jovanovic", "021/345-345",
                new AddressDTO(	19.833549,45.267136,"Serbia",
                "Novi Sad","Novosadska 1"),"reason","CABINOWNER",3.4,"",0);
        User user=new CabinOwner(1L,"Milica","Petrovic","milica@gmail.com",
                "123","011/345-345",new Address(20.457273,44.787197,"Serbia",
                "Beograd","Beogradska 1"),"reason",3.4);

        when(userRepositoryMock.getAll()).thenReturn(Arrays.asList(user));
        when(userRepositoryMock.findByUsername(userRequestDTO.getUsername())).thenReturn(user);

        userService.editUser(userRequestDTO);
        userRepositoryMock.save(user);

        assertEquals(user.getAddress().getCity(),userRequestDTO.getAddress().getCity());
        assertEquals(user.getAddress().getStreetAndNum(),userRequestDTO.getAddress().getStreetAndNum());
        assertEquals(user.getPhoneNum(),userRequestDTO.getPhoneNum());
        assertEquals(user.getLastName(),userRequestDTO.getLastname());
    }
}
