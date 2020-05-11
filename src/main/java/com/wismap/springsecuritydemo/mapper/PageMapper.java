package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageMapper {

    /**
     * @param prisymbol 类型
     * 查询所有页面信息
     * @return 页面信息列表
     */
    List<Page> SelectAll();

    /**
     * 根据url精确查找
     * @param url 页面地址
     * @return 页面信息
     */
    Page selectByUrl(@Param("url") String url);

    /**
     * 根据ID精确查找
     * @param id 页面地址
     * @return 页面信息
     */
    Page selectById(@Param("id") Long id);

    /**
     * 新增页面
     * @param page 页面信息
     * @return 新增记录的id
     */
    Long insert(Page page);

    /**
     * 根据url删除页面
     * @param url 页面地址
     * @return 删除数量
     */
    Long delete(@Param("id") Long id);

    /**
     * 更新页面信息
     * @param page 页面信息
     * @return 更新的数量
     */
    Long update(Page page);

    /**
     *向上排序,比目标位置大的都加一
     * @param pid 父节点id
     * @param sort 目标位置
     * @return 是否成功
     */
    Boolean set_up(@Param("pid") Long pid, @Param("sort") Long sort, @Param("currentSort") Long currentSort);

    /**
     *向下排序,比目标位置大的都减一
     * @param pid 父节点id
     * @param sort 目标位置
     * @return 是否成功
     */
    Boolean set_down(@Param("pid") Long pid, @Param("sort") Long sort, @Param("currentSort") Long currentSort);

    /**
     * 向上排序,比目标位置大的都加一,跨组排序时使用
     * @param pid 旧的组的id
     * @param sort 该记录在旧的组中的位置
     * @return 是否成功
     */
    Boolean clearsequp(@Param("pid") Long pid, @Param("sort") Long sort);

    /**
     * 跨组排序时，将旧的组中的排序归位
     * @param pid 旧的组的id
     * @param sort 该记录在旧的组中的位置
     * @return 是否成功
     */
    Boolean clearseq(@Param("pid") Long pid, @Param("sort") Long sort);

    /**
     * 根据pid查询最大序号
     * @param pid
     * @return  最大序号
     */
    Long  selectMaxSort(@Param("pid") Long pid);

    /**
     * 根据角色查询页面
     * @param list   权限集合
     * @return  页面集合
     */
    List<Page> selectPageByPrivilegeIds(@Param("priids") Long[] priids);

}