package renko.jiang.campus_life_guide.pojo.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer id;
    private Integer postId;
    private Integer commenterId;
    private String content;
}
