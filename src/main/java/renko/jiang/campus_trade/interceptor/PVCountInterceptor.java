package renko.jiang.campus_trade.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import renko.jiang.campus_trade.mapper.StatisticsMapper;

import java.time.LocalDate;

/**
 * @author 86132
 */

@Component
public class PVCountInterceptor implements HandlerInterceptor {
    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        // 获取请求的URL
        String url = request.getRequestURI();
        LocalDate date = LocalDate.now();
        Long count =statisticsMapper.getCount(date);
        if (count == null){
            statisticsMapper.addVisit(date);
        }else{
            statisticsMapper.update(date);
        }
        return true;
    }
}
