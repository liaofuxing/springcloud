package com.springcloud.system.systemuser.service.impl;

import com.springcloud.common.entity.DatePageVO;
import com.springcloud.system.systemuser.dto.SystemUserDto;
import com.springcloud.system.systemuser.entity.SystemUser;
import com.springcloud.system.systemuser.repository.SystemUserRepository;
import com.springcloud.system.systemuser.service.SystemUserService;
import com.springcloud.system.systemuser.vo.SystemUserVO;
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
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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

        Page<SystemUser> systemUserPage = systemUserRepository.findAll(specification, pageable);
        List<SystemUser> systemUserList = systemUserPage.getContent();
        List<SystemUserVO> systemUserVOList = new ArrayList();
        BeanUtils.copyProperties(systemUserList, systemUserVOList);
        for (SystemUser systemUser: systemUserList) {
            SystemUserVO systemUserVO = new SystemUserVO();
            BeanUtils.copyProperties(systemUser, systemUserVO);
            if(systemUserVO.getGender().intValue() == 1){
                systemUserVO.setGenderLabel("男");
            }else{
                systemUserVO.setGenderLabel("女");
            }
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
    @Override
    public SystemUser addSystemUser(SystemUserDto systemUserDto) {
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(systemUserDto, systemUser);
        return systemUserRepository.save(systemUser);
    }

    /**
     * 编辑SystemUser
     * @param systemUser
     */
    @Override
    public void editSystemUser(SystemUser systemUser) {
        Optional<SystemUser> byId = systemUserRepository.findById(systemUser.getId());
        SystemUser systemUserDB = byId.get();
        BeanUtils.copyProperties(systemUser, systemUserDB);
        systemUserRepository.save(systemUserDB);
    }
}
