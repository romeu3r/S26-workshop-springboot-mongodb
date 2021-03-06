package com.romeu3r.workshopcourse.services;

import com.romeu3r.workshopcourse.domain.User;
import com.romeu3r.workshopcourse.dto.UserDTO;
import com.romeu3r.workshopcourse.repository.UserRepository;
import com.romeu3r.workshopcourse.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

    public User insert(User obj) {
        return userRepository.insert(obj);
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public User update(User user) {
        User obj = findById(user.getId());
        update(obj, user);
        return userRepository.save(obj);
    }

    private void update(User obj, User user) {
        obj.setName(user.getName());
        obj.setEmail(user.getEmail());
    }
}
