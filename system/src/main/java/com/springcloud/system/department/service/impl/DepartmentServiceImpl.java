package com.springcloud.system.department.service.impl;


import com.springcloud.common.entity.DatePageVO;
import com.springcloud.system.department.dao.DepartmentRepository;
import com.springcloud.system.department.dto.DepartmentDto;
import com.springcloud.system.department.entity.Department;
import com.springcloud.system.department.entity.SystemUserDepartment;
import com.springcloud.system.department.service.DepartmentService;
import com.springcloud.system.department.service.SystemUserDepartmentService;
import com.springcloud.system.department.vo.DepartmentVO;
import com.springcloud.system.role.vo.SelectFormatVO;
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
 * 部门 ServiceImpl
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/17 16:00
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private SystemUserDepartmentService systemUserDepartmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<SelectFormatVO> findDepartmentAll() {
        List<SelectFormatVO> selectFormatVOList = new ArrayList<>();
        List<Department> departmentList = departmentRepository.findAll();
        for (Department department : departmentList) {
            SelectFormatVO selectFormatVO = new SelectFormatVO(department.getId(), department.getDepartmentName());
            selectFormatVOList.add(selectFormatVO);
        }
        return selectFormatVOList;
    }

    /**
     * 根据userId 查询角色
     *
     * @param userId
     * @return
     */
    @Override
    public Department findDepartmentByUserId(Integer userId) {
        SystemUserDepartment systemUserDepartmentBySystemUserId = systemUserDepartmentService.findSystemUserDepartmentBySystemUserId(userId);
        Optional<Department> byId = departmentRepository.findById(systemUserDepartmentBySystemUserId.getDepartmentId());
        return byId.get();
    }

    /**
     * 分页查询
     */
    /**
     * 角色分页查询
     *
     * @param departmentDto dto
     *
     * @return DatePageVO<DepartmentVO>
     */
    @Override
        public DatePageVO<DepartmentVO> findDepartmentPage(DepartmentDto departmentDto) {
        Pageable pageable = PageRequest.of(departmentDto.getPage() - 1, departmentDto.getPageSize(), Sort.Direction.ASC, "id");

        Specification<Department> specification = (Specification<Department>) (root, criteriaQuery, criteriaBuilder) -> {
            //分页条件组装
            List<Predicate> list = new ArrayList<>();
            if (!StringUtils.isEmpty(departmentDto.getDepartmentName())) {
                list.add(criteriaBuilder.like(root.get("departmentName").as(String.class), "%" + departmentDto.getDepartmentName()+ "%"));
            }

            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        Page<Department> departmentPage = departmentRepository.findAll(specification, pageable);
        return new DatePageVO(departmentPage.getTotalElements(), departmentPage.getContent());
    }

    @Transactional
    @Override
    public void addDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentDto, department);
        departmentRepository.save(department);
    }

    @Override
    public void editDepartment(DepartmentDto departmentDto) {
        Optional<Department> byId = departmentRepository.findById(departmentDto.getId());
        Department departmentDB = byId.get();
        BeanUtils.copyProperties(departmentDto, departmentDB);
        departmentRepository.save(departmentDB);
    }

    @Override
    public Boolean validateDepartmentNameRepeat(String DepartmentName) {
        Department byDepartmentName = departmentRepository.findByDepartmentName(DepartmentName);
        return byDepartmentName != null;
    }
}
