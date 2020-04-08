package com.springcloud.system.router.web;

import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.ResultVO;
import com.springcloud.system.router.dto.Menu2RouterDto;
import com.springcloud.system.router.vo.Router2TreeVO;
import com.springcloud.system.router.vo.RouterVO;
import com.springcloud.system.router.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/19 02:40
 *
 **/
@Controller
@RequestMapping("/router")
public class RouterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouterController.class);


    @Autowired
    private RouterService routerService;


    @GetMapping("/getRouters")
    @ResponseBody
    public ResultVO<List<RouterVO>> getRouters(HttpServletRequest request) {
        LOGGER.info("收到请求...");
        String token = request.getHeader("token");
        List<RouterVO> routerVos = routerService.getRouters(token);
        if (!routerVos.isEmpty()) {
            return ResultVOUtils.success(routerVos);
        } else {
            return ResultVOUtils.error(routerVos);
        }
    }

    @GetMapping("/getMenuTree")
    @ResponseBody
    public ResultVO<List<Router2TreeVO>> getRouterTree() {
        LOGGER.info("收到请求...");

        List<Router2TreeVO> routers2TreeVOs = routerService.getRouters2Tree();
        if (!routers2TreeVOs.isEmpty()) {
            return ResultVOUtils.success(routers2TreeVOs);
        } else {
            return ResultVOUtils.error(routers2TreeVOs);
        }
    }

    @PostMapping("/addMenuTree2Router")
    @ResponseBody
    public ResultVO<Integer> addMenuTree2Router(@RequestBody Menu2RouterDto menu2RouterDto) {
        LOGGER.info("收到请求...");
        Integer id = routerService.addRouter(menu2RouterDto);
        return ResultVOUtils.success(id);
    }

    // 校验路由名重复
    @GetMapping("/validateRouterTitleRepeat")
    @ResponseBody
    public ResultVO<Boolean> validateRouterTitleRepeat(String routerTitle, Integer id) {
        Boolean validate = routerService.validateRouterTitleRepeat(routerTitle, id);
        return ResultVOUtils.success(validate);
    }
}
