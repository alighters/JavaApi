package com.mingdao.data.net.task;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ITaskService {
    /**
     * 获取用户置顶项目
     *
     * @param accessToken   当前登录用户访问令牌
     **/
    @GET("/v1/task/get_top_folders")
    Observable<Void> getTopFolders(
        @Query("access_token") String accessToken);

    /**
     * 获取个人或网络下文件夹和初层项目列表
     *
     * @param accessToken   当前登录用户访问令牌
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @GET("/v1/task/get_main_folders")
    Observable<Void> getMainFolders(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId);

    /**
     * 获取项目文件夹下的项目列表
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderFileId   指定的文件夹id
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @GET("/v1/task/get_file_folders")
    Observable<Void> getFileFolders(
        @Query("access_token") String accessToken,
        @Query("folder_file_id") String folderFileId,
        @Query("project_id") String projectId);

    /**
     * 创建项目
     *
     * @param accessToken   当前登录用户访问令牌
     * @param name   项目名称
     * @param chargeUser   项目负责人 默认当前登录用户
     * @param deadTime   项目截止日期
     * @param isStar   是否给项目标星（默认：false）
     * @param members   项目成员ID多个以，相隔
     * @param admins   项目管理员ID多个以，相隔
     * @param visibility   项目可见性 0私密 1公开仅群组 2全公司(默认0)
     * @param groups   当项目可见性为公开群组时群组ID（多个群组已，相隔）
     * @param folderFileId   项目文件夹ID
     * @param isTop   是否置顶（默认：false）
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/add_folder")
    Observable<Void> addFolder(
        @Query("access_token") String accessToken,
        @Query("name") String name,
        @Query("charge_user") String chargeUser,
        @Query("dead_time") int deadTime,
        @Query("is_star") Boolean isStar,
        @Query("members") String members,
        @Query("admins") String admins,
        @Query("visibility") String visibility,
        @Query("groups") String groups,
        @Query("folder_file_id") String folderFileId,
        @Query("is_top") Boolean isTop,
        @Query("project_id") String projectId);

    /**
     * 删除项目
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   项目ID
     **/
    @POST("/v1/task/delete_folder")
    Observable<Void> deleteFolder(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId);

    /**
     * 复制项目
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   指定的项目id
     * @param isStage   是否复制项目阶段
     * @param isDescribe   是否复制项目描述
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/duplicate_folder")
    Observable<Void> duplicateFolder(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("is_stage") Boolean isStage,
        @Query("is_describe") Boolean isDescribe,
        @Query("project_id") String projectId);

    /**
     * 修改项目是否归档
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   指定的项目id
     * @param isArchived   是否归档默认false
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_folder_archived")
    Observable<Void> updateFolderArchived(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("is_archived") Boolean isArchived,
        @Query("project_id") String projectId);

    /**
     * 修改项目可见性
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   指定的项目id
     * @param visibility   0:私密成员可见，1：群组公开,2全公司公开
     * @param groups   群组公开时群组ID（多个，相隔）
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_folder_visibility")
    Observable<Void> updateFolderVisibility(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("visibility") int visibility,
        @Query("groups") String groups,
        @Query("project_id") String projectId);

    /**
     * 修改项目基本属性(负责人,项目名,项目描述)全部修改或多选一
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   指定的项目id
     * @param changeUser   负责人ID
     * @param folderName   项目名
     * @param describe   项目描述
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_folder_detail")
    Observable<Void> updateFolderDetail(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("change_user") String changeUser,
        @Query("folder_name") String folderName,
        @Query("describe") String describe,
        @Query("project_id") String projectId);

    /**
     * 获取项目下的阶段
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   指定的项目id
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @GET("/v1/task/get_folder_stages")
    Observable<Void> getFolderStages(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("project_id") String projectId);

    /**
     * 创建项目阶段
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   项目ID
     * @param folderStageName   项目阶段名字
     * @param sort   阶段次序（默认排在第一个）
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/add_folder_stage")
    Observable<Void> addFolderStage(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("folder_stage_name") String folderStageName,
        @Query("sort") int sort,
        @Query("project_id") String projectId);

    /**
     * 删除项目阶段
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   项目ID
     * @param folderStageId   项目阶段ID
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/delete_folder_stage")
    Observable<Void> deleteFolderStage(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("folder_stage_id") String folderStageId,
        @Query("project_id") String projectId);

    /**
     * 修改项目阶段顺序或名字
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   项目ID
     * @param folderStageId   项目阶段ID
     * @param folderStageName   新项目阶段名字
     * @param folderStageSort   新项目阶段顺序
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_folder_stage")
    Observable<Void> updateFolderStage(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("folder_stage_id") String folderStageId,
        @Query("folder_stage_name") String folderStageName,
        @Query("folder_stage_sort") String folderStageSort,
        @Query("project_id") String projectId);

    /**
     * 申请成为项目成员
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   项目ID
     * @param applyInfo   申请成为成员的理由
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/apply_folder_member")
    Observable<Void> applyFolderMember(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("apply_info") String applyInfo,
        @Query("project_id") String projectId);

    /**
     * 新增项目成员
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   项目ID
     * @param members   成员ID（多个，相隔）
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/add_folder_member")
    Observable<Void> addFolderMember(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("members") String members,
        @Query("project_id") String projectId);

    /**
     * 移除或退出项目
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   项目ID
     * @param members   成员ID（多个，相隔）为空默认退出
     * @param isRemoveTask   是否同时退出项目下的任务（默认false）
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/delete_folder_member")
    Observable<Void> deleteFolderMember(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("members") String members,
        @Query("is_remove_task") Boolean isRemoveTask,
        @Query("project_id") String projectId);

    /**
     * 项目标星
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   项目ID
     * @param isStar   是否标星（默认false）
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_folder_membe_star")
    Observable<Void> updateFolderMembeStar(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("is_star") Boolean isStar,
        @Query("project_id") String projectId);

    /**
     * 项目置顶
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   项目ID
     * @param isTop   是否置顶(默认false)
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_folder_member_top")
    Observable<Void> updateFolderMemberTop(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("is_top") Boolean isTop,
        @Query("project_id") String projectId);

    /**
     * 项目隐藏
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   项目ID
     * @param isHidden   是否隐藏(默认false)
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_folder_member_hidden")
    Observable<Void> updateFolderMemberHidden(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("is_hidden") Boolean isHidden,
        @Query("project_id") String projectId);

    /**
     * 设置项目成员为管理员
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   项目ID
     * @param changeUser   项目ID
     * @param isAdmin   是否设置为管理员(默认false)
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_folder_member_admin")
    Observable<Void> updateFolderMemberAdmin(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("change_user") String changeUser,
        @Query("is_admin") Boolean isAdmin,
        @Query("project_id") String projectId);

    /**
     * 创建项目文件夹
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderFileName   项目文件夹名称
     * @param folderFileSort   项目文件夹序号(默认第一个)
     * @param folders   放入项目文件夹的项目ID（多个，相隔）
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/add_folder_file")
    Observable<Void> addFolderFile(
        @Query("access_token") String accessToken,
        @Query("folder_file_name") String folderFileName,
        @Query("folder_file_sort") int folderFileSort,
        @Query("folders") String folders,
        @Query("project_id") String projectId);

    /**
     * 删除项目文件夹
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderFileId   项目文件夹ID
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/delete_folder_file")
    Observable<Void> deleteFolderFile(
        @Query("access_token") String accessToken,
        @Query("folder_file_id") String folderFileId,
        @Query("project_id") String projectId);

    /**
     * 修改项目文件夹
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderFileId   项目文件夹ID
     * @param folderFileSort   项目文件夹序号
     * @param folderFileName   项目文件夹名称
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_folder_file")
    Observable<Void> updateFolderFile(
        @Query("access_token") String accessToken,
        @Query("folder_file_id") String folderFileId,
        @Query("folder_file_sort") int folderFileSort,
        @Query("folder_file_name") String folderFileName,
        @Query("project_id") String projectId);

    /**
     * 获取任务列表
     *
     * @param accessToken   当前登录用户访问令牌
     * @param projectId   哪个网络（默认个人自由网络）
     * @param pageindex   指定当前的页码（不指定页码返回所有）
     * @param pagesize   指定要返回的记录条数(默认值20，最大值100)
     * @param tFolderId   项目ID (folderID=1 表示获取未关联项目的任务列表)
     * @param stageId   项目阶段ID
     * @param filterType   过滤类型 默认1：我参与的任务；2：我负责的任务；3：我托付的任务；7：查看同事(与我协作的任务)；8:自己加星的任务
     * @param color   任务颜色 默认-1：全部；0：无颜色；1：蓝色；2：紫色；3：红色；4：橙色；5：黄色
     * @param status   筛选任务状态 默认0：进行中；1：已完成；-1：全部
     * @param tags   过滤任务标签 多个用,隔开
     * @param other   指定用户编号 查看其他同事的任务列表
     * @param classifys   任务所处分类默认全部
     * @param isTop   是否置顶
     * @param sort   任务排序 1：按首字母;2:按到期日期;3:按任务创建时间；4:按项目(查询结果结构有变化);5:任务负责人；7：按颜色；8:完成时间；9:进行中;10:最近更新
     * @param completeTime   查询的时间起始点，当sort为8时(查询时间到当前的) 格式(2015-06-10)
     * @param keywords   关键词模糊搜索
     **/
    @GET("/v1/task/get_task_list")
    Observable<Void> getTaskList(
        @Query("access_token") String accessToken,
        @Query("project_id") String projectId,
        @Query("pageindex") long pageindex,
        @Query("pagesize") int pagesize,
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
     * 获取项目下任务列表
     *
     * @param accessToken   当前登录用户访问令牌
     * @param folderId   项目id
     * @param projectId   哪个网络（默认个人自由网络）
     * @param pageindex   指定当前的页码（不指定页码返回所有）
     * @param pagesize   指定要返回的记录条数(默认值20，最大值100)
     * @param tFolderId   项目ID (folderID=1 表示获取未关联项目的任务列表)
     * @param stageId   项目阶段ID
     * @param filterType   过滤类型 默认1：我参与的任务；2：我负责的任务；3：我托付的任务；7：查看同事(与我协作的任务)；8:自己加星的任务
     * @param color   任务颜色 默认-1：全部；0：无颜色；1：蓝色；2：紫色；3：红色；4：橙色；5：黄色
     * @param status   筛选任务状态 默认0：进行中；1：已完成；-1：全部
     * @param tags   过滤任务标签 多个用,隔开
     * @param other   指定用户编号 查看其他同事的任务列表
     * @param classifys   任务所处分类默认全部
     * @param isTop   是否置顶
     * @param sort   任务排序 1：按首字母;2:按到期日期;3:按任务创建时间；4:按项目(查询结果结构有变化);5:任务负责人；7：按颜色；8:完成时间；9:进行中;10:最近更新
     * @param completeTime   查询的时间起始点，当sort为8时(查询时间到当前的) 格式(2015-06-10)
     * @param keywords   关键词模糊搜索
     **/
    @GET("/v1/task/get_folder_task_list")
    Observable<Void> getFolderTaskList(
        @Query("access_token") String accessToken,
        @Query("folder_id") String folderId,
        @Query("project_id") String projectId,
        @Query("pageindex") long pageindex,
        @Query("pagesize") int pagesize,
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
     * 获取任务详情
     *
     * @param accessToken   当前登录用户访问令牌
     * @param taskId   任务ID
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @GET("/v1/task/get_task_detail")
    Observable<Void> getTaskDetail(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("project_id") String projectId);

    /**
     * 创建一个任务
     *
     * @param accessToken   当前登录用户访问令牌
     * @param taskName   任务名称
     * @param parentId   母任务ID
     * @param describe   任务描述
     * @param deadTime   任务截止日期，yyyy-MM-dd形式
     * @param chargeUser   指定的任务负责人
     * @param members   指定的任务成员 (多个成员用逗号隔开)
     * @param folderId   指定的隶属项目
     * @param color   任务颜色 默认0：无颜色；1：蓝色；2：紫色；3：红色；4：橙色；5：黄色
     * @param postId   动态ID（创建任务时，如果需要某个动态的附件添加到任务中必传）
     * @param folderStageId   指定的隶属项目下的阶段ID
     * @param isStar   是否给任务标星（默认：0：否，1：是）
     * @param groups   指定任务群组
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/add_task")
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
     * 删除任务
     *
     * @param accessToken   当前登录用户访问令牌
     * @param taskId   任务ID
     * @param isSubtask   是否同时删除子任务
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/delete_task")
    Observable<Void> deleteTask(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("is_subtask") Boolean isSubtask,
        @Query("project_id") String projectId);

    /**
     * 修改任务详情（包括字段如下）
     *
     * @param accessToken   当前登录用户访问令牌
     * @param taskId   任务ID
     * @param taskName   任务名称
     * @param describe   任务描述
     * @param parentId   母任务ID
     * @param folderId   项目ID
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_task_detail")
    Observable<Void> updateTaskDetail(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("task_name") String taskName,
        @Query("describe") String describe,
        @Query("parent_id") String parentId,
        @Query("folder_id") String folderId,
        @Query("project_id") String projectId);

    /**
     * 是否锁定任务
     *
     * @param accessToken   当前登录用户访问令牌
     * @param taskId   任务ID
     * @param isLock   是否锁定任务（默认false）
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_task_locked")
    Observable<Void> updateTaskLocked(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("is_lock") Boolean isLock,
        @Query("project_id") String projectId);

    /**
     * 是否标记任务完成
     *
     * @param accessToken   当前登录用户访问令牌
     * @param taskId   任务ID
     * @param status   任务完成状态（0：未完成，1：完成）
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_task_status")
    Observable<Void> updateTaskStatus(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("status") int status,
        @Query("project_id") String projectId);

    /**
     * 任务标星
     *
     * @param accessToken   当前登录用户访问令牌
     * @param taskId   任务ID
     * @param isStar   是否标星（默认false）
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_task_member_star")
    Observable<Void> updateTaskMemberStar(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("is_star") Boolean isStar,
        @Query("project_id") String projectId);

    /**
     * 修改任务颜色
     *
     * @param accessToken   当前登录用户访问令牌
     * @param taskId   任务ID
     * @param color   任务颜色 默认0：无颜色；1：蓝色；2：紫色；3：红色；4：橙色；5：黄色
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_task_member_color")
    Observable<Void> updateTaskMemberColor(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("color") int color,
        @Query("project_id") String projectId);

    /**
     * 修改任务是否接收提醒
     *
     * @param accessToken   当前登录用户访问令牌
     * @param taskId   任务ID
     * @param isNotice   是否接收提醒（默认false）
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_task_member_notice")
    Observable<Void> updateTaskMemberNotice(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("is_notice") Boolean isNotice,
        @Query("project_id") String projectId);

    /**
     * 修改任务分类（待分配，现在要做等）
     *
     * @param accessToken   当前登录用户访问令牌
     * @param taskId   任务ID
     * @param classify   1：现在要做，2：将要做，3：以后再说
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_task_member_classify")
    Observable<Void> updateTaskMemberClassify(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("classify") int classify,
        @Query("project_id") String projectId);

    /**
     * 修改任务所处项目阶段
     *
     * @param accessToken   当前登录用户访问令牌
     * @param taskId   任务ID
     * @param folderStageId   项目阶段ID
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/update_task_stage")
    Observable<Void> updateTaskStage(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("folder_stage_id") String folderStageId,
        @Query("project_id") String projectId);

    /**
     * 获取任务评论
     *
     * @param accessToken   当前登录用户访问令牌
     * @param taskId   任务ID
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @GET("/v1/task/get_task_topics")
    Observable<Void> getTaskTopics(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("project_id") String projectId);

    /**
     * 新增任务评论
     *
     * @param accessToken   当前登录用户访问令牌
     * @param taskId   任务ID
     * @param message   评论内容
     * @param fileType   评论类型（1：图片，2：文档）默认0普通
     * @param replyTopicId   回复哪条评论的ID
     * @param attachments   附件JSON字符串
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/add_task_topic")
    Observable<Void> addTaskTopic(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("message") String message,
        @Query("file_type") int fileType,
        @Query("reply_topic_id") String replyTopicId,
        @Query("attachments") String attachments,
        @Query("project_id") String projectId);

    /**
     * 删除任务评论
     *
     * @param accessToken   当前登录用户访问令牌
     * @param taskId   任务ID
     * @param topicId   任务评论ID
     * @param deleteFile   是否同时删除文件（默认为false）
     * @param projectId   哪个网络（默认个人自由网络）
     **/
    @POST("/v1/task/delete_task_topic")
    Observable<Void> deleteTaskTopic(
        @Query("access_token") String accessToken,
        @Query("task_id") String taskId,
        @Query("topic_id") String topicId,
        @Query("delete_file") Boolean deleteFile,
        @Query("project_id") String projectId);
}
