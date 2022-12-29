package comsparta.noticeboard.controller;

import comsparta.noticeboard.dto.LogInRequestDto;
import comsparta.noticeboard.dto.SignupRequestDto;
import comsparta.noticeboard.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller // Controller -> MVC에서 controller
@RequiredArgsConstructor
@RequestMapping("/auth")
@Getter
public class UserController {
    private final UserService userService;
    private String userName;
    private String password;


    @ResponseBody // View를 찾지 않고 일단 return해줌
    @PostMapping("/signup")
    public String signUp(@RequestBody @Validated SignupRequestDto signUpRequestDto){
        return userService.signup(signUpRequestDto);
    }

    @ResponseBody
    @PostMapping("/login")
    public String logIn(@RequestBody LogInRequestDto logInRequestDto, HttpServletResponse response){
        return userService.logIn(logInRequestDto, response);
    }
}
