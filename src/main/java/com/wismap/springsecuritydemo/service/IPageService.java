package com.wismap.springsecuritydemo.service;

import com.wismap.springsecuritydemo.model.Page;

import java.util.List;

public interface IPageService {

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
    Page selectByUrl(String url);

    /**
     * 新增页面
     * @param page 页面信息
     * @return 新增记录的id
     */
    Long Insert(Page page);

    /**
     * 根据id删除页面
     * @param id 页面地址
     * @return 删除数量
     */
    Long delete(Long id);

    /**
     * 更新页面信息
     * @param page 页面信息
     * @return 更新的数量
     */
    Long update(Page page)throws Exception;


    /**
     * 序号排序操作
     * @param id        页面id
     * @param sort      序号
     * @param pid       父id
     * @return          操作状态
     */
    Boolean SetSeq(Long id, Long sort, Long pid);


    /**
     * 修改页面：改变页面级别
     */
    void updatePrivilegePage(Page page);


    /**
     * 根据id精确查找
     * @param id 页面地址
     * @return 页面信息
     */
    Page selectById(Long id);

}
