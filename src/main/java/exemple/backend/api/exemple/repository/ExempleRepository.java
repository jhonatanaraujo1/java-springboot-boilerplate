package exemple.backend.api.exemple.repository;

import exemple.backend.api.exemple.entity.ExempleEntity;
import org.springframework.data.repository.CrudRepository;

public interface ExempleRepository extends CrudRepository<ExempleEntity, Long> {
}
