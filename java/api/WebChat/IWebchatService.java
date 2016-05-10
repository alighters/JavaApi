package com.mingdao.data.net.webchat;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IWebchatService {
    /**
     * 获取个人和群聊最近联系人
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     **/
    @GET("V1/webchat/get_chat_list")
    Observable<Void> getChatList(
        @Query("access_token") String accessToken);

    /**
     * 获取未读计数
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     **/
    @GET("V1/webchat/get_chat_un_read_count")
    Observable<Void> getChatUnReadCount(
        @Query("access_token") String accessToken);

    /**
     * 获取与某个用户或某个群组的消息列表
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId      用户编号
     * @param groupId      群组编号
     * @param sinceTime      
     * @param direction      向前 true/向后 false
     * @param keyword      搜索关键字
     * @param pageIndex      当前页码(以1开始，1代表第一页)
     * @param pageSize      指定要返回的记录条数
     * @param chooseType    必填字段  用户true/群组false 消息列表
     **/
    @GET("V1/webchat/get_user_or_group_message")
    Observable<Void> getUserOrGroupMessage(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId,
        @Query("group_id") String groupId,
        @Query("since_time") String sinceTime,
        @Query("direction") Boolean direction,
        @Query("keyword") String keyword,
        @Query("pageindex") int pageIndex,
        @Query("pagesize") int pageSize,
        @Query("choose_type") Boolean chooseType);

    /**
     * 私聊会话中用户/群组消息的总条数
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId      用户编号
     * @param groupId      群组编号
     * @param chooseType    必填字段  用户true/群组false 消息列表
     **/
    @GET("V1/webchat/get_user_or_group_message_count")
    Observable<Void> getUserOrGroupMessageCount(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId,
        @Query("group_id") String groupId,
        @Query("choose_type") Boolean chooseType);

    /**
     * 获取跟某个用户/某个群组的前后几条信息
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId      用户编号
     * @param groupId      群组编号
     * @param messageId    必填字段  消息id
     * @param size      群组编号
     * @param chooseType    必填字段  用户true/群组false 消息列表
     **/
    @GET("V1/webchat/get_user_or_group_message_by_id")
    Observable<Void> getUserOrGroupMessageById(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId,
        @Query("group_id") String groupId,
        @Query("message_id") String messageId,
        @Query("size") int size,
        @Query("choose_type") Boolean chooseType);

    /**
     * 获取已加入的群
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     **/
    @GET("V1/webchat/get_joined_group")
    Observable<Void> getJoinedGroup(
        @Query("access_token") String accessToken);

    /**
     * 获取群组的用户
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupId    必填字段  群组编号
     **/
    @GET("V1/webchat/get_group_members")
    Observable<Void> getGroupMembers(
        @Query("access_token") String accessToken,
        @Query("group_id") String groupId);

    /**
     * 删除历史聊天记录
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId      用户编号
     * @param groupId      群组编号
     **/
    @GET("V1/webchat/delete_chat_history_item")
    Observable<Void> deleteChatHistoryItem(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId,
        @Query("group_id") String groupId);

    /**
     * 设置单个/所有群组push
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param isPush    必填字段  是否开启推送 
     * @param chooseType    必填字段  单个 true/全部 false 
     * @param groupId      群组编号
     **/
    @GET("V1/webchat/post_delete_history_item")
    Observable<Void> postDeleteHistoryItem(
        @Query("access_token") String accessToken,
        @Query("is_push") Boolean isPush,
        @Query("choose_type") String chooseType,
        @Query("group_id") String groupId);
}
