package dev.fly_yeseul.qna.controller;

import dev.fly_yeseul.qna.entities.member.UserEntity;
import dev.fly_yeseul.qna.entities.post.PhotoEntity;
import dev.fly_yeseul.qna.entities.post.PostEntity;
import dev.fly_yeseul.qna.enums.post.PostResult;
import dev.fly_yeseul.qna.services.PostService;
import dev.fly_yeseul.qna.vos.post.PostVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Controller(value = "dev.fly_yeseul.qna.controller.PostController")
@RequestMapping(value = "/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "write", method = RequestMethod.GET)
    public ModelAndView getWrite(
            ModelAndView modelAndView,
            @RequestAttribute(value = "userEntity", required = false) UserEntity userEntity,
            @RequestAttribute(value = "postEntity", required = false) PostEntity postEntity
    ) {
        modelAndView.setViewName("post/write");

//        // 사진 가져오기
//        byte photoArray[] = null;
//        final String BASE_64_PREFIX = "data:image/png;base64";
//        try {
//            String base64Url = String.valueOf(photo);
//            if(base64Url.startsWith(BASE_64_PREFIX)) {
//                photoArray = Base64.getDecoder().decode(base64Url.substring(BASE_64_PREFIX.length()));
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> result = postService.getPhoto(postVo.getIndex());
//
//        byte arr[] = photo.getBytes();
//
//        if(arr.length > 0 && arr != null) {
//            String base64Encode = byteToBase64
//        }

        modelAndView.addObject("userEntity", userEntity);
        return modelAndView;
    }



    @RequestMapping(value = "write", method = RequestMethod.POST)
    public ModelAndView postWrite(
            @RequestAttribute(value = "userEntity", required = false) UserEntity userEntity,
            @RequestAttribute(value = "postEntity", required = false) PostEntity postEntity,
            @RequestParam(value = "content", required = true, defaultValue = "") String content,
            @RequestParam(value = "photo", required = true) MultipartFile photo,
            ModelAndView modelAndView,
            PostVo postVo
    ) throws IOException {
        if(userEntity == null){
            modelAndView.setViewName("user/login");
            return modelAndView;
        }
        postVo.setResult(null);
        postVo.setUserEmail(userEntity.getEmail());
        postVo.setUserNickname(userEntity.getNickname());
        postVo.setPostedAt(new Date());
        postVo.setContent(content);
        postVo.setPhotoData(photo.getBytes());
        this.postService.postAdd(postVo);


        if(postVo.getResult() == PostResult.SUCCESS) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("post/write");
        }
        return modelAndView;
    }

    @RequestMapping(value = "image", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> getImage(
            @RequestParam(value = "pid", required = true) int postIndex
    ) {
        PostEntity[] posts = this.postService.getPosts();
        byte[] data = null;
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        if (Arrays.stream(posts).noneMatch(x -> x.getIndex() == postIndex)) {
            status = HttpStatus.NOT_FOUND;
        } else {
            PostEntity post= Arrays.stream(posts).filter(x -> x.getIndex() == postIndex).collect(Collectors.toList()).get(0);
            data = post.getPhotoData();
            headers.add("Content-Type", "image/png");
            headers.add("Content-Length", String.valueOf(data.length));
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(data, headers, status);
    }
}
