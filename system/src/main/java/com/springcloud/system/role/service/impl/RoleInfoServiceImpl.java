package com.springcloud.system.role.service.impl;


import com.springcloud.common.entity.DatePageVO;
import com.springcloud.system.role.dao.RoleInfoDao;
import com.springcloud.system.role.dto.RoleInfoDto;
import com.springcloud.system.role.etity.RoleInfo;
import com.springcloud.system.role.etity.SystemUserRole;
import com.springcloud.system.role.service.RoleInfoService;
import com.springcloud.system.role.service.SystemUserRoleService;
import com.springcloud.system.role.vo.RoleInfoVO;
import com.springcloud.system.role.vo.SelectFormatVO;
import com.springcloud.system.router.entity.MenuRole;
import com.springcloud.system.router.service.MenuRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 角色 Service
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 20:25
 **/
@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Autowired
    private SystemUserRoleService systemUserRoleService;

    @Autowired
    private RoleInfoDao roleInfoRepository;

    @Autowired
    private MenuRoleService menuRoleService;

    /**
     * 角色分页查询
     *
     * @param roleInfoDto
     * @return
     */
    @Override
    public DatePageVO<RoleInfoVO> findRoleInfoPage(RoleInfoDto roleInfoDto) {
        Pageable pageable = PageRequest.of(roleInfoDto.getPage() - 1, roleInfoDto.getPageSize(), Sort.Direction.ASC, "id");

        Specification<RoleInfo> specification = (Specification<RoleInfo>) (root, criteriaQuery, criteriaBuilder) -> {
            //分页条件组装
            List<Predicate> list = new ArrayList<>();
            if (!StringUtils.isEmpty(roleInfoDto.getRoleName())) {
                list.add(criteriaBuilder.like(root.get("roleName").as(String.class), "%" + roleInfoDto.getRoleName() + "%"));
            }

            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        };
        Page<RoleInfo> roleInfoPage = roleInfoRepository.findAll(specification, pageable);
        DatePageVO<RoleInfoVO> datePageVO = new DatePageVO(roleInfoPage.getTotalElements(), roleInfoPage.getContent());
        return datePageVO;
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<SelectFormatVO> findRoleAll() {
        List<SelectFormatVO> roleInfoVOList = new ArrayList();
        List<RoleInfo> roleInfoList = roleInfoRepository.findAll();
        for (RoleInfo roleInfo : roleInfoList) {
            SelectFormatVO roleInfoVO = new SelectFormatVO(roleInfo.getId(), roleInfo.getRoleName());
            roleInfoVOList.add(roleInfoVO);
        }
        return roleInfoVOList;
    }

    /**
     * 根据userId 查询角色
     *
     * @param userId
     * @return
     */
    @Override
    public RoleInfo findRoleInfoByUserId(Integer userId) {
        SystemUserRole systemUserRoleBySystemUserId = systemUserRoleService.findSystemUserRoleBySystemUserId(userId);
        Optional<RoleInfo> byId = roleInfoRepository.findById(systemUserRoleBySystemUserId.getRoleId());
        return byId.get();
    }

    /**
     * 新增角色
     *
     * @param roleInfoDto
     */
    @Transactional
    @Override
    public void addRole(RoleInfoDto roleInfoDto) {
        RoleInfo roleInfo = new RoleInfo();
        BeanUtils.copyProperties(roleInfoDto, roleInfo);
        roleInfoRepository.save(roleInfo);

        // 保存角色和菜单的关系
        MenuRole menuRole = new MenuRole(roleInfo.getId(), roleInfoDto.getTreeChecked());
        menuRoleService.addMenuRole(menuRole);
    }

    /**
     * 编辑角色
     *
     * @param roleInfoDto
     */
    @Transactional
    @Override
    public void editRole(RoleInfoDto roleInfoDto) {
        Optional<RoleInfo> byId = roleInfoRepository.findById(roleInfoDto.getId());
        RoleInfo roleInfoDB = byId.get();
        BeanUtils.copyProperties(roleInfoDto, roleInfoDB);
        roleInfoRepository.save(roleInfoDB);

        // 保存角色和菜单的关系
        MenuRole menuRole = menuRoleService.findMenuRole(roleInfoDto.getId());
        if(menuRole == null) {
            menuRole = new MenuRole();
            menuRole.setRoleId(roleInfoDto.getId());
        }
        menuRole.setMenu(roleInfoDto.getTreeChecked());
        menuRoleService.addMenuRole(menuRole);
    }




}
