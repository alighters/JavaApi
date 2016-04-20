package com.mingdao.data.net.passport;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IPassportService {
    /**
     * 当前登录用户基本信息
     *
     * @param accessToken   当前登录用户访问令牌
     **/
    @GET("/v1/passport/get_passport_detail")
    Observable<Void> getPassportDetail(
        @Query("access_token") String accessToken);

    /**
     * 当前登录用户的设置信息
     *
     * @param accessToken   当前登录用户访问令牌
     **/
    @GET("/v1/passport/get_passport_setting")
    Observable<Void> getPassportSetting(
        @Query("access_token") String accessToken);

    /**
     * 获取当前登录用户的各种未读消息数量
     *
     * @param accessToken   当前登录用户访问令牌
     **/
    @GET("/v1/passport/get_un_read_count")
    Observable<Void> getUnReadCount(
        @Query("access_token") String accessToken);

    /**
     * 获取当前登录用户最常用的的前10个标签
     *
     * @param accessToken   当前登录用户访问令牌
     **/
    @GET("/v1/passport/get_common_category")
    Observable<Void> getCommonCategory(
        @Query("access_token") String accessToken);
}
