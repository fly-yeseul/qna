package dev.fly_yeseul.qna.services;

import dev.fly_yeseul.qna.dtos.CommentDto;
import dev.fly_yeseul.qna.dtos.PostDto;
import dev.fly_yeseul.qna.entities.post.CommentEntity;
import dev.fly_yeseul.qna.entities.post.PostEntity;
import dev.fly_yeseul.qna.enums.post.CommentResult;
import dev.fly_yeseul.qna.mappers.ICommentMapper;
import dev.fly_yeseul.qna.vos.post.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.io.IOException;

@Service(value = "dev.fly_yeseul.qna.services.CommentService")
public class CommentService {
    private final ICommentMapper commentMapper;

    @Autowired
    public CommentService(ICommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }


    public void postComment(CommentVo commentVo) throws IOException {
        if (this.commentMapper.insertComment(commentVo) == 0) {
            commentVo.setResult(CommentResult.FAILURE);
        } else {
            commentVo.setResult(CommentResult.SUCCESS);
        }
    }

    public CommentEntity[] getComments(int postIndex) {return this.commentMapper.selectComment(postIndex);}

}
