package com.springcloud.system.router.web;

import com.alibaba.fastjson.JSON;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/router")

public class RouterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouterController.class);

    @Autowired
    private RouterService routerService;

    @RequestMapping("/rest")
    @ResponseBody
    public String getEntity() {
        LOGGER.info("收到请求...");
        RouterVo router = new RouterVo(1, "Layout", "/form");
        router.setTitle("头头");
        router.setIcon("Form1");
        RouterVo cr = new RouterVo(2, "Form", "/index");
        RouterVo cr2 = new RouterVo(3, "cs", "/cs");
        cr.setTitle("Form1");
        cr.setIcon("Form");
        cr2.setTitle("Form2");
        cr2.setIcon("Form");
        List<RouterVo> arrayList = new ArrayList();
        arrayList.add(cr);
        arrayList.add(cr2);
        router.setChildren(arrayList);

        List<RouterVo> routerList = new ArrayList();
        routerList.add(router);
        return JSON.toJSONString(routerList);
    }


    @RequestMapping("/getRouters")
    @ResponseBody
    public ResultVO<Router> getRouters( HttpServletRequest request) {
        LOGGER.info("收到请求...");
        String token = request.getHeader("token");
        List<RouterVo> routerByParent = routerService.getRouterByParent(0);
        List<RouterVo> routerEntityVos = routerService.formatRouter(routerByParent);
        if (! routerEntityVos.isEmpty()) {
            return ResultVOUtils.success(routerEntityVos);
        } else {
            return ResultVOUtils.error(routerEntityVos);
        }
    }
}
