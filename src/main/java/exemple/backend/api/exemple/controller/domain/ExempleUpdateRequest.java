package exemple.backend.api.exemple.controller.domain;

import lombok.Data;
import org.jetbrains.annotations.NotNull;


@Data
public class ExempleUpdateRequest {

  private Long id;

  @NotNull
  private String descExample;
}
