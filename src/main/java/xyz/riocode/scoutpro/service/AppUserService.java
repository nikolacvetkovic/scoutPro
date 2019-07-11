package xyz.riocode.scoutpro.service;

import xyz.riocode.scoutpro.model.AppUser;

public interface AppUserService {

    AppUser create(AppUser appUser);
    AppUser getByUsername(String username);
    AppUser changePassword(AppUser appUser);
    void deleteById(Long userId);
}
