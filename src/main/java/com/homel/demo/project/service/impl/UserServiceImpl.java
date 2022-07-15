package com.homel.demo.project.service.impl;

import com.homel.demo.project.dto.UserDTO;
import com.homel.demo.project.entity.UserEntity;
import com.homel.demo.project.mapper.UserMapper;
import com.homel.demo.project.repository.UserRepository;
import com.homel.demo.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO save(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new RuntimeException("User is already existed...");
        }

        UserEntity userEntity = userMapper.entity(userDTO);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userMapper.dto(savedUserEntity);
    }

    @Override
    public UserDTO getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return userMapper.dto(userEntity);
    }

    @Override
    public UserDTO getUser(long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException(String.valueOf(id));
        }
        return userMapper.dto(userEntity.get());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userDTO.getId()).orElseThrow(() -> {
            throw new UsernameNotFoundException("User is not found: " + userDTO.getId());
        });

        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        return userMapper.dto(userRepository.save(userEntity));
    }

    @Override
    public void deleteUser(long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> {
            throw new UsernameNotFoundException("User is not found: " + id);
        });

        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDTO> getUsers(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);

        return userMapper.dtos(userRepository.findAll(pageable).getContent());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
