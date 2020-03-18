package com.springcloud.system.router.service;

import com.springcloud.system.router.dao.RouterDao;
import com.springcloud.system.router.entity.Router;
import com.springcloud.system.router.entity.Router2TreeVO;
import com.springcloud.system.router.entity.RouterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2019/08/09 15:21
 **/
@Service
public class RouterService {

    @Autowired
    private RouterDao routerDao;

    /**
     * 获取所有路由,不按菜单结构排序(测试使用)
     *
     * @return
     */
    public List<Router> getRouterAll() {
        return routerDao.findAll();
    }

    /**
     * 根据Parent获取路由
     *
     * @return
     */
    public List<RouterVo> getRouterByParent(Integer parent) {
        List<Router> byParent = routerDao.findByParent(parent);
        List<RouterVo> RouterEntityVoList = new ArrayList<>();
        for (Router router : byParent) {
            RouterVo routerVo = new RouterVo();
            BeanUtils.copyProperties(router, routerVo);
            RouterEntityVoList.add(routerVo);
        }
        return RouterEntityVoList;
    }

    /**
     * 递归组装router,目前可以无限递归,但前端最多能渲染2级目录（vue前端有bug）
     *
     * @param routerList
     * @return
     */
    public List<RouterVo> formatRouter(List<RouterVo> routerList) {
        for (RouterVo routerEntityVo : routerList) {
            List<RouterVo> routerByParent = getRouterByParent(routerEntityVo.getId());
            if (routerByParent != null && routerByParent.size() > 0) {
                routerEntityVo.setChildren(routerByParent);
                formatRouter(routerByParent);
            }
        }
        return routerList;
    }

    /**
     * 根据Parent获取路由
     *
     * @return
     */
    public List<Router2TreeVO> getRouter2TreeByParent(Integer parent) {
        List<Router> byParent = routerDao.findByParent(parent);
        List<Router2TreeVO> router2TreeVOList = new ArrayList<>();
        for (Router router : byParent) {
            Router2TreeVO router2TreeVO = new Router2TreeVO(router.getId(), router.getTitle());
            router2TreeVOList.add(router2TreeVO);
        }
        return router2TreeVOList;
    }

    /**
     * 递归组装router,目前可以无限递归,但前端最多能渲染2级目录（vue前端有bug）
     *
     * @param router2TreeVOList
     * @return
     */
    public List<Router2TreeVO> formatRouter2Tree(List<Router2TreeVO> router2TreeVOList) {
        for (Router2TreeVO router2TreeVO : router2TreeVOList) {
            List<Router2TreeVO> router2TreeByParent = getRouter2TreeByParent(router2TreeVO.getId());
            if (router2TreeByParent != null && router2TreeByParent.size() > 0) {
                router2TreeVO.setChildren(router2TreeByParent);
                formatRouter2Tree(router2TreeByParent);
            }
        }
        return router2TreeVOList;
    }

    public List<RouterVo> getRouters() {
        // 先获取一级路由
        Integer parent = 0;
        List<RouterVo> routerByParent = getRouterByParent(parent);
        List<RouterVo> routerList = formatRouter(routerByParent);
        return routerList;
    }

    public List<Router2TreeVO> getRouters2Tree() {
        // 先获取一级路由
        Integer parent = 0;
        List<Router2TreeVO> routerByParent = getRouter2TreeByParent(parent);
        List<Router2TreeVO> router2TreeVOList = formatRouter2Tree(routerByParent);
        return router2TreeVOList;
    }

}
