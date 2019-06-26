package xyz.riocode.scoutpro.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import xyz.riocode.scoutpro.exception.AppUserNotFoundException;
import xyz.riocode.scoutpro.exception.DuplicateAppUserUsernameException;
import xyz.riocode.scoutpro.model.AppUser;
import xyz.riocode.scoutpro.repository.AppUserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppUserServiceImplTest {


    @Mock
    AppUserRepository appUserRepository;

    @InjectMocks
    AppUserServiceImpl appUserService;

    AppUser user;

    @BeforeEach
    void setUp(){
        user = new AppUser();
        user.setId(1L);
        user.setUsername("cvelenidza");
        user.setPassword("123456");
    }

    @Test
    void testDeleteByIdOk(){
        appUserService.deleteById(1L);

        verify(appUserRepository).deleteById(anyLong());
    }

    @Test
    void testDeleteByIdNotFound(){
        doThrow(EmptyResultDataAccessException.class).when(appUserRepository).deleteById(anyLong());

        assertThrows(EmptyResultDataAccessException.class, () -> appUserService.deleteById(1L));

        verify(appUserRepository).deleteById(anyLong());
    }

    @Test
    void createUserOk(){
        AppUser userToReturn = new AppUser();
        userToReturn.setId(1L);
        userToReturn.setUsername("cvelenidza");

        when(appUserRepository.save(any())).thenReturn(userToReturn);

        AppUser savedUser = appUserService.create(user);

        assertEquals(user.getId(), savedUser.getId());
        assertEquals(user.getUsername(), savedUser.getUsername());
        verify(appUserRepository).findByUsername(anyString());
    }

    @Test
    void createUserDuplicateUsername(){
        when(appUserRepository.save(user)).thenThrow(DuplicateAppUserUsernameException.class);

        assertThrows(DuplicateAppUserUsernameException.class, () -> appUserService.create(user));

        verify(appUserRepository).findByUsername(anyString());
    }

    @Test
    void changePasswordOk(){
        AppUser userToReturn = new AppUser();
        userToReturn.setId(1L);
        userToReturn.setUsername("cvelenidza");
        userToReturn.setPassword("123456");

        when(appUserRepository.findByUsername(anyString())).thenReturn(Optional.of(new AppUser()));
        when(appUserRepository.save(any())).thenReturn(userToReturn);

        AppUser updatedUser = appUserService.changePassword(user);

        assertEquals(userToReturn.getPassword(), updatedUser.getPassword());
    }

    @Test
    void changePasswordUserNotExists(){
        assertThrows(AppUserNotFoundException.class, () -> appUserService.changePassword(user));
    }

}