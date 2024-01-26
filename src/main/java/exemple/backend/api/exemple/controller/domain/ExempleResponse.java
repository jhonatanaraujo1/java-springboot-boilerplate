package exemple.backend.api.exemple.controller.domain;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;


@Getter
@Setter
public class ExempleResponse {

  private Long id;

  @NotNull
  private String descExemplo;
}
