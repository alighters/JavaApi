package com.mingdao.data.net.admin.department;



import retrofit2.http.GET;

import retrofit2.http.POST;

import retrofit2.http.Query;

import rx.Observable;



public interface IDepartmentService {

    /**

     * 获取网络部门

     *

     * @param accessToken   当前登录用户访问令牌

     * @param projectId   哪个网络必传

     * @param keywords   群组的简介

     * @param pageindex   第几页

     * @param pagesize   每页条数

     * @param sortField   排序条件

     * @param sortType   排序类型

     **/

    @GET("/v1/admin/department/get_project_departments")

    Observable<Void> getProjectDepartments(

        @Query("access_token") String accessToken,

        @Query("project_id") String projectId,

        @Query("keywords") String keywords,

        @Query("pageindex") int pageindex,

        @Query("pagesize") int pagesize,

        @Query("sort_field") int sortField,

        @Query("sort_type") int sortType);



    /**

     * 新增网络部门

     *

     * @param accessToken   当前登录用户访问令牌

     * @param projectId   哪个网络必传

     * @param departmentName   部门名字

     * @param mappingGroupId   关联群组ID

     **/

    @POST("/v1/admin/department/add_project_department")

    Observable<Void> addProjectDepartment(

        @Query("access_token") String accessToken,

        @Query("project_id") String projectId,

        @Query("department_name") String departmentName,

        @Query("mapping_group_id") String mappingGroupId);



    /**

     * 修改网络部门

     *

     * @param accessToken   当前登录用户访问令牌

     * @param projectId   哪个网络必传

     * @param departmentId   部门ID

     * @param departmentName   部门名字

     * @param mappingGroupId   关联群组ID

     **/

    @POST("/v1/admin/department/update_project_department")

    Observable<Void> updateProjectDepartment(

        @Query("access_token") String accessToken,

        @Query("project_id") String projectId,

        @Query("department_id") String departmentId,

        @Query("department_name") String departmentName,

        @Query("mapping_group_id") String mappingGroupId);

    /**
     * 获取网络部门
     *
     * @param accessToken   当前登录用户访问令牌
     * @param projectId   哪个网络必传
     * @param keywords   群组的简介
     * @param pageindex   第几页
     * @param pagesize   每页条数
     * @param sortField   排序条件
     * @param sortType   排序类型
     **/
    @GET("/v1/admin/department/get_project_departments")
    Observable<Void> getProjectDepartments(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId,
        @Query("keywords") String keywords,
        @Query("pageindex") int pageindex,
        @Query("pagesize") int pagesize,
        @Query("sort_field") int sortField,
        @Query("sort_type") int sortType);

    /**
     * 新增网络部门
     *
     * @param accessToken   当前登录用户访问令牌
     * @param projectId   哪个网络必传
     * @param departmentName   部门名字
     * @param mappingGroupId   关联群组ID
     **/
    @POST("/v1/admin/department/add_project_department")
    Observable<Void> addProjectDepartment(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId,
        @Query("department_name") String departmentName,
        @Query("mapping_group_id") String mappingGroupId);

    /**
     * 修改网络部门
     *
     * @param accessToken   当前登录用户访问令牌
     * @param projectId   哪个网络必传
     * @param departmentId   部门ID
     * @param departmentName   部门名字
     * @param mappingGroupId   关联群组ID
     **/
    @POST("/v1/admin/department/update_project_department")
    Observable<Void> updateProjectDepartment(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId,
        @Query("department_id") String departmentId,
        @Query("department_name") String departmentName,
        @Query("mapping_group_id") String mappingGroupId);
}

