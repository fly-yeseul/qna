package dev.fly_yeseul.qna.mappers;

import dev.fly_yeseul.qna.dtos.PostDto;
import dev.fly_yeseul.qna.entities.post.PostEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface IPostMapper {

    int insertPost(PostEntity postEntity);

    int selectPostCountByEmail(
            @Param(value = "userEmail") String userEmail
    );

    int selectPostCountByNickname(
            @Param(value = "userNickname") String userNickname
    );

    PostEntity selectPostByIndex(
            @Param(value = "index") int index
    );
//    PostEntity selectPosterByIndex(
//            @Param(value = "index") int index
//    );

    PostDto[] selectPosts(

    );

    PostDto[] selectPostsByNickname(
            @Param(value = "userNickname") String userNickname
    );

    // 이미지 불러오는 interface
//    Map<String, Object> selectPhoto(int index);

}
