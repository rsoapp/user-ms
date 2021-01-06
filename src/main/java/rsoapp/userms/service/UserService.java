package rsoapp.userms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rsoapp.userms.config.ApplicationVariables;
import rsoapp.userms.model.dto.AdDto;
import rsoapp.userms.model.dto.UserAds;
import rsoapp.userms.model.dto.UserProfile;
import rsoapp.userms.model.entity.User;
import rsoapp.userms.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private ApplicationVariables applicationVariables;
    private String adMsUrl;

    @Autowired
    public UserService(UserRepository userRepository, RestTemplate restTemplate, ApplicationVariables applicationVariables) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.applicationVariables = applicationVariables;

        if (applicationVariables.getEnvironmentType().equals("prod")) {
            adMsUrl = "http://ad-ms:8080/v1/";
        }
        else {
            adMsUrl = "http://localhost:8081/v1/";
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    public ResponseEntity<UserProfile> getUserProfile(Integer userId) {
        Optional<User> userQuery = getUserById(userId);

        if (userQuery.isPresent()) {
            UserProfile userProfile = new UserProfile();
            User user = userQuery.get();
            userProfile.setUserData(user);

            try {
                UserAds userAds = restTemplate.getForObject(adMsUrl + "ads/user/" + userId.toString(), UserAds.class);
                userProfile.setUserAds(userAds);
                return new ResponseEntity<>(userProfile, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(userProfile, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
