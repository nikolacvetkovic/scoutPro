package xyz.riocode.scoutpro.service;

import org.springframework.stereotype.Component;
import xyz.riocode.scoutpro.model.AppUser;

@Component
public class AppUserServiceImpl implements AppUserService {
    @Override
    public AppUser create(AppUser appUser) {
        return null;
    }

    @Override
    public AppUser changePassword(AppUser appUser) {
        return null;
    }

    @Override
    public void deleteById(Long userId) {

    }
}
