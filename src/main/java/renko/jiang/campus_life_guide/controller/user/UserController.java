package renko.jiang.campus_life_guide.controller.user;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import renko.jiang.campus_life_guide.pojo.dto.LoginDTO;
import renko.jiang.campus_life_guide.pojo.dto.UserInfoDTO;
import renko.jiang.campus_life_guide.pojo.entity.User;
import renko.jiang.campus_life_guide.pojo.result.PageResult;
import renko.jiang.campus_life_guide.pojo.result.Result;
import renko.jiang.campus_life_guide.pojo.vo.UserInfoVO;
import renko.jiang.campus_life_guide.properties.JwtProperties;
import renko.jiang.campus_life_guide.service.UserService;
import renko.jiang.campus_life_guide.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "用户模块")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param loginDTO
     * @return
     */

    @Operation(summary = "登录",description = "根据用户名和密码登录，成功后返回jwt令牌")
    @Parameter(name = "loginDTO", description = "用户名密码", required = true)
    @PostMapping(value = "/login")
    public Result login(@RequestBody LoginDTO loginDTO) {

        User user = userService.login(loginDTO);

        if (user == null) {
            return Result.error("账号或密码错误!");
        }


        //登录成功，生成token，并且返回给前端
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getSecretKey(),
                jwtProperties.getExpiration(),
                claims);

        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("token", token);
        mapResult.put("userId", user.getId());

        return Result.success(mapResult);
    }

    /**
     * 注册
     *
     * @param loginDTO
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody LoginDTO loginDTO) {
        User user = userService.login(loginDTO);
        if (user != null) {
            Result.error("用户已存在");
        }
        userService.register(loginDTO);
        return Result.success();
    }

    @GetMapping("/authentic")
    public Result<Integer> getAuthentic(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        //System.out.println("userId: " + userId);
        if (userId == null) {
            return Result.error("用户未登录");
        }
        return Result.success(userId);
    }


    /**
     * 获取用户信息
     *
     * @param
     * @return
     */

    @GetMapping("/info")
    public Result<UserInfoVO> getUserInfoById(Integer userId) {
        UserInfoVO userInfo = userService.getUserInfoById(userId);
        return Result.success(userInfo);
    }

    /**
     * 更新用户信息
     *
     * @param userInfoDTO
     * @return
     */
    @PostMapping("/update")
    public Result updateUserInfo(UserInfoDTO userInfoDTO) {
        userService.updateUserInfo(userInfoDTO);
        return Result.success();
    }

    /**
     * 修改用户头像
     *
     * @param id     用户id
     * @param avatar
     */

    @Operation(summary = "修改用户头像",description = "修改用户头像")
    @Parameters({
            @Parameter(name = "id", description = "用户id", required = true),
            @Parameter(name = "avatar", description = "用户头像", required = true)
    })
    @PostMapping("/avatar")
    public Result updateAvatar(Integer id, MultipartFile avatar) {
        userService.updateAvatar(id, avatar);
        return Result.success();
    }

    /**
     * 修改密码
     *
     * @param params
     */
    @PostMapping("/password")
    public Result updatePassword(@RequestBody Map<String, Object> params) {
        userService.updatePassword((Integer) params.get("id"), (String) params.get("currentPassword"), (String) params.get("newPassword"));
        return Result.success();
    }


    @GetMapping("/page")
    public Result<PageResult<UserInfoVO>> getPosts(UserInfoDTO userInfoDTO) {
        return userService.page(userInfoDTO);
    }


    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
