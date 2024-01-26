package exemple.backend.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "auth_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "avatar")
    private String avatar;
    @Getter
    @Column(name = "email_verify_code")
    private String emailVerifyCode;
    @Column(name = "password")
    @JsonIgnore
    private String password;
    @Column(name = "password_reset_code")
    @JsonIgnore
    private String passwordResetCode;
    @Column(name = "is_email_verified")
    private String isEmailVerified;
    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;
    @Column(name = "gender")
    private String gender;
    @Column(name = "country")
    private String country;
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "PERMISSIONS", joinColumns = {
            @JoinColumn(name = "USER_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "PERMISSION_ID")})
    @JsonIgnore
    private Set<PermissionEntity> permissionEntities;

    public UserEntity() {
    }

    public UserEntity(long id, String firstName, String lastName, String email, String avatar,
                      String emailVerifyCode, String password, String passwordResetCode,
                      String isEmailVerified, RoleEntity roleEntity, String gender,
                      Set<PermissionEntity> permissionEntities, String country, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatar = avatar;
        this.emailVerifyCode = emailVerifyCode;
        this.password = password;
        this.passwordResetCode = passwordResetCode;
        this.isEmailVerified = isEmailVerified;
        this.roleEntity = roleEntity;
        this.gender = gender;
        this.permissionEntities = permissionEntities;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public void setEmailVerifyCode(String emailVerifyCode) {
        this.emailVerifyCode = emailVerifyCode;
    }

    public RoleEntity getRole() {
        return roleEntity;
    }

    public void setRole(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public RoleEntity getUserRole() {
        return roleEntity;
    }

    public void setUserRole(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordResetCode() {
        return passwordResetCode;
    }

    public void setPasswordResetCode(String passwordResetCode) {
        this.passwordResetCode = passwordResetCode;
    }

    public String getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(String isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public Set<PermissionEntity> getPermissions() {
        return permissionEntities;
    }

    public void setPermissions(Set<PermissionEntity> permissionEntities) {
        this.permissionEntities = permissionEntities;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone_number() {
        return phoneNumber;
    }

    public void setPhone_number(String phone_number) {
        this.phoneNumber = phone_number;
    }
}
