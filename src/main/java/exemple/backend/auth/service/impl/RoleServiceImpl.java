package exemple.backend.auth.service.impl;

import exemple.backend.auth.repository.UserRepository;
import exemple.backend.auth.repository.UserRoleRepository;
import exemple.backend.auth.dto.RoleDto;
import exemple.backend.auth.entity.PermissionEntity;
import exemple.backend.auth.entity.PermissionsEntity;
import exemple.backend.auth.entity.UserEntity;
import exemple.backend.auth.repository.PermissionRepository;
import exemple.backend.auth.repository.PermissionsRepository;
import exemple.backend.auth.service.interfaces.ICrudService;
import exemple.backend.auth.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements ICrudService<RoleDto, RoleEntity> {

    private final UserRoleRepository userRoleRepository;
    private final PermissionRepository permissionRepository;
    private final PermissionsRepository pRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public RoleEntity create(RoleDto roleDto) {
        RoleEntity roleEntity = modelMapper.map(roleDto, RoleEntity.class);
        return userRoleRepository.save(roleEntity);
    }

    @Override
    public RoleEntity update(RoleDto roleDto) {
        RoleEntity roleEntity = modelMapper.map(roleDto, RoleEntity.class);
        return userRoleRepository.save(roleEntity);
    }

    @Override
    public boolean delete(RoleDto roleDto) {
        RoleEntity roleEntity = modelMapper.map(roleDto, RoleEntity.class);
        userRoleRepository.delete(roleEntity);
        return true;
    }

    @Override
    public boolean delete(long id) {
        userRoleRepository.deleteById(id);
        return true;
    }

    @Override
    public RoleEntity getById(long id) {
        return userRoleRepository.findById(id).get();
    }

    @Override
    public List<RoleEntity> getAll() {
        return (List<RoleEntity>) userRoleRepository.findAll();
    }

    public RoleEntity addPermissions(long roleId, List<Long> permissions) {
        RoleEntity userRoleEntity = userRoleRepository.findById(roleId).get();
        List<UserEntity> userEntityList = userRepository.findUserEntityByRoleEntity(userRoleEntity);
        userRoleEntity.getPermissions().clear();
        for (long permission : permissions) {
            PermissionEntity role = permissionRepository.findById(permission).get();
            userRoleEntity.getPermissions().add(role);
        }
        userRoleEntity = userRoleRepository.save(userRoleEntity);
        Set<PermissionEntity> roles = new HashSet<>(userRoleEntity.getPermissions());
        for (UserEntity userEntity : userEntityList) {
            userEntity.setPermissions(roles);
            userRepository.save(userEntity);
        }

        return userRoleEntity;
    }

    public RoleEntity addPermissionsByName(long roleId, List<String> name) {
        List<PermissionEntity> permissionEntity = new ArrayList<>();
        for (String perm : name) {
            permissionEntity.add(permissionRepository.findPermissionEntitiesByName(perm));
        }
        RoleEntity roleEntity = userRoleRepository.findById(roleId).get();
        roleEntity.setPermissions(permissionEntity);
        roleEntity = userRoleRepository.save(roleEntity);
        return roleEntity;
    }

    public void addPermissions(long userId, Long permissionId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        PermissionsEntity permission = pRepository.findById(permissionId).get();
        PermissionsEntity permissions = new PermissionsEntity();
        permissions.setUser(userEntity.getId());
        permissions.setPermission(permission.getPermission());
        pRepository.save(permissions);
    }


    @Override
    public List<RoleEntity> getPaginate(long page, long offset) {
        return null;
    }
}
