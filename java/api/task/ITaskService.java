package com.mingdao.data.net.task;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ITaskService {
    /**
     * 创建项目
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderName    必填字段  项目名称
     * @param chargeUser      项目负责人 默认当前登录用户
     * @param visibility      项目可见性 0私密 1公开仅群组 2全公司(默认0)
     * @param groups      当项目可见性为公开群组时群组ID（多个群组已，相隔）
     * @param folderFileId      项目文件夹ID
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/add_folder")
    Observable<Void> addFolder(
        @Query("access_token") String accessToken,
        @Query("folder_name") String folderName,
        @Query("charge_user") String chargeUser,
        @Query("visibility") String visibility,
        @Query("groups") String groups,
        @Query("folder_file_id") String folderFileId,
        @Query("project_id") String projectId);

    /**
     * 创建项目文件夹
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderFileName    必填字段  项目文件夹名称
     * @param folderFileSort      项目文件夹序号(默认第一个)
     * @param folders      放入项目文件夹的项目ID（多个，相隔）
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/add_folder_file")
    Observable<Void> addFolderFile(
        @Query("access_token") String accessToken,
        @Query("folder_file_name") String folderFileName,
        @Query("folder_file_sort") int folderFileSort,
        @Query("folders") String folders,
        @Query("project_id") String projectId);

    /**
     * 新增项目成员
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  项目ID
     * @param members    必填字段  成员ID（多个，相隔）
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/add_folder_member")
    Observable<Void> addFolderMember(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("members") String members,
        @Query("project_id") String projectId);

    /**
     * 创建项目阶段
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  项目ID
     * @param folderStageName    必填字段  项目阶段名字
     * @param sort      阶段次序（默认排在第一个）
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/add_folder_stage")
    Observable<Void> addFolderStage(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("folder_stage_name") String folderStageName,
        @Query("sort") int sort,
        @Query("project_id") String projectId);

    /**
     * 创建一个任务
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskName    必填字段  任务名称
     * @param parentId      母任务ID
     * @param describe      任务描述
     * @param deadTime      任务截止日期，yyyy-MM-dd形式
     * @param chargeUser      指定的任务负责人
     * @param members      指定的任务成员 (多个成员用逗号隔开)
     * @param folderId      指定的隶属项目
     * @param color      任务颜色 默认0：无颜色；1：蓝色；2：紫色；3：红色；4：橙色；5：黄色
     * @param postId      动态ID（创建任务时，如果需要某个动态的附件添加到任务中必传）
     * @param folderStageId      指定的隶属项目下的阶段ID
     * @param isStar      是否给任务标星（默认：0：否，1：是）
     * @param groups      指定任务群组
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/add_task")
    Observable<Void> addTask(
        @Query("access_token") String accessToken,
        @Query("task_name") String taskName,
        @Query("parent_id") String parentId,
        @Query("describe") String describe,
        @Query("dead_time") String deadTime,
        @Query("charge_user") String chargeUser,
        @Query("members") String members,
        @Query("folder_id") String folderId,
        @Query("color") int color,
        @Query("post_id") String postId,
        @Query("folder_stage_id") String folderStageId,
        @Query("is_star") Boolean isStar,
        @Query("groups") String groups,
        @Query("project_id") String projectId);

    /**
     * 增加一条评论
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务ID
     * @param message      评论内容
     * @param replyTopicId      回复哪条评论的ID
     * @param attachments      附件JSON字符串，请参照：{"Value":[{"OriginalFileName":"原文件名","FileName":"新文件名","FilePath":"文件路径","FileSize":大小(int),"AttachmentType":附件类型}}]} 文件类型： Other = 0, Picture = 1, Document = 2, Compress = 3
     **/
    @POST("v1/task/add_task_topic")
    Observable<Void> addTaskTopic(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("message") String message,
        @Query("reply_topic_id") String replyTopicId,
        @Query("attachments") String attachments);

    /**
     * 申请成为项目成员
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  项目ID
     * @param applyInfo    必填字段  申请成为成员的理由
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/apply_folder_member")
    Observable<Void> applyFolderMember(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("apply_info") String applyInfo,
        @Query("project_id") String projectId);

    /**
     * 删除项目
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  项目ID
     **/
    @POST("v1/task/delete_folder")
    Observable<Void> deleteFolder(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId);

    /**
     * 删除项目文件夹
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderFileId    必填字段  项目文件夹ID
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/delete_folder_file")
    Observable<Void> deleteFolderFile(
        @Query("access_token") String accessToken,
        @Query("folder_file_id") String folderFileId,
        @Query("project_id") String projectId);

    /**
     * 移除项目成员
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  项目ID
     * @param members      成员ID（多个，相隔）为空默认退出
     * @param removedFromTasks      是否同时退出项目下的任务（默认false）
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/Delete_Folder_Members")
    Observable<Void> DeleteFolderMembers(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("members") String members,
        @Query("removed_from_tasks") Boolean removedFromTasks,
        @Query("project_id") String projectId);

    /**
     * 删除项目阶段
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  项目ID
     * @param folderStageId    必填字段  项目阶段ID
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/delete_folder_stage")
    Observable<Void> deleteFolderStage(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("folder_stage_id") String folderStageId,
        @Query("project_id") String projectId);

    /**
     * 删除任务
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务ID
     * @param isSubtask      是否同时删除子任务
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/delete_task")
    Observable<Void> deleteTask(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("is_subtask") Boolean isSubtask,
        @Query("project_id") String projectId);

    /**
     * 删除任务评论
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务ID
     * @param topicId    必填字段  任务评论ID
     * @param deleteFile      是否同时删除文件（默认为false）
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/delete_task_topic")
    Observable<Void> deleteTaskTopic(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("topic_id") String topicId,
        @Query("delete_file") Boolean deleteFile,
        @Query("project_id") String projectId);

    /**
     * 复制项目
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  指定的项目id
     * @param isStage      是否复制项目阶段
     * @param isDescribe      是否复制项目描述
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/duplicate_folder")
    Observable<Void> duplicateFolder(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("is_stage") Boolean isStage,
        @Query("is_describe") Boolean isDescribe,
        @Query("project_id") String projectId);

    /**
     * 获取个人或网络下归档项目文件夹下项目
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param projectId      哪个网络， 不支持all
     **/
    @GET("v1/task/Get_Archived_Folders")
    Observable<Void> GetArchivedFolders(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId);

    /**
     * 获取个人或网络下文件夹和初层项目列表
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @GET("v1/task/get_first_level_folders_and_files_by_project_id")
    Observable<Void> getFirstLevelFoldersAndFilesByProjectId(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId);

    /**
     * 获取项目下的阶段
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  指定的项目id
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @GET("v1/task/get_folder_stages")
    Observable<Void> getFolderStages(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("project_id") String projectId);

    /**
     * 获取项目下任务列表
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  项目id
     * @param projectId      哪个网络（默认个人自由网络）
     * @param pageIndex      指定当前的页码（不指定页码返回所有）
     * @param pageSize      指定要返回的记录条数(默认值20，最大值100)
     * @param tFolderId      项目ID (folderID=1 表示获取未关联项目的任务列表)
     * @param stageId      项目阶段ID
     * @param filterType      过滤类型 默认1：我参与的任务；2：我负责的任务；3：我托付的任务；7：查看同事(与我协作的任务)；8:自己加星的任务
     * @param color      任务颜色 默认-1：全部；0：无颜色；1：蓝色；2：紫色；3：红色；4：橙色；5：黄色
     * @param status      筛选任务状态 默认0：进行中；1：已完成；-1：全部
     * @param tags      过滤任务标签 多个用,隔开
     * @param other      指定用户编号 查看其他同事的任务列表
     * @param classifys      任务所处分类默认全部
     * @param isTop      是否置顶
     * @param sort      任务排序 1：按首字母;2:按到期日期;3:按任务创建时间；4:按项目(查询结果结构有变化);5:任务负责人；7：按颜色；8:完成时间；9:进行中;10:最近更新
     * @param completeTime      查询的时间起始点，当sort为8时(查询时间到当前的) 格式(2015-06-10)
     * @param keywords      关键词模糊搜索
     **/
    @GET("v1/task/get_folder_task_list")
    Observable<Void> getFolderTaskList(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("project_id") String projectId,
        @Query("pageindex") long pageIndex,
        @Query("pagesize") int pageSize,
        @Query("t_folder_id") String tFolderId,
        @Query("stage_id") String stageId,
        @Query("filter_type") int filterType,
        @Query("color") int color,
        @Query("status") int status,
        @Query("tags") String tags,
        @Query("other") String other,
        @Query("classifys") String classifys,
        @Query("is_top") String isTop,
        @Query("sort") int sort,
        @Query("completeTime") int completeTime,
        @Query("keywords") String keywords);

    /**
     * 获取项目文件夹下的项目列表
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderFileId    必填字段  指定的文件夹id
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @GET("v1/task/get_folders_by_fileID")
    Observable<Void> getFoldersByFileid(
        @Query("access_token") String accessToken,
        @Query("folder_file_id") String folderFileId,
        @Query("project_id") String projectId);

    /**
     * 获取个人或网络下隐藏项目文件夹下项目
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param projectId      哪个网络， 不支持all
     **/
    @GET("v1/task/get_Hidden_folders")
    Observable<Void> getHiddenFolders(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId);

    /**
     * 获取用户置顶项目
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param projectId      哪个网络, 不支持传入all
     **/
    @GET("v1/task/get_subordinates")
    Observable<Void> getSubordinates(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId);

    /**
     * 获取任务的所有附件信息
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务ID
     **/
    @GET("v1/task/Get_Task_Attachments")
    Observable<Void> GetTaskAttachments(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId);

    /**
     * 获取任务详情
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务ID
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @GET("v1/task/get_task_detail")
    Observable<Void> getTaskDetail(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("project_id") String projectId);

    /**
     * 获取任务列表
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param projectId      哪个网络（默认个人自由网络）
     * @param pageIndex      指定当前的页码（不指定页码返回所有）
     * @param pageSize      指定要返回的记录条数(默认值20，最大值100)
     * @param tFolderId      项目ID (folderID=1 表示获取未关联项目的任务列表)
     * @param stageId      项目阶段ID
     * @param filterType      过滤类型 默认1：我参与的任务；2：我负责的任务；3：我托付的任务；7：查看同事(与我协作的任务)；8:自己加星的任务
     * @param color      任务颜色 默认-1：全部；0：无颜色；1：蓝色；2：紫色；3：红色；4：橙色；5：黄色
     * @param status      筛选任务状态 默认0：进行中；1：已完成；-1：全部
     * @param tags      过滤任务标签 多个用,隔开
     * @param other      指定用户编号 查看其他同事的任务列表
     * @param classifys      任务所处分类默认全部
     * @param isTop      是否置顶
     * @param sort      任务排序 1：按首字母;2:按到期日期;3:按任务创建时间；4:按项目(查询结果结构有变化);5:任务负责人；7：按颜色；8:完成时间；9:进行中;10:最近更新
     * @param completeTime      查询的时间起始点，当sort为8时(查询时间到当前的) 格式(2015-06-10)
     * @param keywords      关键词模糊搜索
     **/
    @GET("v1/task/get_task_list")
    Observable<Void> getTaskList(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId,
        @Query("pageindex") long pageIndex,
        @Query("pagesize") int pageSize,
        @Query("t_folder_id") String tFolderId,
        @Query("stage_id") String stageId,
        @Query("filter_type") int filterType,
        @Query("color") int color,
        @Query("status") int status,
        @Query("tags") String tags,
        @Query("other") String other,
        @Query("classifys") String classifys,
        @Query("is_top") String isTop,
        @Query("sort") int sort,
        @Query("completeTime") int completeTime,
        @Query("keywords") String keywords);

    /**
     * 获取任务日志
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param pageId    必填字段  指定当前的页码, 从第一页开始
     * @param pageSize    必填字段  指定要返回的记录条数
     * @param taskId    必填字段  任务ID
     **/
    @GET("v1/task/get_task_Log")
    Observable<Void> getTaskLog(
        @Query("access_token") String accessToken,
        @Query("page_id") int pageId,
        @Query("page_size") int pageSize,
        @Query("task_id") String taskId);

    /**
     * 获取任务评论
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务ID
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @GET("v1/task/get_task_topics")
    Observable<Void> getTaskTopics(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("project_id") String projectId);

    /**
     * 根据传入的筛选条件查询任务的数量
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param status    必填字段  与任务是否完成相关的条件  All = -1 所有任务, Incomplete = 0 未完成, Complete = 1 已经完成
     * @param filterType    必填字段  与协作相关的条件 Participate = 1 我参与的 , Charge = 2 我负责, Release = 3（我托付的）, AllTask = 6 所有人物, WithMe = 7 与我协作的, MeTask = 9 包含 我参与、我负责、我托付 
     * @param classify    必填字段  与任务调度有关的条件 All = -1 不进行过滤，Now = 1 今天要做的， Will = 2 最近要做, After = 3 以后再说
     * @param color    必填字段  任务颜色 All = -1 全部颜色 不进行过滤，None = 0 无颜色， Purple = 1, Blue = 2, Yellow = 3, Orange = 4, Red = 5
     * @param star      是否有星标，为false则包含所有类别
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @GET("v1/task/get_tasks_count")
    Observable<Void> getTasksCount(
        @Query("access_token") String accessToken,
        @Query("status") Boolean status,
        @Query("filter_type") String filterType,
        @Query("classify") String classify,
        @Query("color") String color,
        @Query("star") Boolean star,
        @Query("project_id") String projectId);

    /**
     * 获取用户置顶项目
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     **/
    @GET("v1/task/get_sticky_folders")
    Observable<Void> getStickyFolders(
        @Query("access_token") String accessToken);

    /**
     * 获取协作成员
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param projectId      哪个网络, 不支持传入all
     **/
    @GET("v1/task/get_teamwork_member")
    Observable<Void> getTeamworkMember(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId);

    /**
     * 通过关键词搜索项目
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param keyword    必填字段  要搜索的关键词
     * @param accountIdOther      查询他人的任务, 他人ID
     **/
    @GET("v1/task/Search_Folders")
    Observable<Void> SearchFolders(
        @Query("access_token") String accessToken,
        @Query("keyword") String keyword,
        @Query("account_id_other") int accountIdOther);

    /**
     * 修改项目是否归档
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  指定的项目id
     * @param isArchived    必填字段  是否归档默认false
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/update_folder_archived_property")
    Observable<Void> updateFolderArchivedProperty(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("is_archived") Boolean isArchived,
        @Query("project_id") String projectId);

    /**
     * 修改项目基本属性(负责人,项目名,项目描述)全部修改或多选一
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  指定的项目id
     * @param chargeUser      负责人ID
     * @param folderName      项目名
     * @param describe      项目描述
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/update_folder_detail")
    Observable<Void> updateFolderDetail(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("charge_user") String chargeUser,
        @Query("folder_name") String folderName,
        @Query("describe") String describe,
        @Query("project_id") String projectId);

    /**
     * 修改项目文件夹
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderFileId    必填字段  项目文件夹ID
     * @param folderFileSort      项目文件夹序号
     * @param folderFileName      项目文件夹名称
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/update_folder_file")
    Observable<Void> updateFolderFile(
        @Query("access_token") String accessToken,
        @Query("folder_file_id") String folderFileId,
        @Query("folder_file_sort") int folderFileSort,
        @Query("folder_file_name") String folderFileName,
        @Query("project_id") String projectId);

    /**
     * 项目隐藏
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  项目ID
     * @param isHidden    必填字段  是否隐藏(默认false)
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/Update_Folder_IsHidden_Property")
    Observable<Void> UpdateFolderIshiddenProperty(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("is_hidden") Boolean isHidden,
        @Query("project_id") String projectId);

    /**
     * 项目置顶
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  项目ID
     * @param isTop    必填字段  是否置顶(默认false)
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/Update_Folder_IsTop_Property")
    Observable<Void> UpdateFolderIstopProperty(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("is_top") Boolean isTop,
        @Query("project_id") String projectId);

    /**
     * 设置项目成员为管理员
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  项目ID
     * @param changeUser    必填字段  项目ID
     * @param isAdmin    必填字段  是否设置为管理员(默认false)
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/update_folder_member_admin")
    Observable<Void> updateFolderMemberAdmin(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("change_user") String changeUser,
        @Query("is_admin") Boolean isAdmin,
        @Query("project_id") String projectId);

    /**
     * 项目标星
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  项目ID
     * @param isStar    必填字段  是否标志为star（默认false）
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/update_folder_member_star")
    Observable<Void> updateFolderMemberStar(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("is_star") Boolean isStar,
        @Query("project_id") String projectId);

    /**
     * 修改项目阶段顺序或名字
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  项目ID
     * @param folderStageId    必填字段  项目阶段ID
     * @param folderStageName    必填字段  新项目阶段名字
     * @param folderStageSort    必填字段  新项目阶段顺序
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/update_folder_stage")
    Observable<Void> updateFolderStage(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("folder_stage_id") String folderStageId,
        @Query("folder_stage_name") String folderStageName,
        @Query("folder_stage_sort") String folderStageSort,
        @Query("project_id") String projectId);

    /**
     * 修改项目可见性
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param folderId    必填字段  指定的项目id
     * @param visibility    必填字段  0:私密成员可见，1：群组公开,2全公司公开
     * @param groups      群组公开时群组ID（多个，相隔）
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/Update_Folder_Visibility_Property")
    Observable<Void> UpdateFolderVisibilityProperty(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("visibility") int visibility,
        @Query("groups") String groups,
        @Query("project_id") String projectId);

    /**
     * 更新任务负责人
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务id
     * @param newCharger    必填字段  新负责人的ID
     **/
    @POST("v1/task/update_task_charger_property")
    Observable<Void> updateTaskChargerProperty(
        @Query("access_token") String accessToken,
        @Query("task_id") int taskId,
        @Query("new_charger") int newCharger);

    /**
     * 更新任务截止日期
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务id
     * @param deadline    必填字段  截止日期
     * @param includeSubTasks    必填字段  是否包含子任务
     **/
    @POST("v1/task/update_task_deadline")
    Observable<Void> updateTaskDeadline(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("deadline") String deadline,
        @Query("include_sub_tasks") Boolean includeSubTasks);

    /**
     * /doc/task/update_task_description.html
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务id
     * @param description    必填字段  任务描述
     **/
    @POST("v1/task/update_task_description")
    Observable<Void> updateTaskDescription(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("description") String description);

    /**
     * 修改任务详情（包括字段如下）
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务ID
     * @param taskName      任务名称
     * @param describe      任务描述
     * @param parentId      母任务ID
     * @param folderId      项目ID
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/update_task_detail")
    Observable<Void> updateTaskDetail(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("task_name") String taskName,
        @Query("describe") String describe,
        @Query("parent_id") String parentId,
        @Query("folder_id") String folderId,
        @Query("project_id") String projectId);

    /**
     * 更新任务所在的项目
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务id
     * @param folderId    必填字段  项目ID
     **/
    @POST("v1/task/update_task_folderID")
    Observable<Void> updateTaskFolderid(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("folder_id") String folderId);

    /**
     * 是否锁定任务
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务ID
     * @param isLock      是否锁定任务（默认false）
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/update_task_locked")
    Observable<Void> updateTaskLocked(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("is_lock") Boolean isLock,
        @Query("project_id") String projectId);

    /**
     * 修改任务分类（待分配，现在要做等）
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务ID
     * @param classify    必填字段  1：现在要做，2：将要做，3：以后再说
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/update_task_member_classify")
    Observable<Void> updateTaskMemberClassify(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("classify") int classify,
        @Query("project_id") String projectId);

    /**
     * 修改任务颜色
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务ID
     * @param color    必填字段  任务颜色 默认0：无颜色；1：蓝色；2：紫色；3：红色；4：橙色；5：黄色
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/update_task_member_color")
    Observable<Void> updateTaskMemberColor(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("color") int color,
        @Query("project_id") String projectId);

    /**
     * 修改任务是否接收提醒
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务ID
     * @param isNotice    必填字段  是否接收提醒（默认false）
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/update_task_member_notice")
    Observable<Void> updateTaskMemberNotice(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("is_notice") Boolean isNotice,
        @Query("project_id") String projectId);

    /**
     * 任务标星
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务ID
     * @param isStar    必填字段  是否标星（默认false）
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/update_task_member_star")
    Observable<Void> updateTaskMemberStar(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("is_star") Boolean isStar,
        @Query("project_id") String projectId);

    /**
     * 更新任务名称
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务id
     * @param taskName    必填字段  任务名称
     **/
    @POST("v1/task/update_task_name")
    Observable<Void> updateTaskName(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("task_name") String taskName);

    /**
     * 更新母任务
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务id
     * @param parentId    必填字段  母任务ID
     **/
    @POST("v1/task/update_task_parentID")
    Observable<Void> updateTaskParentid(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("parent_id") String parentId);

    /**
     * 修改任务所处项目阶段
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务ID
     * @param folderStageId    必填字段  项目阶段ID
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/update_task_stage")
    Observable<Void> updateTaskStage(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("folder_stage_id") String folderStageId,
        @Query("project_id") String projectId);

    /**
     * 是否标记任务完成
     *
     * @param accessToken    必填字段  当前登录用户访问令牌
     * @param taskId    必填字段  任务ID
     * @param status    必填字段  任务完成状态（0：未完成，1：完成）
     * @param projectId      哪个网络（默认个人自由网络）
     **/
    @POST("v1/task/update_task_status")
    Observable<Void> updateTaskStatus(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("status") int status,
        @Query("project_id") String projectId);
}
