package com.example.signup.Service;

import com.example.signup.Dto.RegisterRequest;
import com.example.signup.Dto.RegisterResponse;
import com.example.signup.Entity.User;
import com.example.signup.Exception.UserAlreadyExistsException;
import com.example.signup.Repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException(
                    "Email already registered"
            );
        }

        if (userRepository.existsByMobile(request.getMobile())) {
            throw new UserAlreadyExistsException(
                    "Mobile number already registered"
            );
        }

        String hashedPassword =
                passwordEncoder.encode(request.getPassword());

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setPassword(hashedPassword);

        userRepository.save(user);

        return new RegisterResponse(
                true,
                "Registration successful"
        );
    }
}





