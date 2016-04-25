package com.mingdao.data.net.message;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IMessageService {
    /**
     * 获取当前登录用户与其它单个用户的私人消息列表
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId    必填字段  发送消息对象的用户编号
     * @param pageindex      当前页码(以1开始，1代表第一页)
     * @param pagesize      指定要返回的记录条数
     **/
    @GET("/v1/message/get_message_list")
    Observable<Void> getMessageList(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId,
        @Query("pageindex") int pageindex,
        @Query("pagesize") int pagesize);

    /**
     * 获取消息的第一条信息
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     **/
    @GET("/v1/message/get_inbox_first_message")
    Observable<Void> getInboxFirstMessage(
        @Query("access_token") String accessToken);
}
