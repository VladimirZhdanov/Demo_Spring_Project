package com.homel.demo.project.init;

import com.homel.demo.project.entity.AuthorityEntity;
import com.homel.demo.project.entity.RoleEntity;
import com.homel.demo.project.entity.UserEntity;
import com.homel.demo.project.repository.AuthorityRepository;
import com.homel.demo.project.repository.RoleRepository;
import com.homel.demo.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

import static com.homel.demo.project.security.Authorities.*;
import static com.homel.demo.project.security.Roles.ROLE_ADMIN;
import static com.homel.demo.project.security.Roles.ROLE_USER;

@Component
@AllArgsConstructor
public class InitialUsersSetup {
    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        AuthorityEntity readAuthority = createAuthority(READ_AUTHORITY.toString());
        AuthorityEntity writeAuthority = createAuthority(WRITE_AUTHORITY.toString());
        AuthorityEntity deleteAuthority = createAuthority(DELETE_AUTHORITY.toString());
        createRole(ROLE_USER.toString(), Arrays.asList(readAuthority, writeAuthority));
        RoleEntity roleAdmin = createRole(ROLE_ADMIN.toString(), Arrays.asList(readAuthority, writeAuthority, deleteAuthority));

        UserEntity adminUser = new UserEntity();
        adminUser.setName("Admin Name");
        adminUser.setEmail("admin@test.com");
        adminUser.setEmailVerificationStatus(true);
        adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("12345678"));
        adminUser.getRoles().add(roleAdmin);

        userRepository.save(adminUser);
    }

    private AuthorityEntity createAuthority(String name) {
        AuthorityEntity authority = authorityRepository.findByName(name);
        if (authority == null) {
            authority = new AuthorityEntity();
            authority.setName(name);
            authorityRepository.save(authority);
        }
        return authority;
    }

    private RoleEntity createRole(String name, Collection<AuthorityEntity> authorityEntities) {
        RoleEntity role = roleRepository.findByName(name);
        if (role == null) {
            role = new RoleEntity();
            role.setName(name);
            role.setAuthorities(authorityEntities);
            roleRepository.save(role);
        }
        return role;
    }
}
