package com.springcloud.system.systemuser.service.impl;

import com.springcloud.common.entity.DatePageVO;
import com.springcloud.system.systemuser.dto.SystemUserDto;
import com.springcloud.system.systemuser.entity.SystemUser;
import com.springcloud.system.systemuser.repository.SystemUserRepository;
import com.springcloud.system.systemuser.service.SystemUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


/**
 * 系统用户（运营平台）Service 实现
 *
 * @author liaofuxing
 * @date 2020/03/13 20:01
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    /**
     * 根据id查询SystemUser
     */
    @Override
    public SystemUser findSystemUserById(Integer systemUserId) {
        return systemUserRepository.findById(systemUserId).get();
    }

    /**
     * 查询用户列表
     *
     * @param systemUser
     * @return
     */
    @Override
    public List<SystemUser> findSystemUserList(SystemUser systemUser) {
        return systemUserRepository.findSystemUserList(systemUser);
    }

    /**
     * 运营平台用户分页查询
     * @param systemUserDto
     * @return
     */
    public DatePageVO<SystemUser> findSystemUserPage(SystemUserDto systemUserDto) {
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

        Page<SystemUser> systemUserPage = systemUserRepository.findAll(specification, pageable);
        DatePageVO<SystemUser> datePageVO = new DatePageVO(systemUserPage.getTotalElements(), systemUserPage.getContent());
        return datePageVO;
    }


    /**
     * 新增用户
     *
     * @param systemUserDto
     * @return
     */
    @Override
    public SystemUser addSystemUser(SystemUserDto systemUserDto) {
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(systemUserDto, systemUser);
        return systemUserRepository.save(systemUser);
    }
}
