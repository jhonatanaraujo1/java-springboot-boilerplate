package exemple.backend.auth.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<PermissionEntity> permissionEntities;

    public RoleEntity() {
    }

    public RoleEntity(String name, String description, List<PermissionEntity> permissionEntities) {
        this.name = name;
        this.description = description;
        this.permissionEntities = permissionEntities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PermissionEntity> getPermissions() {
        return permissionEntities;
    }

    public void setPermissions(List<PermissionEntity> permissionEntities) {
        this.permissionEntities = permissionEntities;
    }
}
