package com.mingdao.data.net.post;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IPostService {
    /**
     * 获取全公司的动态更新 
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param keywords      关键词模糊搜索，当为空时则返回所有的动态更新
     * @param postType      筛选动态更新类型,默认-1：表示全部动态；0：普通消息；1：链接；2：图片；3：文档；4：提问；7：投票；8：视频
     * @param sinceId      若指定此参数，则只返回ID比since_id大的动态更新(即比since_id发表时间晚的动态更新)
     * @param maxId      若指定此参数，则只返回ID比max_id小的动态更新(即比max_id发表时间早的动态更新)
     * @param pageSize      指定要返回的记录条数(int默认值20，最大值100)
     **/
    @GET("v1/post/get_all_posts")
    Observable<Void> getAllPosts(
        @Query("access_token") String accessToken,
        @Query("keywords") String keywords,
        @Query("post_type") int postType,
        @Query("since_id") long sinceId,
        @Query("max_id") long maxId,
        @Query("pagesize") int pageSize);

    /**
     * 获取提及@我的动态更新(适用inbox中的提到我的)
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param isUnreading      是否获取未读提及我的动态 默认0：否
     * @param keywords      关键词模糊搜索，当为空时则返回所有的动态更新
     * @param postType      筛选动态更新类型,默认-1：表示全部动态；0：普通消息；1：链接；2：图片；3：文档；4：提问；7：投票
     * @param pageIndex      指定当前的页码
     * @param pageSize      指定要返回的记录条数(int默认值20，最大值100)
     **/
    @GET("v1/post/get_at_me_posts")
    Observable<Void> getAtMePosts(
        @Query("access_token") String accessToken,
        @Query("is_unreading") Boolean isUnreading,
        @Query("keywords") String keywords,
        @Query("post_type") int postType,
        @Query("pageindex") long pageIndex,
        @Query("pagesize") int pageSize);

    /**
     * 根据动态更新编号获取单条动态更新内容
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param postId    必填字段  动态更新编号
     **/
    @GET("v1/post/get_post_detail")
    Observable<Void> getPostDetail(
        @Query("access_token") String accessToken,
        @Query("post_id") String postId);

    /**
     * 获取 文档/图片/问答 列表信息 
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupId      群组编号
     * @param keywords      关键词模糊搜索，当为空时则返回所有的动态更新
     * @param filterType      过滤类型。默认值0，0表示所有；1表示我上传的；2表示我收藏的
     * @param sinceId      若指定此参数，则只返回ID比since_id大的动态更新(即比since_id发表时间晚的动态更新)
     * @param maxId      若指定此参数，则只返回ID比max_id小的动态更新(即比max_id发表时间早的动态更新)
     * @param pageSize      指定要返回的记录条数(int默认值20，最大值100)
     * @param selectType    必填字段  查询类型 (2：图片,3:文档，4：问答)
     **/
    @GET("v1/post/get_doc_faq_img_posts")
    Observable<Void> getDocFaqImgPosts(
        @Query("access_token") String accessToken,
        @Query("group_id") String groupId,
        @Query("keywords") String keywords,
        @Query("filter_type") int filterType,
        @Query("since_id") long sinceId,
        @Query("max_id") long maxId,
        @Query("pagesize") int pageSize,
        @Query("select_type") int selectType);

    /**
     * 获取当前登录用户收藏的动态更新
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param keywords      关键词模糊搜索，当为空时则返回所有的动态更新
     * @param postType      筛选动态更新类型,默认-1：表示全部动态；0：普通消息；1：链接；2：图片；3：文档；4：提问；7：投票
     * @param maxId      若指定此参数，则只返回ID比max_id小的动态更新(即比max_id发表时间早的动态更新)
     * @param pageSize      指定要返回的记录条数(int默认值20，最大值100)
     **/
    @GET("v1/post/get_favorite_posts")
    Observable<Void> getFavoritePosts(
        @Query("access_token") String accessToken,
        @Query("keywords") String keywords,
        @Query("post_type") int postType,
        @Query("max_id") long maxId,
        @Query("pagesize") int pageSize);

    /**
     * 获取群组的动态更新
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param keywords      关键词模糊搜索，当为空时则返回所有的动态更新
     * @param groupId    必填字段  群组编号
     * @param maxId      若指定此参数，则只返回ID比max_id小的动态更新(即比max_id发表时间早的动态更新)
     * @param pageSize      指定要返回的记录条数(int默认值20，最大值100)
     **/
    @GET("v1/post/get_group_groups")
    Observable<Void> getGroupGroups(
        @Query("access_token") String accessToken,
        @Query("keywords") String keywords,
        @Query("group_id") String groupId,
        @Query("max_id") long maxId,
        @Query("pagesize") int pageSize);

    /**
     * 获取全公司的置顶动态更新
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     **/
    @GET("v1/post/get_company_top_posts")
    Observable<Void> getCompanyTopPosts(
        @Query("access_token") String accessToken);

    /**
     * 获取当前登录用户发布的动态更新
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param keywords      关键词模糊搜索，当为空时则返回所有的动态更新
     * @param postType      筛选动态更新类型,默认-1：表示全部动态；0：普通消息；1：链接；2：图片；3：文档；4：提问；7：投票；8：视频
     * @param maxId      若指定此参数，则只返回ID比max_id小的动态更新(即比max_id发表时间早的动态更新)
     * @param sinceId      若指定此参数，则只返回ID比since_id大的动态更新(即比since_id发表时间晚的动态更新)
     * @param pageSize      指定要返回的记录条数(int默认值20，最大值100)
     **/
    @GET("v1/post/get_my_posts")
    Observable<Void> getMyPosts(
        @Query("access_token") String accessToken,
        @Query("keywords") String keywords,
        @Query("post_type") int postType,
        @Query("max_id") long maxId,
        @Query("since_id") long sinceId,
        @Query("pagesize") int pageSize);

    /**
     * 获取我回复的最新回复信息
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param keywords      关键词模糊搜索，当为空时则返回所有的动态更新
     * @param maxId      若指定此参数，则只返回ID比max_id小的动态更新(即比max_id发表时间早的动态更新)
     * @param pageSize      指定要返回的记录条数(int默认值20，最大值100)
     **/
    @GET("v1/post/get_reply_by_me_posts")
    Observable<Void> getReplyByMePosts(
        @Query("access_token") String accessToken,
        @Query("keywords") String keywords,
        @Query("max_id") long maxId,
        @Query("pagesize") int pageSize);

    /**
     * 获取回复我的最新回复信息
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param keywords      关键词模糊搜索，当为空时则返回所有的动态更新
     * @param isUnreading      是否获取未读提及我的动态 默认0：否
     * @param maxId      若指定此参数，则只返回ID比max_id小的动态更新(即比max_id发表时间早的动态更新)(特殊，数据库内部处理)
     * @param pageSize      指定要返回的记录条数(int默认值20，最大值100)
     **/
    @GET("v1/post/get_reply_me_posts")
    Observable<Void> getReplyMePosts(
        @Query("access_token") String accessToken,
        @Query("keywords") String keywords,
        @Query("is_unreading") Boolean isUnreading,
        @Query("max_id") String maxId,
        @Query("pagesize") int pageSize);

    /**
     * 根据动态更新编号获取某条动态更新的回复列表信息
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param postId    必填字段  动态更新编号
     **/
    @GET("v1/post/get_reply_post")
    Observable<Void> getReplyPost(
        @Query("access_token") String accessToken,
        @Query("post_id") String postId);

    /**
     * 获取某个标签下的动态更新
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param tag    必填字段  标签名称
     * @param keywords      关键词模糊搜索
     * @param pageSize      指定要返回的记录条数(默认值20，最大值100
     **/
    @GET("v1/post/get_tag_posts")
    Observable<Void> getTagPosts(
        @Query("access_token") String accessToken,
        @Query("tag") String tag,
        @Query("keywords") String keywords,
        @Query("pagesize") int pageSize);

    /**
     * 获取用户发布的动态更新
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param accountId    必填字段  用户编号
     * @param maxId      若指定此参数，则只返回ID比max_id小的动态更新(即比max_id发表时间早的动态更新)
     * @param pageSize      指定要返回的记录条数(int默认值20，最大值100)
     **/
    @GET("v1/post/get_user_posts")
    Observable<Void> getUserPosts(
        @Query("access_token") String accessToken,
        @Query("account_id") String accountId,
        @Query("max_id") long maxId,
        @Query("pagesize") int pageSize);

    /**
     * 获取当前企业动态更新标签信息
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param keywords      关键词模糊搜索
     * @param pageIndex      指定当前的页码
     * @param pageSize      指定要返回的记录条数(默认值20，最大值100
     **/
    @GET("v1/post/get_company_tag")
    Observable<Void> getCompanyTag(
        @Query("access_token") String accessToken,
        @Query("keywords") String keywords,
        @Query("pageindex") long pageIndex,
        @Query("pagesize") int pageSize);

    /**
     * 增加当前登录用户的一条动态更新 收藏/喜欢
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param postId    必填字段  动态更新编号
     * @param chooseType      操作类型 1:喜欢 2：收藏
     **/
    @POST("v1/post/add_favorite_or_like_post")
    Observable<Void> addFavoriteOrLikePost(
        @Query("access_token") String accessToken,
        @Query("post_id") String postId,
        @Query("choose_type") int chooseType);

    /**
     * 增加一条动态更新的回复
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param postId    必填字段  回复的动态更新编号
     * @param replyId      回复编号(可以对别人的回复进行回复)[可选]
     * @param replyMsg    必填字段  回复的消息内容([aid]accountID[/aid]代表@某个人,[gid]groupID[/gid]代表@某个群组)
     * @param fileType      可为空(为空时 p_img或p_doc也必须为空)，picture：表示上传图片；document：表示上传文档
     * @param pImg或pDoc    必填字段  要上传的图片、文档。图片仅支持JPEG,GIF,PNG,目前上传图片大小限制为<8M。文档仅支持DOC,PDF,XLS,PPT,TXT,压缩包,目前上传文件大小限制为<50M
     * @param isShare      同时转发动态(0表示不转发动态；1表示同时转发动态)
     * @param groupIds      可为空，动态分享群组编号(多个群组用逗号隔开)
     * @param shareType      分享范围(0表示分享给所有同事;1表示群内分享；2表示所有关注者和群组；3表示分享给自己； 默认0表示分享给所有同事)
     **/
    @POST("v1/post/add_reply_post")
    Observable<Void> addReplyPost(
        @Query("access_token") String accessToken,
        @Query("post_id") String postId,
        @Query("reply_id") String replyId,
        @Query("reply_msg") int replyMsg,
        @Query("file_type") int fileType,
        @Query("p_img或p_doc") int pImg或pDoc,
        @Query("is_share") Boolean isShare,
        @Query("group_ids") String groupIds,
        @Query("share_type") int shareType);

    /**
     * 增加/删除一条动态更新的标签
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param postId    必填字段  动态更新编号(post_id为空 只创建个标签)
     * @param tag      标签名称
     * @param chooseType    必填字段  操作类型 添加=true/删除=false
     **/
    @POST("v1/post/add_or_delete_post_tag")
    Observable<Void> addOrDeletePostTag(
        @Query("access_token") String accessToken,
        @Query("post_id") String postId,
        @Query("tag") String tag,
        @Query("choose_type") Boolean chooseType);

    /**
     * 根据动态更新编号删除一条动态更新
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param postId    必填字段  动态更新编号
     **/
    @POST("v1/post/delete_post")
    Observable<Void> deletePost(
        @Query("access_token") String accessToken,
        @Query("post_id") int postId);

    /**
     * 删除当前登录用户 收藏/喜欢 的一条动态更新 
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param postId    必填字段  动态更新编号
     * @param chooseType    必填字段  操作类型 喜欢=1/收藏=2
     **/
    @POST("v1/post/delete_favorite_or_like_post")
    Observable<Void> deleteFavoriteOrLikePost(
        @Query("access_token") String accessToken,
        @Query("post_id") String postId,
        @Query("choose_type") int chooseType);

    /**
     * 根据回复编号删除一条回复 *
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param postId    必填字段  动态更新编号
     * @param replyId      回复编号(必须是当前登录用户自己创建的回复
     **/
    @POST("v1/post/delete_reply_post")
    Observable<Void> deleteReplyPost(
        @Query("access_token") String accessToken,
        @Query("post_id") String postId,
        @Query("reply_id") String replyId);

    /**
     * 置顶一条动态更新(仅限网络管理员使用) 
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param postId    必填字段  动态更新编号
     * @param duration      置顶时长 默认为:不限时长 1:代表时长24小时；2:代表时长48小时；3:代表时长72小时.
     **/
    @POST("v1/post/top_post")
    Observable<Void> topPost(
        @Query("access_token") String accessToken,
        @Query("post_id") int postId,
        @Query("duration") int duration);

    /**
     * 发布一条动态更新
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupIds      可为空，动态分享群组编号(多个群组用逗号隔开)
     * @param postMsg    必填字段  动态更新内容(###userID###代表@某个人,$$$groupID$$$代表@某个群组，#标签内容#代表给动态定义标签)
     * @param postType      动态更新类型(0表示普通动态更新(默认值);1表示链接动态更新 ;4表示问答动态更新)
     * @param shareType      分享范围(0表示分享给所有同事;1表示群内分享；2表示所有关注者和群组；3表示分享给自己； 默认0表示分享给所有同事)
     **/
    @POST("v1/post/issue_post")
    Observable<Void> issuePost(
        @Query("access_token") String accessToken,
        @Query("group_ids") String groupIds,
        @Query("post_msg") String postMsg,
        @Query("post_type") int postType,
        @Query("share_type") int shareType);

    /**
     * 上传图片、文档并发布一条动态更新
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param groupIds      可为空，动态分享群组编号(多个群组用逗号隔开)
     * @param postMsg    必填字段  动态更新内容(###userID###代表@某个人,$$$groupID$$$代表@某个群组，#标签内容#代表给动态定义标签)
     * @param fileType      可为空，默认为picture：表示上传图片；document：表示上传文档
     * @param pImg或pDoc    必填字段  要上传的图片、文档。图片仅支持JPEG,GIF,PNG,目前上传图片大小限制为<8M。文档仅支持DOC,PDF,XLS,PPT,TXT,压缩包,目前上传文件大小限制为<50M
     * @param isKnowledge      是否加入中心(0表示不加入；1表示加入 默认为1加入)
     * @param shareType      分享范围(0表示分享给所有同事;1表示群内分享；2表示所有关注者和群组；3表示分享给自己； 默认0表示分享给所有同事)
     **/
    @POST("v1/post/upload_post")
    Observable<Void> uploadPost(
        @Query("access_token") String accessToken,
        @Query("group_ids") String groupIds,
        @Query("post_msg") String postMsg,
        @Query("file_type") int fileType,
        @Query("p_img或p_doc") int pImg或pDoc,
        @Query("is_knowledge") int isKnowledge,
        @Query("share_type") String shareType);
}
