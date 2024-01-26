package exemple.backend.auth.service.impl;

import exemple.backend.auth.dto.AuthTokenDto;
import exemple.backend.auth.dto.ConfirmEmailDto;
import exemple.backend.auth.dto.LoginUserDto;
import exemple.backend.auth.dto.UserDto;
import exemple.backend.auth.entity.PermissionEntity;
import exemple.backend.auth.entity.UserEntity;
import exemple.backend.auth.repository.PermissionRepository;
import exemple.backend.auth.service.interfaces.AuthService;
import exemple.backend.auth.service.interfaces.UserService;
import exemple.backend.exceptions.EmailAlreadyException;
import exemple.backend.exceptions.EmailNotVerifiedException;
import exemple.backend.exceptions.InvalidCodeException;
import exemple.backend.exceptions.InvalidCredentialsException;
import exemple.backend.util.EmailService;
import exemple.backend.util.HtmlProcessService;
import exemple.backend.util.RandomValueUtil;
import exemple.backend.util.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider jwtTokenUtil;

    private final UserService userService;

    private final EmailService emailService;

    private final BCryptPasswordEncoder bcryptEncoder;

    private final HtmlProcessService htmlProcessService;

    private final PermissionRepository permissionRepository;

    private final RoleServiceImpl roleService;

    @Override
    public List<Object> signIn(LoginUserDto loginUserDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword()));
        } catch (Exception e) {
            throw new InvalidCredentialsException("Usuário ou senha incorretos.");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        UserEntity userEntity = userService.findOne(loginUserDto.getEmail());
        if (userEntity != null) {
            if (userEntity.getIsEmailVerified().equals("FALSE")) {
                throw new EmailNotVerifiedException("Seu email ainda nao foi verificado.");
            }
        }
        List<Object> results = new ArrayList<>();
        results.add(new AuthTokenDto(token));
        results.add(userEntity);

        return results;
    }

    @Override
    public UserEntity signUp(UserDto userDto) throws IOException {
        Set<PermissionEntity> userPermissionEntities = new HashSet<>();
        userPermissionEntities.add(permissionRepository.findById(2L).orElse(null));

        if (!userService.isUserExists(userDto.getEmail())) {
            UserEntity userEntity = new UserEntity();
            String resetCode = RandomValueUtil.getRandomNumberString(999999);
            userEntity.setFirstName(userDto.getFirstName());
            userEntity.setLastName(userDto.getLastName());
            userEntity.setPhone_number(userDto.getPhoneNumber());
            userEntity.setEmail(userDto.getEmail());
            userEntity.setEmailVerifyCode(resetCode);
            userEntity.setIsEmailVerified("FALSE");
            userEntity.setPassword(bcryptEncoder.encode(userDto.getPassword()));
            userEntity.setPermissions(userPermissionEntities);
            Context emailContext = new Context();
            emailContext.setVariable("user", userEntity);
            emailContext.setVariable("code", resetCode);
            emailService.sendHtmlEmailSmtp("Email Verification ", this.htmlProcessService.processHtml(emailContext, "email/email-verification"), userEntity.getEmail());
            userService.setUserRole(2, userEntity);
            userEntity = userService.save(userEntity);
            roleService.addPermissions(userEntity.getId(), 2L);

            return userEntity;
        } else {
            throw new EmailAlreadyException("Email já esta em uso.");
        }
    }

    @Override
    public Object verifyEmail(ConfirmEmailDto confirmEmailDto) throws IOException {
        UserEntity authUserEntity = userService.findOne(confirmEmailDto.getEmail());
        if (authUserEntity != null) {
            if (confirmEmailDto.getVerifyCode().equals(authUserEntity.getEmailVerifyCode())) {
                authUserEntity.setIsEmailVerified("TRUE");
                authUserEntity.setEmailVerifyCode(null);
                userService.save(authUserEntity);
                emailService.sendHtmlEmailSmtp("Email Verification", "Welcome html", authUserEntity.getEmail());
                return authUserEntity;
            } else {
                throw new InvalidCodeException("You entered the incorrect code.");
            }
        } else {
            throw new InvalidCredentialsException("Incorrect email address");
        }
    }

    @Override
    public Object forgotPassword(String email) throws IOException {
        if (userService.isUserExists(email)) {
            UserEntity authUserEntity = userService.findOne(email);
            String resetCode = RandomValueUtil.getRandomNumberString(999999);
            authUserEntity.setPasswordResetCode(resetCode);
            userService.save(authUserEntity);

            Context emailContext = new Context();
            emailContext.setVariable("user", authUserEntity);
            emailContext.setVariable("code", resetCode);

            emailService.sendHtmlEmailSmtp("Email Verification", this.htmlProcessService.processHtml(emailContext, "email/email-verification"), authUserEntity.getEmail());

            return authUserEntity;
        } else {
            throw new InvalidCredentialsException("User not found");
        }
    }

    @Override
    public Object checkPasswordVerifyCode(String email, String code) {
        if (userService.isUserExists(email)) {
            UserEntity authUserEntity = userService.findOne(email);
            authUserEntity = userService.save(authUserEntity);
            if (code.equals(authUserEntity.getPasswordResetCode())) {
                return authUserEntity;
            } else {
                throw new InvalidCodeException("Wrong reset code");
            }
        } else {
            throw new InvalidCredentialsException("User not found");
        }
    }

    @Override
    public Object resetPassword(String email, String code, String newPassword) throws IOException {
        if (userService.isUserExists(email)) {
            UserEntity authUserEntity = userService.findOne(email);
            if (code.equals(authUserEntity.getPasswordResetCode())) {
                authUserEntity.setPassword(bcryptEncoder.encode(newPassword));
                authUserEntity.setPasswordResetCode(null);
                authUserEntity = userService.save(authUserEntity);
                emailService.sendHtmlEmailSmtp("Password reset successfully", "Your password reset successfully!", email);
                return authUserEntity;
            } else {
                throw new InvalidCodeException("Wrong reset code");
            }
        } else {
            throw new InvalidCredentialsException("User not found");
        }
    }
}
