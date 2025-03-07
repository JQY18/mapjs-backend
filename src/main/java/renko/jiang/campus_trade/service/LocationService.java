package renko.jiang.campus_trade.service;


import org.springframework.web.multipart.MultipartFile;
import renko.jiang.campus_trade.controller.admin.pojo.dto.DetailCommentDTO;
import renko.jiang.campus_trade.controller.admin.pojo.dto.LocationDTO;
import renko.jiang.campus_trade.controller.admin.pojo.dto.LocationDetailDTO;
import renko.jiang.campus_trade.controller.admin.pojo.entity.DetailComment;
import renko.jiang.campus_trade.controller.admin.pojo.vo.LocationDetailVO;
import renko.jiang.campus_trade.controller.admin.pojo.vo.LocationVO;
import renko.jiang.campus_trade.pojo.result.PageResult;
import renko.jiang.campus_trade.pojo.result.Result;

import java.util.List;

/**
 * @author 86132
 */
public interface LocationService {
    Result<List<LocationVO>> getAllLocations(Integer locationId);

    Result<LocationDetailVO> getDetailById(String detailId);

    LocationVO getLocationById(Long id);

    LocationVO createLocation(LocationDTO locationDTO);

    Result updateLocation(LocationDTO locationDTO);

    Result deleteLocation(Long id);

    Result<List<DetailComment>> getCommentsById(String detailId);

    Result<String> submitComment(DetailCommentDTO detailCommentDTO);

    Result addLocation(LocationDTO locationDTO);

    Result updloadDetails(LocationDetailDTO locationDetailDTO);

    Result addImages(String detailId, List<MultipartFile> images);

    Result addVideos(String detailId, List<MultipartFile> videos);

    Result deleteImage(String imageUrl);

    Result deleteVideo(String videoUrl);

    Result deleteLocationCommentById(Integer id);

    Result<PageResult<DetailComment>> queryCommentsByPage(DetailCommentDTO detailCommentDTO);
}