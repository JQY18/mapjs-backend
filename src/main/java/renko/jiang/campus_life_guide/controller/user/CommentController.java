package renko.jiang.campus_life_guide.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import renko.jiang.campus_life_guide.pojo.dto.CommentDTO;
import renko.jiang.campus_life_guide.pojo.result.Result;
import renko.jiang.campus_life_guide.pojo.vo.CommentVO;
import renko.jiang.campus_life_guide.service.CommentService;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 获取登录状态
     */

    /**
     * 根据帖子id获取评论
     *
     * @param postId
     * @param userId
     * @return
     */

    //userId为当前用户，待改进
    @GetMapping("/{postId}")
    public Result<List<CommentVO>> getComments(@PathVariable Integer postId, Integer userId) {
        List<CommentVO> comments = commentService.getCommentsByPostId(postId, userId);
        return Result.success(comments);
    }

    /**
     * 添加评论
     *
     * @param commentDTO
     * @return
     */
    @PostMapping("/add")
    public Result addComment(@RequestBody CommentDTO commentDTO) {
        commentService.addComment(commentDTO);
        return Result.success();
    }


    /**
     * 点赞评论
     *
     * @param requestBody
     * @return
     */

    //likerId为当前用户，待改进
    @PostMapping("/like")
    public Result likeComment(@RequestBody Map<String, Integer> requestBody) {
        Integer commentId = requestBody.get("commentId");
        Integer likerId = requestBody.get("likerId");
        Integer to = requestBody.get("to");
        boolean isLiked = requestBody.get("isLiked") == 1;
        commentService.likeComment(commentId, likerId, to, isLiked);
        return Result.success();
    }
}
