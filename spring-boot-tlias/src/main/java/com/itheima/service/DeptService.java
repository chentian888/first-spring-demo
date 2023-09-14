package com.itheima.service;


import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> list();

    void addDept(Dept dept);

    void deleteDept(Integer id) throws Exception;

    void updateDept(Dept dept);

    Dept get(Integer id);
}
