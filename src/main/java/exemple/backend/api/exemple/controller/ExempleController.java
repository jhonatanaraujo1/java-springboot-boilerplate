package exemple.backend.api.exemple.controller;

import exemple.backend.api.exemple.service.ExempleService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
@Validated
public class ExempleController {

    private ExempleService service;


}
