package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    @Select("select * from tb_emp where id = #{id}")
    Emp get(Integer id);

    @Insert("insert into tb_emp(username,name,gender,image,job,entrydate,dept_id,create_time,update_time) values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void add(Emp emp);

    void delete(List<Integer> ids);

    void update(Emp emp);

    @Select("select * from tb_emp where username =#{username} and password=#{password}")
    Emp getByUsernameAndPassword(Emp emp);

    @Delete("delete from tb_emp where dept_id = #{deptId}")
    void deleteByDepId(Integer deptId);
}
