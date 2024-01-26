package exemple.backend.auth.controller;

import exemple.backend.auth.dto.ConfirmEmailDto;
import exemple.backend.auth.dto.LoginUserDto;
import exemple.backend.auth.dto.ResponseWrapper;
import exemple.backend.auth.dto.UserDto;
import exemple.backend.exceptions.EmailAlreadyException;
import exemple.backend.exceptions.EmailNotVerifiedException;
import exemple.backend.exceptions.InvalidCodeException;
import exemple.backend.exceptions.InvalidCredentialsException;
import exemple.backend.auth.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/sign-in")
    public ResponseEntity<ResponseWrapper> signIn(@RequestBody LoginUserDto loginUserDto) throws AuthenticationException {
        try {
            return ResponseEntity.ok(new ResponseWrapper(authService.signIn(loginUserDto), "success", "User authentication successful"));
        } catch (EmailNotVerifiedException emailNotVerifiedException){
            return ResponseEntity.ok(new ResponseWrapper(null, "un-verified", emailNotVerifiedException.getMessage()));
        } catch (InvalidCredentialsException invalidCredentialsException) {
            return ResponseEntity.ok(new ResponseWrapper(null, "wrong", invalidCredentialsException.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseWrapper(null, "failed", "Something went wrong! Please contact developer"));
        }
    }

    @PostMapping(value = "/signup")
    public Object saveUser(@RequestBody UserDto user) {
        try {
            return new ResponseWrapper(authService.signUp(user), "success", "User successfully registered");
        } catch (EmailAlreadyException emailAlreadyException) {
            return new ResponseWrapper(null, "already", emailAlreadyException.getMessage());
        } catch (IOException ioException) {
            return new ResponseWrapper(null, "email-sending-failed", "Email sending failed");
        } catch (Exception exception) {
            return new ResponseWrapper(null, "failed", "Something went wrong! Please contact developer");
        }
    }

    @PostMapping(value = "/email/verify")
    public ResponseWrapper verifyEmail(@RequestBody ConfirmEmailDto confirm) throws IOException {
        try {
            return new ResponseWrapper(authService.verifyEmail(confirm), "success", "Email verification Success");
        } catch (InvalidCredentialsException invalidCredentialsException) {
            return new ResponseWrapper(null, "wrong-email", invalidCredentialsException.getMessage());
        } catch (InvalidCodeException invalidCodeException) {
            return new ResponseWrapper(null, "invalid-code", invalidCodeException.getMessage());
        } catch (IOException ioException) {
            return new ResponseWrapper(null, "email-sending-failed", "Email sending failed");
        } catch (Exception e) {
            return new ResponseWrapper(null, "failed", "Something went wrong! Please contact developer");
        }
    }

    @PostMapping(value = "/password/forgot")
    public Object forgotPassword(@RequestParam("email") String email) {
        try{
            return new ResponseWrapper(authService.forgotPassword(email), "success", "Forgot password request success");
        } catch(IOException ioException){
            return new ResponseWrapper(null, "email-sending-failed", "Email sending failed");
        } catch (InvalidCredentialsException invalidCredentialsException) {
            return new ResponseWrapper(null, "wrong-email", invalidCredentialsException.getMessage());
        } catch(Exception e){
            return new ResponseWrapper(null, "failed", "Something went wrong! Please contact developer");
        }
    }

    @PostMapping(value = "/password/verify-code")
    public Object verifyResetCode(@RequestParam("email") String email, @RequestParam("resetCode") String resetCode) {
        try{
            return new ResponseWrapper(authService.checkPasswordVerifyCode(email, resetCode), "success", "Forgot password request success");
        } catch (InvalidCodeException invalidCodeException) {
            return new ResponseWrapper(null, "invalid-code", invalidCodeException.getMessage());
        } catch (Exception e) {
            return new ResponseWrapper(null, "failed", "Something went wrong! Please contact developer");
        }
    }

    @PostMapping(value = "/password/reset")
    public Object verifyResetCode(@RequestParam("email") String email, @RequestParam("resetCode") String resetCode, @RequestParam("newPassword") String newPassword) {
        try{
            return new ResponseWrapper(authService.resetPassword(email, resetCode, newPassword), "success", "Password resettled successfully");
        }catch (IOException ioException) {
            return new ResponseWrapper(null, "email-sending-failed", "Email sending failed");
        } catch (InvalidCodeException invalidCodeException) {
            return new ResponseWrapper(null, "invalid-code", invalidCodeException.getMessage());
        } catch (InvalidCredentialsException invalidCredentialsException) {
            return new ResponseWrapper(null, "wrong-email", invalidCredentialsException.getMessage());
        } catch (Exception exception){
            return new ResponseWrapper(null, "failed", "Something went wrong! Please contact developer");
        }

    }
}
