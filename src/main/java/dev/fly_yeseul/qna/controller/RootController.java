package dev.fly_yeseul.qna.controller;

import dev.fly_yeseul.qna.dtos.CommentDto;
import dev.fly_yeseul.qna.entities.member.UserEntity;
import dev.fly_yeseul.qna.entities.post.CommentEntity;
import dev.fly_yeseul.qna.entities.post.PostEntity;
import dev.fly_yeseul.qna.enums.post.CommentResult;
import dev.fly_yeseul.qna.services.CommentService;
import dev.fly_yeseul.qna.services.PostService;
import dev.fly_yeseul.qna.services.UserService;
import dev.fly_yeseul.qna.vos.post.CommentVo;
import org.apache.ibatis.ognl.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Controller(value = "dev.fly_yeseul.qna.controller.RootController")
@RequestMapping("/")
public class RootController {
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public RootController(PostService postService, UserService userService, CommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView postComment(
            @RequestAttribute(value = "userEntity", required = false) UserEntity userEntity,
            @RequestAttribute(value = "postEntity", required = false) PostEntity postEntity,
            @RequestAttribute(value = "commentEntity", required = false) CommentEntity commentEntity,
            @RequestParam(value = "postIndex", required = true) int postIndex,
            @RequestParam(value = "comment", required = true) String comment,
            HttpServletRequest request,
            ModelAndView modelAndView,
            CommentVo commentVo
    ) throws IOException {
        if (userEntity == null) {
            modelAndView.setViewName("user/login");
            return modelAndView;
        }
        commentVo.setResult(null);
        commentVo.setPostIndex(postIndex);
//        System.out.println("포스트인덱스 불러옴");
        commentVo.setUserEmail(userEntity.getEmail());
        commentVo.setUserNickname(userEntity.getNickname());
        commentVo.setCommentAt(new Date());
        commentVo.setCommentLikes(0);
        commentVo.setComment(comment);
        this.commentService.postComment(commentVo);
//        System.out.println("입력됨");

        if (commentVo.getResult() == CommentResult.SUCCESS) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
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


    //    TODO "root/userNickname"으로 주소 수정해야함 해야함해야함 service/mapping 다 고치기
    @RequestMapping(value = "{userNickname}", method = RequestMethod.GET)
    public ModelAndView getMy(
            ModelAndView modelAndView,
            @RequestAttribute(value = "userEntity", required = true) UserEntity userEntity,
            @RequestAttribute(value = "postEntity", required = false) PostEntity postEntity,
            @PathVariable(value = "userNickname") String userNickname
    ) {
        PostEntity[] postEntities = this.postService.getPosts();
        modelAndView.addObject("postEntities", postEntities);
        modelAndView.addObject("userEntity", userEntity);
        modelAndView.addObject("postCount", postService.countPosts(userEntity));
        modelAndView.setViewName("user/my");
        return modelAndView;
    }

}
