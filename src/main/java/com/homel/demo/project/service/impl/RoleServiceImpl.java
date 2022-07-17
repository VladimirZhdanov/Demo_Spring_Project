package com.homel.demo.project.service.impl;

import com.homel.demo.project.dto.RoleDTO;
import com.homel.demo.project.mapper.RoleMapper;
import com.homel.demo.project.repository.RoleRepository;
import com.homel.demo.project.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleDTO getRole(String name) {
        return roleMapper.dto(roleRepository.findByName(name));
    }
}
