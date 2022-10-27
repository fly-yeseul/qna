package dev.fly_yeseul.qna.controller;

import dev.fly_yeseul.qna.entities.member.UserEntity;
import dev.fly_yeseul.qna.entities.post.PostEntity;
import dev.fly_yeseul.qna.services.PostService;
import dev.fly_yeseul.qna.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller(value = "dev.fly_yeseul.qna.controller.RootController")
@RequestMapping("/")
public class RootController {
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public RootController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex(
            ModelAndView modelAndView,
            @RequestAttribute(value = "postEntity", required = false) PostEntity postEntity,
            @RequestAttribute(value = "userEntity", required = false) UserEntity userEntity
    ) {
        if (userEntity == null) {
            modelAndView.setViewName("user/login");
        } else {
            PostEntity[] postEntities = this.postService.getPosts();
            UserEntity[] userEntities = this.userService.getProfiles();
            modelAndView.addObject("postEntities", postEntities);
            modelAndView.addObject("userEntities", userEntities);
            modelAndView.setViewName("root/index");
        }
        return modelAndView;
    }


}
