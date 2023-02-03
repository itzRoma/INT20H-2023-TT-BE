package org.sevenorganization.int20h2023ttbe.service;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.entity.User;
import org.sevenorganization.int20h2023ttbe.exception.entity.EntityExistsException;
import org.sevenorganization.int20h2023ttbe.exception.entity.EntityNotFoundException;
import org.sevenorganization.int20h2023ttbe.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityExistsException("Email is already taken");
        }
        return userRepository.save(user);
    }

    public List<User> readAllUsers() {
        return userRepository.findAll();
    }

    public User readUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw EntityNotFoundException.withId(User.class, id);
        });
    }

    @Transactional
    public User updateUser(User target, User source) {
        if (userRepository.existsByEmail(source.getEmail()) && !target.getEmail().equals(source.getEmail())) {
            throw new EntityExistsException("Email is already taken");
        }
        BeanUtils.copyProperties(source, target, "id", "password");
        return userRepository.save(target);
    }

    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
