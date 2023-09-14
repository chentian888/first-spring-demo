package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from tb_dept order by update_time desc")
    public List<Dept> list();

    @Insert("insert into tb_dept(name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    public void addDept(Dept dept);

    @Delete("delete from tb_dept where id = #{id}")
    public void deleteDept(Integer id);

    @Update("update tb_dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    public void updateDept(Dept dept);

    @Select("select * from tb_dept where  id = ${id}")
    public Dept get(Integer id);
}
