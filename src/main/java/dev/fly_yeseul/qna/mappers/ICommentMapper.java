package dev.fly_yeseul.qna.mappers;

import dev.fly_yeseul.qna.dtos.CommentDto;
import dev.fly_yeseul.qna.entities.post.CommentEntity;
import dev.fly_yeseul.qna.entities.post.PostEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.xml.stream.events.Comment;

@Mapper
public interface ICommentMapper {
    int insertComment(CommentEntity commentEntity);

    CommentEntity[] selectComment(
            @Param(value = "postIndex") int postIndex
    );
}
