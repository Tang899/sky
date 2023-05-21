package com.sky.mapper;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.mapper.provider.EmployeeProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @SelectProvider(type = EmployeeProvider.class,method = "queryEmployeeByPageProvider")
    List<Employee> queryEmployeeByPage(@Param("employeeDTO") EmployeePageQueryDTO employeePageQueryDTO);

    @SelectProvider(type = EmployeeProvider.class,method = "queryTotal")
    Integer queryEmployeeTotal(@Param("employeeDTO") EmployeePageQueryDTO employeePageQueryDTO);

    @SelectProvider(type = EmployeeProvider.class,method = "insert")
    @ResultType(int.class)
    Integer insert(@Param("employeeDTO") EmployeeDTO employeeDTO);

    @SelectProvider(type = EmployeeProvider.class,method = "update")
    void update(@Param("employeeDTO")EmployeeDTO employeeDTO);

    @SelectProvider(type = EmployeeProvider.class,method = "selectById")
    Employee selectById(Integer id);

}
