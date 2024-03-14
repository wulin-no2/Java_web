package com.lina.mapper;

import com.lina.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    // get emp list;
    @Select("SELECT * from db_01.emp")
    public List<Emp> List();
    // delete by id;
    @Delete("delete from emp where id = #{id}")
    public void delete(Integer id);

    // delete some
    public void deleteByIds(List<Integer> ids);
    // add emp;
    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            "VALUES (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    public void insert(Emp emp);
    // update data;
//    @Update("update emp set username = #{username},name=#{name},gender=#{gender},image=#{image},job=#{job}," +
//            "entrydate=#{entrydate},dept_id =#{deptId},update_time=#{updateTime} where id = #{id}")
//    public void update(Emp emp);
    public void update(Emp emp);
    // search info by id;
    @Select("select * from emp where id = #{id}")
    public Emp getById(Integer id);
    // search info by conditions;
//    @Select("select * from emp where name like concat('%',#{name},'%') and gender = #{gender} and entrydate between #{begin} and #{end} order by update_time desc")
//    public List<Emp> searchByCondition(String name, Short gender, LocalDate begin, LocalDate end);
    public List<Emp> searchByCondition(String name, Short gender, LocalDate begin, LocalDate end);
}
