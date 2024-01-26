package exemple.backend.auth.controller;

import exemple.backend.auth.entity.UserEntity;
import exemple.backend.auth.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN_PRIVILEGES')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<UserEntity> listUser(){
        return userService.findAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserEntity getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }

}
