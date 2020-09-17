package me.yunb.miristudy.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.yunb.miristudy.domain.User;
import me.yunb.miristudy.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Getter
    public static class LoginRequestParam {
        private String name;
        private String password;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> login(@RequestBody LoginRequestParam loginRequestParam, HttpSession httpSession) {
        User findUser = userService.findByName(loginRequestParam.getName());
        if(findUser == null || !findUser.matchPassword(loginRequestParam.getPassword())) {
            return ResponseEntity.notFound().build();
        }

        // session에 유저 정보 저장
        httpSession.setAttribute("userIdx", findUser.getIdx());

        return ResponseEntity.ok(findUser.getIdx());
    }
}
