package renko.jiang.campus_trade.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import renko.jiang.campus_trade.pojo.dto.PostDTO;
import renko.jiang.campus_trade.pojo.dto.PostSearchDTO;
import renko.jiang.campus_trade.pojo.result.Result;
import renko.jiang.campus_trade.pojo.vo.PostVO;
import renko.jiang.campus_trade.service.PostService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@CrossOrigin("*")
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;


    /**
     * 添加帖子
     * @param postDTO
     * @return
     */
    @PostMapping("/addPost")
    public Result addPost(PostDTO postDTO) {
        postService.addPost(postDTO);
        return Result.success();
    }

    /**
     * 获取所有帖子
     * @return
     */
    @GetMapping("/all")
    public Result<List<PostVO>> getPosts(Integer userId) {
        System.out.println("userId = " + userId);
        List<PostVO> list = postService.getAllPosts(userId);
        return Result.success(list);
    }


    /**
     * 用户收藏列表
     */
    @GetMapping("/current/collections")
    public Result<List<PostVO>> getCollections(Integer userId) {
        return postService.getCollections(userId);
    }



    @PostMapping("/{postId}/like/{userId}")
    Result likePost(@PathVariable Integer postId, @PathVariable Integer userId) {
        return postService.likePost(postId, userId);
    }


    /**
     * 根据id获取帖子
     * @param id
     * @return
     */
    @GetMapping
    public Result<PostVO> getPostById(Integer id) {
        PostVO postVO = postService.getPostById(id);
        return Result.success(postVO);
    }

    /**
     * 工具搜索框搜索帖子
     * @param postSearchDTO
     */
    @GetMapping("/search")
    public Result<List<PostVO>> searchPost(PostSearchDTO postSearchDTO) {
        List<PostVO> list = postService.searchPost(postSearchDTO);
        return Result.success(list);
    }

    /**
     * 收藏/取消收藏帖子
     *
     */
    @PostMapping("/{postId}/collect")
    public Result collectPost(@PathVariable Integer postId) {
        return postService.collectPost(postId);
    }
}
