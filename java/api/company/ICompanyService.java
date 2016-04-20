package com.mingdao.data.net.company;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ICompanyService {
    /**
     * 获取我的网络列表
     *
     * @param accessToken   当前登录用户访问令牌
     * @param pageindex   指定当前的页码（不指定页码返回所有）
     * @param pagesize   指定要返回的记录条数（默认值20，最大值100）
     **/
    @GET("/v1/company/get_project_list")
    Observable<Void> getProjectList(
        @Query("access_token") String accessToken,
        @Query("pageindex") int pageindex,
        @Query("pagesize") int pagesize);

    /**
     * 获取网络部门列表
     *
     * @param accessToken   当前登录用户访问令牌
     * @param projectId   要获取的网络ID
     * @param pageindex   指定当前的页码（不指定页码返回所有）
     * @param pagesize   指定要返回的记录条数（默认值20，最大值100）
     **/
    @GET("/v1/company/get_project_departments")
    Observable<Void> getProjectDepartments(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId,
        @Query("pageindex") int pageindex,
        @Query("pagesize") int pagesize);
}
