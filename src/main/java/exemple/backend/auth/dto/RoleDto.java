package exemple.backend.auth.dto;

import exemple.backend.auth.entity.PermissionEntity;

import java.util.List;

public class RoleDto {
    private String id;
    private String name;
    private String description;
    private List<PermissionEntity> permissionEntities;

    public RoleDto() {
    }

    public RoleDto(String id, String name, String description, List<PermissionEntity> permissionEntities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.permissionEntities = permissionEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
