package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmptServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean list(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page, pageSize);
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public Emp get(Integer id) {
        Emp emp = empMapper.get(id);
        return emp;
    }

    @Override
    public void add(Emp emp) {
        empMapper.add(emp);
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void deleteByDepId(Integer deptId) {
        empMapper.deleteByDepId(deptId);
    }

    @Override
    public void update(Emp emp) {
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        Emp user = empMapper.getByUsernameAndPassword(emp);
        return user;
    }
}
