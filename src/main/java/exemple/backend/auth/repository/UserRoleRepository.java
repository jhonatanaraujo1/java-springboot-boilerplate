package exemple.backend.auth.repository;

import exemple.backend.auth.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<RoleEntity, Long> {
}
