package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;

import java.util.HashMap;
import java.util.List;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    HashMap<Object,Object> selectEmployeeByPage(EmployeePageQueryDTO employeePageQueryDTO);

    boolean addEmployee(EmployeeDTO employeeDTO);

    boolean editEmployee(EmployeeDTO employeeDTO);

    Employee selectEmployeeById(Integer id);

}
