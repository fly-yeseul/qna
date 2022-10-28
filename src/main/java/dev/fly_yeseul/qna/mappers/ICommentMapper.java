package dev.fly_yeseul.qna.mappers;

import dev.fly_yeseul.qna.entities.post.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICommentMapper {
    int insertComment(CommentEntity commentEntity);

    CommentEntity[] selectCommentByIndex();
}
