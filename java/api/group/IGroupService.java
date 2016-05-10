package com.mingdao.data.net.group;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IGroupService {
    /**
     * 根据群组编号获取群组的基本资料
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupId    必填字段  群组编号
     **/
    @GET("v1/group/get_group_detail")
    Observable<Void> getGroupDetail(
        @Query("access_token") String accessToken,
        @Query("group_id") String groupId);

    /**
     * 获取用户创建的群组
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId      指定用户编号，获取此用户创建的群组，默认为当前授权用户
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @GET("v1/group/get_my_created_groups")
    Observable<Void> getMyCreatedGroups(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId,
        @Query("project_id") String projectId);

    /**
     * 获取公司群组
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param projectId    必填字段  要获取的网络ID
     * @param sortGroup      默认按群组名称排序（2：按动态数量最常协作群组）
     * @param sortType      升序还是降序
     * @param pageIndex      指定当前的页码(不指定页码返回所有)
     * @param pageSize      指定要返回的记录条数(默认值20，最大值100)
     **/
    @GET("v1/group/get_project_groups")
    Observable<Void> getProjectGroups(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId,
        @Query("sort_group") int sortGroup,
        @Query("sort_type") Boolean sortType,
        @Query("pageindex") int pageIndex,
        @Query("pagesize") int pageSize);

    /**
     * 获取用户加入的群组
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId      指定用户编号，获取此用户创建的群组，默认为当前授权用户
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @GET("v1/group/get_account_joined_groups")
    Observable<Void> getAccountJoinedGroups(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId,
        @Query("project_id") String projectId);

    /**
     * 获取群组成员信息
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupId    必填字段  群组编号
     **/
    @GET("v1/group/get_group_members")
    Observable<Void> getGroupMembers(
        @Query("access_token") String accessToken,
        @Query("group_id") String groupId);

    /**
     * 获取群组待审批成员信息
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupId    必填字段  群组编号
     **/
    @GET("v1/group/get_unaudited_members")
    Observable<Void> getUnauditedMembers(
        @Query("access_token") String accessToken,
        @Query("group_id") String groupId);

    /**
     * 创建一个新的群组
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupName    必填字段  要创建的群组的名称
     * @param about      群组的简介
     * @param projectId      群组网络
     * @param avatar      群组头像
     * @param accountIds      群组成员
     **/
    @POST("v1/group/create_group")
    Observable<Void> createGroup(
        @Query("access_token") String accessToken,
        @Query("group_name") String groupName,
        @Query("about") String about,
        @Query("project_id") String projectId,
        @Query("avatar") String avatar,
        @Query("account_ids") String accountIds);

    /**
     * 编辑群组
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupId    必填字段  群组id
     * @param groupName    必填字段  要创建的群组的名称
     * @param about      群组的简介
     * @param isApproval      群组网络
     * @param avatar      群组头像
     **/
    @POST("v1/group/edit_group")
    Observable<Void> editGroup(
        @Query("access_token") String accessToken,
        @Query("group_id") String groupId,
        @Query("group_name") String groupName,
        @Query("about") String about,
        @Query("is_approval") String isApproval,
        @Query("avatar") String avatar);

    /**
     * 群组操作退出
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupId    必填字段  群组编号
     **/
    @POST("v1/group/exit_group")
    Observable<Void> exitGroup(
        @Query("access_token") String accessToken,
        @Query("group_id") String groupId);

    /**
     * 添加群组管理员(仅限群组管理员和网络管理员)
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupId    必填字段  群组编号
     * @param accountId    必填字段  用户编号
     **/
    @POST("v1/group/add_group_admin")
    Observable<Void> addGroupAdmin(
        @Query("access_token") String accessToken,
        @Query("group_id") String groupId,
        @Query("account_id") String accountId);

    /**
     * 移除群组用户/管理员(仅限群组管理员和网络管理员)
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupId      群组编号
     * @param accountId      用户编号
     * @param chooseType    必填字段  操作类型 1：移除用户 2：移除管理员(仅限群组管理员)
     **/
    @POST("v1/group/remove_group_user_or_admin")
    Observable<Void> removeGroupUserOrAdmin(
        @Query("access_token") String accessToken,
        @Query("group_id") String groupId,
        @Query("account_id") String accountId,
        @Query("choose_type") int chooseType);

    /**
     * 重新邀请群组成员
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupId    必填字段  群组编号
     * @param emails    必填字段  重新邀请用户email 多个邮箱用逗号隔开
     * @param inviteType      邀请类型 0：内部用户或来宾；1：外联群组用户
     **/
    @POST("v1/group/again_invite_group_user")
    Observable<Void> againInviteGroupUser(
        @Query("access_token") String accessToken,
        @Query("group_id") String groupId,
        @Query("emails") String emails,
        @Query("invite_type") int inviteType);

    /**
     * 邀请成员
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupId    必填字段  群组编号
     * @param accountIds      邀请加入群组的现有用户ID 多个用户用逗号隔开
     * @param egroupEmails      邀请外联用户email 多个邮箱用逗号隔开
     * @param egroupMobilephones      邀请外联用户手机号码 多个手机号码用逗号隔开
     **/
    @POST("v1/group/invite_user_join_group")
    Observable<Void> inviteUserJoinGroup(
        @Query("access_token") String accessToken,
        @Query("group_id") String groupId,
        @Query("account_ids") String accountIds,
        @Query("egroup_emails") String egroupEmails,
        @Query("egroup_mobilephones") String egroupMobilephones);

    /**
     * 同意/拒绝 用户加入群组(仅限群组管理员)
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupId    必填字段  群组编号
     * @param accountIds    必填字段  待审批用户编号 多个用,号隔开
     * @param chooseType    必填字段  同意1/拒绝0
     **/
    @POST("v1/group/pass_or_refuse_user_join_group")
    Observable<Void> passOrRefuseUserJoinGroup(
        @Query("access_token") String accessToken,
        @Query("group_id") String groupId,
        @Query("account_ids") String accountIds,
        @Query("choose_type") Boolean chooseType);

    /**
     * 聊天群组转永久动态群组
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupId    必填字段  群组编号
     **/
    @POST("v1/group/chat_to_post_group")
    Observable<Void> chatToPostGroup(
        @Query("access_token") String accessToken,
        @Query("group_id") String groupId);
}
