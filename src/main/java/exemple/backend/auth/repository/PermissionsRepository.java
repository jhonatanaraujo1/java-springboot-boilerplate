package exemple.backend.auth.repository;

import exemple.backend.auth.entity.PermissionsEntity;
import org.springframework.data.repository.CrudRepository;

public interface PermissionsRepository extends CrudRepository<PermissionsEntity, Long> {
}
