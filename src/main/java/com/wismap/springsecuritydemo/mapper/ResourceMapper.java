package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {

    /**
     * 查询所有后台API
     * @return api集合
     */
    List<Resource> selectAll();

    /**
     * 新增后台API
     * @param resource api属性
     * @return 新增的id
     */
    Long insert(Resource resource);

    /**
     * 删除后台api
     * @param id api id
     * @return 删除数量
     */
    Long delete(@Param("id") Long id);

    /**
     * 根据url查找,精确查询
     * @param url api地址
     * @return api
     */
    Resource selectByUrl(@Param("url") String url);

    /**
     * 根据id精确查找
     * @param ids api ids
     * @return api
     */
    List<Resource> selectByIds(@Param("ids") Long[] ids);

    /**
     *更新后台api
     * @param resource api属性
     * @return 新增的id
     */
    Long update(Resource resource);

}