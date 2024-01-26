package exemple.backend.auth.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "permission")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public PermissionEntity() {
    }

    public PermissionEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //    public List<UserRole> getUserRoles() {
//        return userRoles;
//    }
//
//    public void setUserRoles(List<UserRole> userRoles) {
//        this.userRoles = userRoles;
//    }
}
