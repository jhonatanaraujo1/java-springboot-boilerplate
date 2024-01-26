package exemple.backend.auth.service.impl;

import exemple.backend.auth.repository.UserRepository;
import exemple.backend.auth.entity.PermissionEntity;
import exemple.backend.auth.entity.UserEntity;
import exemple.backend.auth.service.interfaces.UserService;
import exemple.backend.auth.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service(value = "userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {
	
	private final UserRepository userRepository;
	private final RoleServiceImpl roleServiceImpl;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findUserEntityByEmail(username);
		if(userEntity == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), getAuthority(userEntity));
	}

	private Set<SimpleGrantedAuthority> getAuthority(UserEntity userEntity) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		userEntity.getPermissions().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
	}

	public List<UserEntity> findAll() {
		List<UserEntity> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}


	@Override
	public boolean isUserExists(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public void delete(long id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserEntity findOne(String email) {
		return userRepository.findUserEntityByEmail(email);
	}

	@Override
	public UserEntity findById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
    public UserEntity save(UserEntity userEntity) {
		userEntity = userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity setUserRole(long userRoleId, UserEntity userEntity){
		RoleEntity roleEntity = roleServiceImpl.getById(userRoleId);
		Set<PermissionEntity> roles = new HashSet<>(roleEntity.getPermissions());
		userEntity.setPermissions(roles);
		userEntity.setUserRole(roleEntity);
		userEntity = userRepository.save(userEntity);
		return userEntity;
	}
}
