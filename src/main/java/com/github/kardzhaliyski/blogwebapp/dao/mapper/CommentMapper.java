package com.github.kardzhaliyski.blogwebapp.dao.mapper;

import com.github.kardzhaliyski.blogwebapp.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface CommentMapper {

    @Select("SELECT * FROM comments WHERE post_id = #{id}")
    Comment[] getAllCommentsForPost(int id);

    @Select("SELECT * FROM comments")
    Comment[] getAllComments();

    @Insert("INSERT INTO comments(post_id, name, email, body) VALUES (#{postId}, #{name}, #{email}, #{body})")
    void addComment(Comment comment);
}
