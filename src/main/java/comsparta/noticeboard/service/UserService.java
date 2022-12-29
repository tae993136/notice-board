package comsparta.noticeboard.service;

import comsparta.noticeboard.dto.LogInRequestDto;
import comsparta.noticeboard.dto.SignupRequestDto;
import comsparta.noticeboard.entity.User;
import comsparta.noticeboard.entity.UserRoleEnum;
import comsparta.noticeboard.jwt.JwtUtil;
import comsparta.noticeboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public String logIn(LogInRequestDto logInRequestDto, HttpServletResponse response) {
        String userName = logInRequestDto.getUserName();
        String password = logInRequestDto.getPassword();

        // 1. 사용자 있는지 확인
        User user = userRepository.findByUsername(userName). orElseThrow(
                () -> new IllegalArgumentException("해당 이름으로 등록된 유저가 없습니다.")
        );

        // 2. 비밀번호 확인
        if(!user.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 헤더에 올리기
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));

        return "로그인 완료";

    }

    @Transactional
    public String signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        String email = signupRequestDto.getEmail();
        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, email, role);
        userRepository.save(user);

        return "회원가입 성공";
    }
}
