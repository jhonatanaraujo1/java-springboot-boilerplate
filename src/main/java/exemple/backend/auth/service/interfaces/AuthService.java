package exemple.backend.auth.service.interfaces;


import exemple.backend.auth.dto.ConfirmEmailDto;
import exemple.backend.auth.dto.LoginUserDto;
import exemple.backend.auth.dto.UserDto;
import exemple.backend.auth.entity.UserEntity;

import java.io.IOException;
import java.util.List;

public interface AuthService {
    List<Object> signIn(LoginUserDto loginUserDto);
    UserEntity signUp(UserDto userDto) throws IOException;
    Object verifyEmail(ConfirmEmailDto confirmEmailDto) throws IOException;
    Object forgotPassword(String email) throws IOException;
    Object checkPasswordVerifyCode(String email, String code);
    Object resetPassword(String email, String code, String newPassword) throws IOException;
}
