package com.springcloud.system.systemuser.service.impl;

import com.springcloud.common.entity.DatePageVO;
import com.springcloud.system.department.entity.Department;
import com.springcloud.system.department.entity.SystemUserDepartment;
import com.springcloud.system.department.service.DepartmentService;
import com.springcloud.system.department.service.SystemUserDepartmentService;
import com.springcloud.system.role.etity.RoleInfo;
import com.springcloud.system.role.etity.SystemUserRole;
import com.springcloud.system.role.service.RoleInfoService;
import com.springcloud.system.role.service.SystemUserRoleService;
import com.springcloud.system.systemuser.dao.SystemUserDao;
import com.springcloud.system.systemuser.dto.SystemUserDto;
import com.springcloud.system.systemuser.entity.SystemUser;
import com.springcloud.system.systemuser.service.SystemUserService;
import com.springcloud.system.systemuser.vo.SystemUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * 系统用户（运营平台）Service 实现
 *
 * @author liaofuxing
 * @date 2020/03/13 20:01
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserDao systemUserDao;

    @Autowired
    private RoleInfoService roleInfoService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SystemUserDepartmentService systemUserDepartmentService;

    @Autowired
    private SystemUserRoleService systemUserRoleService;

    /**
     * 根据id查询SystemUser
     */
    //@Cacheable(value = "spring-cloud-system", key = "'user_'+#systemUserId")
    @Override
    public SystemUser findSystemUserById(Integer systemUserId) {
        System.out.println("测试缓存");
        return systemUserDao.findById(systemUserId).get();
    }

    /**
     * 根据name查询SystemUser
     */
    @Override
    public SystemUser findSystemUserByUsername(String username) {
        return systemUserDao.findByUsername(username);
    }

    /**
     * 查询用户列表
     *
     * @param systemUser
     * @return
     */
    @Override
    public List<SystemUser> findSystemUserList(SystemUser systemUser) {
        return systemUserDao.findSystemUserList(systemUser);
    }

    /**
     * 运营平台用户分页查询
     *
     * @param systemUserDto
     * @return
     */
    public DatePageVO<SystemUserVO> findSystemUserPage(SystemUserDto systemUserDto) {
        Pageable pageable = PageRequest.of(systemUserDto.getPage() - 1, systemUserDto.getPageSize(), Sort.Direction.ASC, "id");

        Specification<SystemUser> specification = (Specification<SystemUser>) (root, criteriaQuery, criteriaBuilder) -> {
            //分页条件组装
            List<Predicate> list = new ArrayList();
            if (!StringUtils.isEmpty(systemUserDto.getAccount())) {
                list.add(criteriaBuilder.like(root.get("account").as(String.class), "%" + systemUserDto.getAccount() + "%"));
            }

            if (!StringUtils.isEmpty(systemUserDto.getUsername())) {
                list.add(criteriaBuilder.like(root.get("username").as(String.class), systemUserDto.getUsername() + "%"));
            }

            if (!StringUtils.isEmpty(systemUserDto.getPhone())) {
                list.add(criteriaBuilder.equal(root.get("phone").as(String.class), systemUserDto.getPhone()));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        };

        Page<SystemUser> systemUserPage = systemUserDao.findAll(specification, pageable);
        List<SystemUser> systemUserList = systemUserPage.getContent();
        List<SystemUserVO> systemUserVOList = new ArrayList();
        BeanUtils.copyProperties(systemUserList, systemUserVOList);
        for (SystemUser systemUser : systemUserList) {
            SystemUserVO systemUserVO = new SystemUserVO();
            BeanUtils.copyProperties(systemUser, systemUserVO);

            // 查询用户角色
            RoleInfo roleInfoByUserId = roleInfoService.findRoleInfoByUserId(systemUserVO.getId());
            systemUserVO.setRoleId(roleInfoByUserId.getId());

            // 查询用户部门
            Department departmentByUserId = departmentService.findDepartmentByUserId(systemUserVO.getId());
            systemUserVO.setDepartmentId(departmentByUserId.getId());
            systemUserVOList.add(systemUserVO);
        }
        DatePageVO<SystemUserVO> datePageVO = new DatePageVO(systemUserPage.getTotalElements(), systemUserVOList);
        return datePageVO;
    }


    /**
     * 新增用户
     *
     * @param systemUserDto
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addSystemUser(SystemUserDto systemUserDto) {
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(systemUserDto, systemUser);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        systemUser.setPassword(bCryptPasswordEncoder.encode("123456"));
        systemUserDao.save(systemUser);


        // 保存角色关系
        SystemUserRole systemUserRole = new SystemUserRole();
        systemUserRole.setRoleId(systemUserDto.getRoleId());
        systemUserRole.setSystemUserId(systemUser.getId());
        systemUserRoleService.addSystemUserRole(systemUserRole);

        // 保存部门关系
        SystemUserDepartment systemUserDepartment = new SystemUserDepartment();
        systemUserDepartment.setDepartmentId(systemUserDto.getDepartmentId());
        systemUserDepartment.setSystemUserId(systemUser.getId());
        systemUserDepartmentService.addSystemUserDepartment(systemUserDepartment);

    }

    /**
     * 编辑SystemUser
     *
     * this @Transactional is Propagation.REQUIRED, 这是一个整体事务，出现异常会一起回滚
     *
     * @param systemUserDto
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void editSystemUser(SystemUserDto systemUserDto) {
        Optional<SystemUser> byId = systemUserDao.findById(systemUserDto.getId());
        SystemUser systemUserDB = byId.get();
        BeanUtils.copyProperties(systemUserDto, systemUserDB);
        systemUserDao.save(systemUserDB);

        // 保存用户和角色关系
        SystemUserRole systemUserRole = systemUserRoleService.findSystemUserRoleBySystemUserId(systemUserDto.getId());
        systemUserRole.setRoleId(systemUserDto.getRoleId());
        systemUserRoleService.addSystemUserRole(systemUserRole);

        // 保存用户和部门角色关系
        SystemUserDepartment systemUserDepartment = systemUserDepartmentService.findSystemUserDepartmentBySystemUserId(systemUserDto.getId());
        systemUserDepartment.setDepartmentId(systemUserDto.getDepartmentId());
        systemUserDepartmentService.addSystemUserDepartment(systemUserDepartment);
    }


    /**
     * 校验用户名不重复
     *
     * @param username username
     */
    public  Boolean validateUsernameRepeat(String username){
        SystemUser byUsername = systemUserDao.findByUsername(username);
        return byUsername != null;
    }

}
