package com.homel.demo.project.service.impl;

import com.homel.demo.project.dto.UserDTO;
import com.homel.demo.project.entity.UserEntity;
import com.homel.demo.project.mapper.UserMapper;
import com.homel.demo.project.repository.RoleRepository;
import com.homel.demo.project.repository.UserRepository;
import com.homel.demo.project.security.UserPrincipal;
import com.homel.demo.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.homel.demo.project.error.ErrorMessages.USER_IS_ALREADY_EXISTED;
import static com.homel.demo.project.error.ErrorMessages.USER_IS_NOT_FOUND;
import static com.homel.demo.project.security.Roles.ROLE_USER;
import static com.homel.demo.project.utils.CommonUtils.getExceptionMessage;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDTO createNewUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new RuntimeException(USER_IS_ALREADY_EXISTED.getValue());
        }

        UserEntity userEntity = userMapper.entity(userDTO);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userEntity.getRoles().add(roleRepository.findByName(ROLE_USER.toString()));

        return userMapper.dto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDTO getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return userMapper.dto(userEntity);
    }

    @Override
    @Transactional
    public UserDTO getUser(long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException(getExceptionMessage(USER_IS_NOT_FOUND.getValue(),
                    List.of(Pair.of("id", String.valueOf(id)))));
        }
        return userMapper.dto(userEntity.get());
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userDTO.getId()).orElseThrow(() -> {
            throw new UsernameNotFoundException(getExceptionMessage(USER_IS_NOT_FOUND.getValue(),
                    List.of(Pair.of("id", String.valueOf(userDTO.getId())))));
        });

        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        return userMapper.dto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> {
            throw new UsernameNotFoundException(getExceptionMessage(USER_IS_NOT_FOUND.getValue(),
                    List.of(Pair.of("id", String.valueOf(id)))));
        });

        userRepository.delete(userEntity);
    }

    @Override
    @Transactional
    public List<UserDTO> getUsers(int page, int limit) {
        return userMapper.dtos(userRepository.findAll(PageRequest.of(page, limit)).getContent());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(getExceptionMessage(USER_IS_NOT_FOUND.getValue(),
                    List.of(Pair.of("email", email))));
        }

        return new UserPrincipal(userEntity);
    }
}
