package com.springcloud.system.router.web;

import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.ResultVO;
import com.springcloud.system.router.entity.Router;
import com.springcloud.system.router.entity.RouterVo;
import com.springcloud.system.router.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/router")

public class RouterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouterController.class);

    @Autowired
    private RouterService routerService;

    @RequestMapping("/getRouters")
    @ResponseBody
    public ResultVO<Router> getRouters( HttpServletRequest request) {
        LOGGER.info("收到请求...");
        String token = request.getHeader("token");
        //先获取所有一级路由
        List<RouterVo> routerByParent = routerService.getRouterByParent(0);
        List<RouterVo> routerEntityVos = routerService.formatRouter(routerByParent);
        if (! routerEntityVos.isEmpty()) {
            return ResultVOUtils.success(routerEntityVos);
        } else {
            return ResultVOUtils.error(routerEntityVos);
        }
    }
}
