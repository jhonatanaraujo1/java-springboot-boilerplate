package exemple.backend.auth.service.interfaces;

import exemple.backend.auth.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity save(UserEntity userEntity);
    List<UserEntity> findAll();
    void delete(long id);
    UserEntity findOne(String username);

    UserEntity findById(Long id);

    UserEntity setUserRole(long userRoleId, UserEntity userEntity);

    boolean isUserExists(String email);
}
