package com.itheima.service;


import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageBean list(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    Emp get(Integer id);

    void add(Emp emp);

    void delete(List<Integer> ids);

    void deleteByDepId(Integer deptId);

    void update(Emp emp);

    Emp login(Emp emp);
}
