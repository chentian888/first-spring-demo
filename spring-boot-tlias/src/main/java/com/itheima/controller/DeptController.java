package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list() {
        List<Dept> depts = deptService.list();
        return Result.success(depts);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Dept dept = deptService.get(id);
        return Result.success(dept);
    }

    @PostMapping
    public Result add(@RequestBody Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptService.addDept(dept);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        deptService.deleteDept(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptService.updateDept(dept);
        return Result.success();
    }
}
