package me.yunb.miristudy.service;

import lombok.RequiredArgsConstructor;
import me.yunb.miristudy.domain.User;
import me.yunb.miristudy.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
