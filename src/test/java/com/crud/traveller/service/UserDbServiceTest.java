package com.crud.traveller.service;

import com.crud.traveller.entity.*;;
import com.crud.traveller.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;

@RunWith (SpringRunner.class)
public class UserDbServiceTest {
    @InjectMocks
    UserDbService userDbService;
    @Mock
    UserRepository userRepository;

    @Test
    public void findAllUsersTest() {
        //Given
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        User user1 = new User (1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly);
        User user2 = new User (2L, "Marek", "456", excursionsItaly, currencyItaly, weatherItaly);

        List<User> usersList = new ArrayList<> ();
        usersList.add (user1);
        usersList.add (user2);
        when (userRepository.findAll()).thenReturn (usersList);
        //When
        List<User> list = userDbService.findAllUsers ();
        //Then
        Assert.assertEquals (2, list.size ());
        Assert.assertEquals (1L, list.get (0).getUserId().longValue());
        Assert.assertEquals (2L, list.get (1).getUserId().longValue());
    }
    @Test
    public void getUserTest(){
        //Given
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        User user1 = new User (1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly);

        when(userRepository.findById(user1.getUserId ())).thenReturn( Optional.ofNullable(user1));
        //When
        Optional<User> fetchedUser = userDbService.getUser (user1.getUserId());
        //Then
        Assert.assertNotNull(fetchedUser);
    }

    @Test
    public void saveUserTest(){
        //Given
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        User user1 = new User (1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly);

        when(userRepository.save(user1)).thenReturn(user1);
        //When
        User savedUser= userDbService.saveUser(user1);
        //Then
        Assert.assertEquals(user1, savedUser);
        Assert.assertEquals(1L, savedUser.getUserId().longValue ());
        Assert.assertEquals("Darek", savedUser.getUserName ());
        Assert.assertEquals ("123", savedUser.getUserKey ());
    }

    @Test
    public void deleteUserTest() {
        //Given
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        User user1 = new User (1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly);

        when (userRepository.save (user1)).thenReturn (user1);
        //When
        userDbService.saveUser (user1);
        userDbService.deleteUser (user1.getUserId());
        //Then
        Assert.assertFalse (userDbService.isExist(user1.getUserId()));
    }
}