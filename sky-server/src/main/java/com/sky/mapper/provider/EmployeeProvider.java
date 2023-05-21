package com.sky.mapper.provider;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.BeanUtils;

import java.util.List;


public class EmployeeProvider {


    /**
     *根据用户id查询实体
     */
    public String selectById(Integer id) {
        return new SQL() {{
            SELECT("*");
            FROM("employee");
            WHERE("id  = #{id}");
        }}.toString();
    }


        /**
         * 修改员工记录
         */
    public String update(EmployeeDTO employeeDTO) {
        return new SQL() {{
            UPDATE("employee");
            SET("id_number = #{employeeDTO.idNumber}");
            SET("name = #{employeeDTO.name}");
            SET("phone = #{employeeDTO.phone}");
            SET("sex  = #{employeeDTO.sex}");
            SET("username  = #{employeeDTO.username}");
            WHERE("id = #{employeeDTO.id}");
        }}.toString();

    }


    /**
     * 查询记录数
     *
     * @param employeeDTO
     * @return
     */
    public String queryTotal(EmployeePageQueryDTO employeeDTO) {
        return new SQL() {{
            SELECT("count(id)");
            FROM("employee");
        }}.toString();
    }

    /**
     * 模糊查询一个员工
     *
     * @param employeeDTO
     * @return
     */
    public String queryEmployeeByPageProvider(EmployeePageQueryDTO employeeDTO) {

        return new SQL() {
            int index = 0;

            {
                SELECT("*");
                FROM("employee");
                if ("".equals(employeeDTO.getName()) && employeeDTO.getName() != null) {
                    WHERE("name like concat(%,${employeeDTO.name},%)");
                }
                index = (employeeDTO.getPage() - 1) * employeeDTO.getPageSize();
                LIMIT(index + "," + employeeDTO.getPageSize());
            }

        }.toString();
    }

    /**
     * 插入一个员工
     *
     * @param employeeDTO
     * @return
     */
    public String insert(EmployeeDTO employeeDTO) {
        return new SQL() {{
            INSERT_INTO("employee");
            VALUES("id_number,name,phone,sex,username",
                    "#{employeeDTO.idNumber},#{employeeDTO.name},#{employeeDTO.phone},#{employeeDTO.sex},#{employeeDTO.username}");

        }}.toString();
    }
}
