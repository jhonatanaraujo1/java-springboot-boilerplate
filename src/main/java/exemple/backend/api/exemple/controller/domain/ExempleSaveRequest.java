package exemple.backend.api.exemple.controller.domain;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class ExempleSaveRequest {

  @NotNull
  private String descExemple;
}
