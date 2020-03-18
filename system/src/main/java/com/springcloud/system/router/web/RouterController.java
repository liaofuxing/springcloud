package com.springcloud.system.router.web;

import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.ResultVO;
import com.springcloud.system.router.entity.Router;
import com.springcloud.system.router.entity.Router2TreeVO;
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

    @GetMapping("/getRouters")
    @ResponseBody
    public ResultVO getRouters(HttpServletRequest request) {
        LOGGER.info("收到请求...");

        List<RouterVo> routerVos= routerService.getRouters();
        if (!routerVos.isEmpty()) {
            return ResultVOUtils.success(routerVos);
        } else {
            return ResultVOUtils.error(routerVos);
        }
    }

    @GetMapping("/getMenuTree")
    @ResponseBody
    public ResultVO getRouterTree() {
        LOGGER.info("收到请求...");

        List<Router2TreeVO> routers2TreeVOs = routerService.getRouters2Tree();
        if (!routers2TreeVOs.isEmpty()) {
            return ResultVOUtils.success(routers2TreeVOs);
        } else {
            return ResultVOUtils.error(routers2TreeVOs);
        }
    }
}
