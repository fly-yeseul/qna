package dev.fly_yeseul.qna.controller;

import dev.fly_yeseul.qna.entities.SessionEntity;
import dev.fly_yeseul.qna.entities.member.UserEntity;
import dev.fly_yeseul.qna.entities.post.PostEntity;
import dev.fly_yeseul.qna.enums.member.user.EditResult;
import dev.fly_yeseul.qna.enums.member.user.LoginResult;
import dev.fly_yeseul.qna.services.PostService;
import dev.fly_yeseul.qna.services.UserService;
import dev.fly_yeseul.qna.vos.member.EditVo;
import dev.fly_yeseul.qna.vos.member.LoginVo;
import dev.fly_yeseul.qna.vos.member.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller(value = "dev.fly_yeseul.qna.controller.UserController")
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLogin (
            ModelAndView modelAndView
    ) {
        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView postLogin (
            ModelAndView modelAndView,
//            @RequestParam(value = "email") String email,
//            @RequestParam(value = "password") String password,
            HttpServletResponse response,
            LoginVo loginVo,
            HttpServletRequest request
    ) {
        loginVo.setResult(null);
        this.userService.login(loginVo, request);
        if (loginVo.getResult() == LoginResult.SUCCESS) {
            Cookie sessionKeyCookie = new Cookie("sk", loginVo.getSessionEntity().getKey());
            sessionKeyCookie.setPath("/");
            response.addCookie(sessionKeyCookie);
        }
        modelAndView.addObject("loginVo", loginVo);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @RequestMapping(value = "reg", method = RequestMethod.GET)
    public ModelAndView getRegister(
            ModelAndView modelAndView
    ) {
        modelAndView.setViewName("user/reg");
        return modelAndView;
    }

    @RequestMapping(value = "reg", method = RequestMethod.POST)
    public ModelAndView postRegister(
            ModelAndView modelAndView,
            RegisterVo registerVo,
            @RequestParam(value = "photo") MultipartFile photo
    ) throws IOException {
        registerVo.setResult(null);

        registerVo.setProfile(photo.getBytes());
        this.userService.register(registerVo);
        modelAndView.addObject("registerVo", registerVo);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView getLogout(
            ModelAndView modelAndView,
            HttpServletRequest request
    ) {
        if(request.getAttribute("sessionEntity") instanceof SessionEntity) {
            SessionEntity sessionEntity = (SessionEntity) request.getAttribute("sessionEntity");
            this.userService.expireSession(sessionEntity);
        }
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @RequestMapping(value = "my", method = RequestMethod.GET)
    public ModelAndView getMy(
            ModelAndView modelAndView,
            @RequestAttribute (value = "userEntity", required = false) UserEntity userEntity,
            @RequestAttribute (value = "postEntity", required = false) PostEntity postEntity
            ) {
        PostEntity[] postEntities = this.postService.getPosts();
        modelAndView.addObject("postEntities", postEntities);
        modelAndView.addObject("userEntity", userEntity);
        modelAndView.addObject("postCount", postService.countPosts(userEntity));
        modelAndView.setViewName("user/my");
        return modelAndView;
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView getEdit(
            ModelAndView modelAndView,
            @RequestAttribute (value = "userEntity", required = false) UserEntity userEntity,
            @RequestParam (value = "pid", required = false) String email
    ) {
//        modelAndView.addObject("userEntity", userEntity);
        EditVo editVo = new EditVo();
        editVo.setEmail(email);
        this.userService.getUser(userEntity.getEmail());
        modelAndView.addObject("editVo", editVo);
        modelAndView.setViewName("user/edit");
        return modelAndView;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ModelAndView postEdit(
            HttpServletResponse response,
            ModelAndView modelAndView,
            @RequestAttribute (value = "userEntity", required = false) UserEntity userEntity,
            @RequestParam(value = "pid", required = false) String email,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "nickname", required = true) String nickname,
            @RequestParam(value = "intro", required = false) String intro,
            EditVo editVo
            ) throws IOException {
        userEntity.setEmail(email);
        this.userService.modifyProfilePhoto(editVo);
        modelAndView.addObject("editVo", editVo);
        modelAndView.setViewName("redirect:/user/edit");
        return modelAndView;
    }

    @RequestMapping(value = "edit-profile", method = RequestMethod.GET)
    public ModelAndView getEditProfile(
           ModelAndView modelAndView
    ) {
        modelAndView.setViewName("user/edit-profile");
        return modelAndView;
    }

    @RequestMapping(value = "edit-profile", method = RequestMethod.POST)
    public ModelAndView postEditProfile(
            @RequestAttribute(value = "userEntity", required = true) UserEntity userEntity,
            @RequestParam(value = "profile", required = false) MultipartFile profile,
            ModelAndView modelAndView,
            EditVo editVo
    ) throws IOException {
        if(userEntity == null) {
            modelAndView.setViewName("user/login");
            return modelAndView;
        }
        editVo.setResult(null);
        editVo.setProfile(profile.getBytes());
        this.userService.modifyProfilePhoto(editVo);

        if(editVo.getResult() == EditResult.SUCCESS) {
            modelAndView.setViewName("redirect:/user/edit");
        } else {
            modelAndView.setViewName("user/edit-profile");
        }
        modelAndView.setViewName("user/edit-profile");
        return modelAndView;
    }






    @RequestMapping(value = "profile", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> getProfile(
            @RequestParam(value = "uid", required = true) String nickname
            ) {
        UserEntity[] profiles = this.userService.getProfiles();
        byte[] data = null;
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        if(Arrays.stream(profiles).noneMatch(x -> x.getNickname() == nickname)) {
            status = HttpStatus.NOT_FOUND;
        } else {
            UserEntity profile = Arrays.stream(profiles).filter(x -> x.getNickname() == nickname).collect(Collectors.toList()).get(0);
            data = profile.getProfile();
            headers.add("Content-Type", "image/png");
            headers.add("Content-Length", String.valueOf(data.length));
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(data, headers, status);
    }




}
