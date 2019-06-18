package com.riocode.scoutpro.service;

import com.riocode.scoutpro.model.AppUser;

public interface AppUserService {

    AppUser create(AppUser appUser);
    AppUser changePassword(AppUser appUser);
    void deleteById(Long userId);
}
