package com.diploma.application.service;

import com.diploma.application.exception.OAuth2AuthenticationProcessingException;
import com.diploma.application.model.AuthProvider;
import com.diploma.application.model.Role;
import com.diploma.application.model.User;
import com.diploma.application.repository.UserRepository;
import com.diploma.application.security.UserPrincipal;
import com.diploma.application.security.oauth2.OAuth2UserInfo;
import com.diploma.application.security.oauth2.OAuth2UserInfoFactory;
import com.diploma.application.util.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.util.StringUtils;

@Service
public class UserService extends DefaultOAuth2UserService implements UserDetailsService {

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByName(s);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        return userRepository.findById(id).orElseThrow(() ->new EntityNotFoundException(id));
    }

    public void register(String name, String pass){
        User user = new User();
        user.setName(name);
        user.setPassword(pass);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return;
    }

    public User registerLocal(SignUpRequest signUpRequest){
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        Set<Role> role = new HashSet();
        role.add(Role.USER);
        user.setRoles(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;

    }

    public User findByUserName(String name){
        return userRepository.findByName(name);
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        System.out.println("OAuth2User loadUser");

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        User userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if(userOptional!=null) {
            user = userOptional;
            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();

        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }

    @Transactional
    public UserDetails loadUserById(String id) {
        System.out.println("UserDetails loadUserById:" +id);
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public User loadByEmail(String email) throws Exception {
        System.out.println("User loadByEmail:" +email);
        System.out.println("EMAIL: "+email);
        User user = userRepository.findByEmail(email);
        if (user == null) throw new Exception("USER IS NULL");

        System.out.println("returning User, with email:"+user.getEmail()+" and Id:"+user.getId());

        return user;
       // return UserPrincipal.create(user);
    }

    public void addTeacherRole(User user){
        Set roles = user.getRoles();
        if (!roles.contains(Role.TEACHER)&&roles.contains(Role.USER)){
            roles.add(Role.TEACHER);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}
