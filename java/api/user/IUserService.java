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
     * @param pageIndex      指定当前的页码(不指定页码返回所有)
     * @param pageSize      指定要返回的记录条数(默认值20，最大值100)
     **/
    @GET("v1/user/get_new_friends")
    Observable<Void> getNewFriends(
        @Query("access_token") String accessToken,
        @Query("pageindex") int pageIndex,
        @Query("pagesize") int pageSize);

    /**
     * 我的联系人
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param pageIndex      指定当前的页码(不指定页码返回所有)
     * @param pageSize      指定要返回的记录条数(默认值20，最大值100)
     * @param timestamp      上次拉取列表返回的时间戳
     **/
    @GET("v1/user/get_my_friends")
    Observable<Void> getMyFriends(
        @Query("access_token") String accessToken,
        @Query("pageindex") int pageIndex,
        @Query("pagesize") int pageSize,
        @Query("timestamp") String timestamp);

    /**
     * 获取我的最常协作人(不区分网络)
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     **/
    @GET("v1/user/get_metioned_users")
    Observable<Void> getMetionedUsers(
        @Query("access_token") String accessToken);

    /**
     * 根据手机号获取是否是明道用户
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param phone    必填字段  手机号码
     **/
    @GET("v1/user/get_account_byphone")
    Observable<Void> getAccountByphone(
        @Query("access_token") String accessToken,
        @Query("phone") String phone);

    /**
     * 获取通讯录
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param projectId    必填字段  要获取的网络ID
     * @param department      部门名称
     * @param pageIndex      指定当前的页码(不指定页码返回所有)
     * @param pageSize      指定要返回的记录条数(默认值20，最大值100)
     * @param timestamp      上次拉取列表返回的时间戳
     **/
    @GET("v1/user/get_project_users")
    Observable<Void> getProjectUsers(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId,
        @Query("department") String department,
        @Query("pageindex") int pageIndex,
        @Query("pagesize") int pageSize,
        @Query("timestamp") String timestamp);

    /**
     * 个人卡片信息
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId    必填字段  用户账号ID
     **/
    @GET("v1/user/get_user_card")
    Observable<Void> getUserCard(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId);

    /**
     * 请求添加好友
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId    必填字段  请求加为好友的用户账号ID
     * @param message    必填字段  请求描述
     **/
    @POST("v1/user/add_friend")
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
    @POST("v1/user/update_friend_status")
    Observable<Void> updateFriendStatus(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId,
        @Query("status") int status);

    /**
     * 移除好友
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId    必填字段  需要移除的用户账号ID
     **/
    @POST("v1/user/remove_friend")
    Observable<Void> removeFriend(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId);

    /**
     * 屏蔽/取消屏蔽 好友
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId    必填字段  需要移除的用户账号ID
     * @param isShiled    必填字段  是否屏蔽好友
     **/
    @POST("v1/user/shiled_friend")
    Observable<Void> shiledFriend(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId,
        @Query("is_shiled") Boolean isShiled);

    /**
     * 添加用户手机通讯录
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param mobiles    必填字段  手机号码数组[1300000000,13000000000]序列化
     **/
    @POST("v1/user/add_mobile_address")
    Observable<Void> addMobileAddress(
        @Query("access_token") String accessToken,
        @Query("mobiles") String mobiles);

    /**
     * 获取手机通讯录推荐好友列表
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     **/
    @GET("v1/user/get_mobile_address_recommend")
    Observable<Void> getMobileAddressRecommend(
        @Query("access_token") String accessToken);
}
