package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;


    @GetMapping("{id}")
    public Result selectEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.selectEmployeeById(id);
        return Result.success(employee);
    }


    @PutMapping("")
    public Result editEmployee(@RequestBody EmployeeDTO employeeDTO) {

        boolean flag = employeeService.editEmployee(employeeDTO);
        if (flag) {
            return Result.success(flag);
        }
        return Result.success(flag);
    }

    @PostMapping("")
    public Result addEmployee(@RequestBody EmployeeDTO employeeDTO) {

        boolean flag = employeeService.addEmployee(employeeDTO);
        if (flag) {
            return Result.success(flag);
        }
        return Result.success(flag);
    }

    /**
     * 条件分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result<HashMap<Object, Object>> categoryPageQueryD(EmployeePageQueryDTO employeePageQueryDTO) {
        HashMap<Object, Object> map = employeeService.selectEmployeeByPage(employeePageQueryDTO);
        return Result.success(map);
    }

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

}
