package com.mingdao.data.net.admin.application;



import retrofit2.http.GET;

import retrofit2.http.POST;

import retrofit2.http.Query;

import rx.Observable;



public interface IApplicationService {

    /**

     * 获取网络安装应用列表

     *

     * @param accessToken   当前登录用户访问令牌

     * @param projectId   哪个网络必传

     * @param pageindex   指定当前的页码（不指定页码返回所有）

     * @param pagesize   指定要返回的记录条数（默认值20，最大值100）

     **/

    @GET("/v1/admin/application/get_application_list")

    Observable<Void> getApplicationList(

        @Query("access_token") String accessToken,

        @Query("project_id") String projectId,

        @Query("pageindex") int pageindex,

        @Query("pagesize") int pagesize);

    /**
     * 创建一个新的群组
     *
     * @param accessToken   当前登录用户访问令牌
     * @param groupName   要创建的群组的名称
     * @param about   群组的简介
     * @param isHidden   是否列入公司群组列表(*只有私有群组才有此功能)，0不隐藏，1隐藏
     * @param isApproval   用户加入是否审批（0：否，1：是）
     * @param isPost   是否作为动态分享群组（0：否，1：是）
     * @param deptId   部门ID（如果设置官方群组需传关联的部门ID）
     * @param accountIds   群组成员
     **/
    @POST("/v2/group/create_group")
    Observable<Void> createGroup(
        @Query("access_token") String accessToken,
        @Query("group_name") String groupName,
        @Query("about") String about,
        @Query("is_hidden") Boolean isHidden,
        @Query("is_approval") String isApproval,
        @Query("is_post") String isPost,
        @Query("dept_id") int deptId,
        @Query("account_ids") String accountIds);

    /**
     * 获取我的网络列表
     *
     * @param accessToken   当前登录用户访问令牌
     * @param pageindex   指定当前的页码（不指定页码返回所有）
     * @param pagesize   指定要返回的记录条数（默认值20，最大值100）
     **/
    @GET("/v2/company/get_project_list")
    Observable<Void> getProjectList(
        @Query("access_token") String accessToken,
        @Query("pageindex") int pageindex,
        @Query("pagesize") int pagesize);
}

