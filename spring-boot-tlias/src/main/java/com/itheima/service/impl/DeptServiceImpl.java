package com.itheima.service.impl;

import com.itheima.anno.MyLog;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        List<Dept> deptList = deptMapper.list();
        return deptList;
    }

    @Override
    public void addDept(Dept dept) {
        deptMapper.addDept(dept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDept(Integer id) throws Exception {
        try {
            // 根据id删除部门
            deptMapper.deleteDept(id);
            if (true) {
                throw new Exception("出现异常了~~~");
            }
            // 根据部门ID
            empMapper.deleteByDepId(id);
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此时解散的是" + id + "号部门");
            deptLogService.insert(deptLog);
        }

    }

    @Override
    public void updateDept(Dept dept) {
        deptMapper.updateDept(dept);
    }

    @Override
    public Dept get(Integer id) {
        Dept dept = deptMapper.get(id);
        return dept;
    }
}
