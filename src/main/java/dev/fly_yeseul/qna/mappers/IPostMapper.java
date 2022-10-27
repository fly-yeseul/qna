package dev.fly_yeseul.qna.mappers;

import dev.fly_yeseul.qna.entities.post.PhotoEntity;
import dev.fly_yeseul.qna.entities.post.PostEntity;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface IPostMapper {

    int insertPost(PostEntity postEntity);

    int selectPostCountByEmail(
            @Param(value = "userEmail") String userEmail
    );

    PostEntity[] selectPost();

    // 이미지 불러오는 interface
//    Map<String, Object> selectPhoto(int index);

}
