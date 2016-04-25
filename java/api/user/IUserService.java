package com.mingdao.data.net.user;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IUserService {
    /**
     * 新的朋友
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param pageindex      指定当前的页码(不指定页码返回所有)
     * @param pagesize      指定要返回的记录条数(默认值20，最大值100)
     **/
    @GET("/v1/user/get_new_friends")
    Observable<Void> getNewFriends(
        @Query("access_token") String accessToken,
        @Query("pageindex") int pageindex,
        @Query("pagesize") int pagesize);

    /**
     * 我的联系人
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param pageindex      指定当前的页码(不指定页码返回所有)
     * @param pagesize      指定要返回的记录条数(默认值20，最大值100)
     **/
    @GET("/v1/user/get_my_friends")
    Observable<Void> getMyFriends(
        @Query("access_token") String accessToken,
        @Query("pageindex") int pageindex,
        @Query("pagesize") int pagesize);

    /**
     * 获取通讯录
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param projectId    必填字段  要获取的网络ID
     * @param department      部门名称
     * @param pageindex      指定当前的页码(不指定页码返回所有)
     * @param pagesize      指定要返回的记录条数(默认值20，最大值100)
     **/
    @GET("/v1/user/get_project_users")
    Observable<Void> getProjectUsers(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId,
        @Query("department") String department,
        @Query("pageindex") int pageindex,
        @Query("pagesize") int pagesize);

    /**
     * 个人卡片全部信息
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId    必填字段  用户账号ID
     **/
    @GET("/v1/user/get_user_card_full_info")
    Observable<Void> getUserCardFullInfo(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId);

    /**
     * 请求添加好友
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId    必填字段  请求加为好友的用户账号ID
     * @param message    必填字段  请求描述
     **/
    @POST("/v1/user/add_friend")
    Observable<Void> addFriend(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId,
        @Query("message") String message);

    /**
     * 同意，拒绝，忽略请求添加好友
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId    必填字段  操作的用户账号ID
     * @param status    必填字段  同意=1,忽略=2,拒绝=3
     **/
    @POST("/v1/user/update_friend_status")
    Observable<Void> updateFriendStatus(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId,
        @Query("status") int status);

    /**
     * 移除好友
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId    必填字段  需要移除的用户账号ID
     * @param status    必填字段  同意=1,忽略=2,拒绝=3
     **/
    @POST("/v1/user/remove_friend")
    Observable<Void> removeFriend(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId,
        @Query("status") int status);

    /**
     * 屏蔽/取消屏蔽 好友
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId    必填字段  需要移除的用户账号ID
     * @param isShiled    必填字段  屏蔽1 / 取消屏蔽0
     **/
    @POST("/v1/user/shiled_friend")
    Observable<Void> shiledFriend(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId,
        @Query("is_shiled") Boolean isShiled);
}
