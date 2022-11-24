package dev.fly_yeseul.qna.services;


import dev.fly_yeseul.qna.dtos.PostDto;
import dev.fly_yeseul.qna.entities.member.UserEntity;
import dev.fly_yeseul.qna.entities.post.PostEntity;
import dev.fly_yeseul.qna.enums.post.PostResult;
import dev.fly_yeseul.qna.mappers.IPostMapper;
import dev.fly_yeseul.qna.vos.post.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service(value = "dev.fly_yeseul.qna.services.PostService")
public class PostService {

    private final IPostMapper postMapper;

    @Autowired
    public PostService(IPostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public void postAdd(PostVo postVo) throws IOException {
        if(this.postMapper.insertPost(postVo) == 0) {
            postVo.setResult(PostResult.FAILURE);
        } else {
            postVo.setResult(PostResult.SUCCESS);
        }
    }


    public void getPoster(PostDto postDto) {
        PostEntity postEntity = this.postMapper.selectPostByIndex(postDto.getIndex());
        postDto.setIndex(postEntity.getIndex());
        postDto.setPostedAt(postEntity.getPostedAt());
        postDto.setContent(postEntity.getContent());
        postDto.setPhotoData(postEntity.getPhotoData());
        postDto.setUserEmail(postEntity.getUserEmail());
        postDto.setLikes(postEntity.getLikes());
        postDto.setUserNickname(postEntity.getUserNickname());

    }
    public PostDto[] getPosts() {return this.postMapper.selectPosts();}

    public PostDto[] getPostsByNickname(
            String userNickname
    ){
        return this.postMapper.selectPostsByNickname(userNickname);
    }

    public int countPosts(UserEntity userEntity){
        return this.postMapper.selectPostCountByEmail(userEntity.getEmail());
    }

    public int countPostForUnm(String userNickname){
        return this.postMapper.selectPostCountByNickname(userNickname);
    }
}

