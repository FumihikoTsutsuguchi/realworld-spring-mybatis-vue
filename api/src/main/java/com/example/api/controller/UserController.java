package com.example.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.api.model.User;
import com.example.api.security.JwtProvider;
import com.example.api.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final JwtProvider jwt;

    public UserController(UserService userService, JwtProvider jwt) {
        this.userService = userService;
        this.jwt = jwt;
    }



    /** ユーザ登録 */
    @PostMapping("/users")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest req) {
        var p = req.user();                             // ペイロード取り出し
        User user = userService.register(
                p.username(), p.email(), p.password());

        String token = jwt.issueToken(user);            // "token" フィールド用
        return ResponseEntity.ok(RegisterResponse.from(user, token));
    }

    /** ユーザ取得 */
    // @GetMapping("/{username}")
    // public ResponseEntity<UserResponse> find(@PathVariable String username) {
    //     User user = userService.findByUsername(username);
    //     return (user == null)
    //             ? ResponseEntity.notFound().build()
    //             : ResponseEntity.ok(UserResponse.from(user));
    // }

    /* ------------ DTO -------------- */

    // request -------------------------------------------------
    public record RegisterRequest(UserPayload user) {
        public record UserPayload(String email, String password, String username) {}
    }

    // response ------------------------------------------------
    public record RegisterResponse(UserBody user) {
        public static RegisterResponse from(User u, String token) {
            return new RegisterResponse(
                    new UserBody(u.email(), u.username(), /*bio*/"", /*image*/"", token)
            );
        }
        public record UserBody(
                String email,
                String username,
                String bio,
                String image,
                String token) {}
    }
}