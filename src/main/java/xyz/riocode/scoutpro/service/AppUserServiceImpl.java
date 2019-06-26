package xyz.riocode.scoutpro.service;

import org.springframework.stereotype.Component;
import xyz.riocode.scoutpro.exception.AppUserNotFoundException;
import xyz.riocode.scoutpro.exception.DuplicateAppUserUsernameException;
import xyz.riocode.scoutpro.model.AppUser;
import xyz.riocode.scoutpro.repository.AppUserRepository;

@Component
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser create(AppUser appUser) {
        appUserRepository.findByUsername(appUser.getUsername())
                .ifPresent(u -> {
                    throw new DuplicateAppUserUsernameException();
                });

        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser changePassword(AppUser appUser) {
        AppUser user = appUserRepository.findByUsername(appUser.getUsername())
            .orElseThrow(AppUserNotFoundException::new);

        user.setPassword(appUser.getPassword());

        return appUserRepository.save(user);
    }

    @Override
    public void deleteById(Long userId) {
        appUserRepository.deleteById(userId);
    }
}
