package com.springcloud.system.router.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springcloud.system.role.etity.SystemUserRole;
import com.springcloud.system.role.service.SystemUserRoleService;
import com.springcloud.system.router.dao.RouterDao;
import com.springcloud.system.router.entity.MenuRole;
import com.springcloud.system.router.entity.Router;
import com.springcloud.system.router.entity.Router2TreeVO;
import com.springcloud.system.router.entity.RouterVo;
import com.springcloud.system.systemuser.entity.SystemUser;
import com.springcloud.system.systemuser.service.SystemUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.springcluod.rediscore.utils.RedisUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/19 02:37
 *
 **/

@Service
public class RouterService {

    @Autowired
    private RouterDao routerDao;

    @Autowired
    private SystemUserRoleService systemUserRoleService;

    @Autowired
    private MenuRoleService menuRoleService;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 获取所有路由,不按菜单结构排序
     *
     * @return List<Router> 所有路由
     */
    public List<Router> getRouterAll() {
        return routerDao.findAll();
    }


    /**
     * 获取前端路由
     *
     * @param token 登录用户token
     *
     * @return List<RouterVo> 所有的一级路由
     *
     */
    public List<RouterVo> getRouters(String token) {
        // 先获取一级路由
        Integer parent = 0;
        List<RouterVo> routerByParent = getRouterByParent(parent);

        //获取登录用户的菜单权限
        List<Integer> showMenu = getMenuRoleByLoginUser(token);

        return formatRouter(routerByParent, showMenu);
    }

    /**
     *
     * 根据Parent获取路由
     * @param parent 父级路由id
     *
     * @return List<RouterVo>
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
     * 通过与传入的父级路由 组装子级
     *
     * 递归组装router,目前可以无限递归,但前端最多能渲染2级目录（vue前端有bug）
     *
     * 通过子级和showMenu如果有一个子级在showMenu中，则显示该父级目录
     * 如果该父级不存在子子级，则直接与showMenu对比，是否在showMenu中，在setHidden(0)
     *
     * @param routerList 传入的父级路由
     *
     * @return List<RouterVo> 递归
     */
    public List<RouterVo> formatRouter(List<RouterVo> routerList, List<Integer> showMenu) {
        for (RouterVo routerEntityVo : routerList) {
            List<RouterVo> routerByParent = getRouterByParent(routerEntityVo.getId());
            if (routerByParent != null && routerByParent.size() > 0) {
                for (RouterVo byParent: routerByParent) {
                    if (showMenu.contains(byParent.getId())) {
                        routerEntityVo.setHidden(0);
                    }
                }
                routerEntityVo.setChildren(routerByParent);
                formatRouter(routerByParent, showMenu);
            } else {
                if (showMenu.contains(routerEntityVo.getId())) {
                    routerEntityVo.setHidden(0);
                }
            }
        }
        return routerList;
    }




    /**
     * 获取菜单Tree
     *
     * @return List<Router2TreeVO> TreeVO
     *
     */
    public List<Router2TreeVO> getRouters2Tree() {
        // 先获取一级路由
        Integer parent = 0;
        List<Router2TreeVO> routerByParent = getRouter2TreeByParent(parent);
        return formatRouter2Tree(routerByParent);
    }



    /**
     * 根据Parent获取路由
     * @param parent 父级路由id
     * @return List<Router2TreeVO>
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
     * 通过与传入的父级Tree 组装子级
     * 递归组装router,目前可以无限递归,但前端最多能渲染2级目录（vue前端有bug）
     *
     * @param router2TreeVOList 传入的父级路由
     *
     * @return router2TreeVOList 递归
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

    /**
     *
     * 获取所有显示的路由 showMenu
     *
     * @param token 登录用户token
     *
     * @return showMenu
     */
    public List<Integer> getMenuRoleByLoginUser(String token) {
        RedisUtils redisUtils = new RedisUtils(stringRedisTemplate);
        String userInfoStr = redisUtils.get("USER_INFO:" + token);
        // String userInfoStr = stringRedisTemplate.opsForValue().get();
        JSONObject jsonObject = JSONObject.parseObject(userInfoStr);
        SystemUser systemUser = JSON.toJavaObject(jsonObject, SystemUser.class);

        SystemUser systemUserByUsername = systemUserService.findSystemUserByUsername(systemUser.getUsername());

        SystemUserRole systemUserRole = systemUserRoleService.findSystemUserRoleBySystemUserId(systemUserByUsername.getId());
        MenuRole menuRole = menuRoleService.findMenuRole(systemUserRole.getRoleId());
        String[] menuSplit = menuRole.getMenu().split(",");
        List<String> menuSplitList = Arrays.asList(menuSplit);

        return menuSplitList.stream().map(Integer::parseInt).distinct().collect(Collectors.toList());
    }
}
