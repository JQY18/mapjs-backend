package renko.jiang.campus_life_guide.controller.admin.pojo.entity;


import lombok.Data;

/**
 * @author 86132
 */
@Data
public class DetailComment {
    private Integer id;
    private String username;
    private String detailId;
    private String content;
    private String time;
}
