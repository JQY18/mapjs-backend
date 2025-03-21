package renko.jiang.campus_life_guide.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import renko.jiang.campus_life_guide.pojo.dto.ReplyDTO;

@Mapper
public interface ReplyMapper {
    @Insert("insert into reply set father_id = #{fatherId},commenter_id = #{commenterId},replier_id = #{replierId},content = #{content}")
    void addReply(ReplyDTO replyDTO);
}
