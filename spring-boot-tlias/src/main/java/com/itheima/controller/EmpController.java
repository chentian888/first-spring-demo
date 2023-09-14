package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result empList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String name, Short gender,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        PageBean emps = (PageBean) empService.list(page, pageSize, name, gender, begin, end);
        return Result.success(emps);
    }

    @GetMapping("/{id}")
    public Result empById(@PathVariable Integer id) {
        Emp emp = empService.get(id);
        return Result.success(emp);
    }

    @DeleteMapping("/{ids}")
    public Result deleteEmp(@PathVariable List<Integer> ids) {
        empService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result addEmp(@RequestBody Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empService.add(emp);
        return Result.success();
    }

    @PutMapping
    public Result updateEmp(@RequestBody Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empService.update(emp);
        return Result.success();
    }

}
