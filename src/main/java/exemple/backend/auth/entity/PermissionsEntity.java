package exemple.backend.auth.entity;

import javax.persistence.*;

@Entity
@Table(name = "permissions")
public class PermissionsEntity {

    @Id
    @Column(name = "user_id")
    private Long user;

    @Column(name = "permission_id")
    private Long permission;


    public PermissionsEntity() {
    }

    public PermissionsEntity(Long user, Long permission) {
        this.user = user;
        this.permission = permission;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getPermission() {
        return permission;
    }

    public void setPermission(Long permission) {
        this.permission = permission;
    }
}
