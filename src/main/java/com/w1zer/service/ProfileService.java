package com.w1zer.service;

import com.w1zer.entity.Profile;
import com.w1zer.repository.ProfileRepository;

public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    public Profile login(String login, String password) {
        Profile profile = profileRepository.getByLogin(login);
        if (profile != null && getPasswordHash(password).equals(profile.getPassword())){
            return profile;
        }
        return null;
    }

    public boolean canRegister(String login) {
        Profile profile = profileRepository.getByLogin(login);
        return profile == null;
    }

    public void register(Profile profile) {
        profile.setPassword(getPasswordHash(profile.getPassword()));
        profileRepository.insert(profile);
    }
    private String getPasswordHash(String password){
        return String.valueOf(password.hashCode());
    }

}
