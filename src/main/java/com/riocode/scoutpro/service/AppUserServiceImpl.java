package com.riocode.scoutpro.service;

import com.riocode.scoutpro.model.AppUser;
import org.springframework.stereotype.Component;

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
