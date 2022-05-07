package kz.narxoz.sabak.app.service;

import kz.narxoz.sabak.app.model.Role;
import kz.narxoz.sabak.app.model.User;
import kz.narxoz.sabak.app.repository.RoleRepository;
import kz.narxoz.sabak.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(List.of(roleRepository.getById(1L)))); // USER
        userRepository.save(user);
    }
}