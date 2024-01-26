package exemple.backend.events;

import exemple.backend.auth.entity.PermissionEntity;
import exemple.backend.auth.entity.RoleEntity;
import exemple.backend.auth.entity.UserEntity;
import exemple.backend.auth.repository.PermissionRepository;
import exemple.backend.auth.repository.UserRepository;
import exemple.backend.auth.repository.UserRoleRepository;
import exemple.backend.auth.service.impl.RoleServiceImpl;

import exemple.backend.auth.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SeedEventListner {

    private final PermissionRepository permissionRepository;

    private final UserRepository userRepository;
    private final UserService userService;
    private final BCryptPasswordEncoder bcryptEncoder;
    private final UserRoleRepository userRoleRepository;
    private final RoleServiceImpl roleServiceImpl;
    private static final Logger logger = LoggerFactory.getLogger(SeedEventListner.class);
    @EventListener
    public void seed(ContextRefreshedEvent event){
        try{
            boolean roles =  permissionRepository.findById(1L).isPresent();
            if(!roles){
                seedRoles();
                seedUsers();
            }
            logger.info("Database already populated");  // Log information indicating that the database is already populated
        } catch (Exception ignored) {
            logger.error("An error occurred while populating the database.");  // Error log indicating that an error occurred while populating the database
        }

    }

    private void seedRoles(){

        try{
            //ADMIN PRIVILEGES

            List<PermissionEntity> adminPermissionEntities = List.of(
                    new PermissionEntity("ADMIN_PRIVILEGES", "Admin privileges"),
                    new PermissionEntity("USER_PRIVILEGES", "User privileges")
            );

            List<PermissionEntity> userRoles = new ArrayList<>();
            for(PermissionEntity item: adminPermissionEntities){
                userRoles.add(permissionRepository.save(item));
            }

            List<RoleEntity> userRoleEntity = new ArrayList<>();

            RoleEntity roleAdmin = new RoleEntity();
            roleAdmin.setDescription("Admin");
            roleAdmin.setName("ADMIN");
            roleAdmin.setPermissions(adminPermissionEntities);
            userRoleEntity.add(roleAdmin);

            //USER PRIVILEGES
            RoleEntity roleUser = new RoleEntity();
            roleUser.setDescription("User");
            roleUser.setName("USER");
            roleUser.setPermissions(List.of(userRoles.get(1)));
            userRoleEntity.add(roleUser);

            userRoleRepository.saveAll(userRoleEntity);

        }catch (Exception ignored){

        }
    }

    private void seedUsers(){
        UserEntity admin;

        try{
            admin = new UserEntity();
            RoleEntity userRoleEntity = userRoleRepository.findById((long) 1).get();
            admin.setFirstName("Default");
            admin.setLastName("Admin");
            admin.setEmail("admin@exemple.com");
            admin.setIsEmailVerified("TRUE");
            admin.setPassword(bcryptEncoder.encode("123456"));
            admin.setUserRole(userRoleEntity);
            admin = userRepository.save(admin);
            userService.setUserRole(1, admin);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
