package exemple.backend.auth.repository;

import exemple.backend.auth.entity.UserEntity;
import exemple.backend.auth.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntityByEmail(String email);
    List<UserEntity> findUserEntityByRoleEntity(RoleEntity roleEntity);
    boolean existsByEmail(String email);
}
