package exemple.backend.auth.repository;

import exemple.backend.auth.entity.PermissionEntity;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<PermissionEntity, Long> {
    PermissionEntity findPermissionEntitiesByName(String name);
}
