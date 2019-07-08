package io.github.dunwu.zero.aaa.controller;

import io.github.dunwu.core.BaseResult;
import io.github.dunwu.core.DataResult;
import io.github.dunwu.core.DefaultAppCode;
import io.github.dunwu.core.ResultUtil;
import io.github.dunwu.web.util.CookieUtil;
import io.github.dunwu.zero.aaa.dto.UserInfoDTO;
import io.github.dunwu.zero.aaa.entity.UserDO;
import io.github.dunwu.zero.aaa.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public static final String TOKEN_KEY = "token";

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public DataResult<UserInfoDTO> login(HttpServletRequest request, HttpServletResponse response,
                                         @RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        UserDO userDO = userService.getByUsername(username);
        if (userDO == null) {
            return ResultUtil.failDataResult(DefaultAppCode.ERROR_AUTH);
        }

        if (userDO.getPassword()
                  .equals(password)) {

            String sessionId = request.getSession(true)
                                      .getId();
            request.getSession()
                   .setAttribute(TOKEN_KEY, sessionId);
            log.info("sessionId = {}", sessionId);
            Cookie[] cookies = request.getCookies();
            CookieUtil.addCookie(request, response, "SESSION", sessionId);

            UserInfoDTO userInfoDTO = new UserInfoDTO();
            userInfoDTO.setName("Super Admin");
            userInfoDTO.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            userInfoDTO.setIntroduction("I am a super administrator");
            userInfoDTO.getRoles()
                       .add("admin");
            userInfoDTO.setToken(sessionId);
            return ResultUtil.successDataResult(userInfoDTO);
        }
        return ResultUtil.failDataResult(DefaultAppCode.ERROR_AUTH);
    }

    @PostMapping("/logout")
    public BaseResult logout(HttpServletRequest request) {
        request.getSession()
               .removeAttribute(TOKEN_KEY);
        return ResultUtil.successBaseResult();
    }

    @GetMapping("/getInfo")
    public DataResult<UserInfoDTO> getInfo(HttpServletRequest request, @RequestParam String token) {
        if (StringUtils.isBlank(token)) {
            ResultUtil.failDataResult();
        }

        HttpSession session = request.getSession();
        log.info("token = {}", session.getAttribute("token"));

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setName("Super Admin");
        userInfoDTO.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfoDTO.setIntroduction("I am a super administrator");
        userInfoDTO.getRoles()
                   .add("admin");
        return ResultUtil.successDataResult(userInfoDTO);
    }
}
