package com.mingdao.data.net.calendar;



import retrofit2.http.GET;

import retrofit2.http.POST;

import retrofit2.http.Query;

import rx.Observable;



public interface ICalendarService {

    /**

     * 获取当前登录用户待办日程列表

     *

     * @param accessToken   当前登录用户访问令牌

     * @param rsscal   是否订阅待办日程。0表不订阅；以列表形式返回，1表示订阅，直接返回订阅链接url

     * @param groupTime   是否以日程的起始时间分组显示。0表不；以列表形式返回，1表示分组，以分组列表显示

     * @param accountIds   用户编号 查看其他同事的日程，多个以逗号相隔

     * @param isWorkCalendar   是否查看工作日程

     * @param isTaskCalendar   是否查看任务日程

     * @param isPrivateCalendar   是否查看私密日程

     * @param categorys   用户日程分类，多个以逗号相隔

     **/

    @GET("/v1/calendar/get_calendar_to_do")

    Observable<Void> getCalendarToDo(

        @Query("access_token") String accessToken,

        @Query("rsscal") Boolean rsscal,

        @Query("group_time") Boolean groupTime,

        @Query("account_ids") String accountIds,

        @Query("is_work_calendar") Boolean isWorkCalendar,

        @Query("is_task_calendar") Boolean isTaskCalendar,

        @Query("is_private_calendar") Boolean isPrivateCalendar,

        @Query("categorys") String categorys);



    /**

     * 获取当前登录用户某日/某周/某月 日程列表

     *

     * @param accessToken   当前登录用户访问令牌

     * @param date   日期字符串。默认值为今天。如：2013-05-05。

     * @param accountIds   用户编号 查看其他同事的日程，多个以逗号相隔

     * @param week   某年第几周数。默认值为当前日期周数。

     * @param year   日期年数字。默认值为当前年。如：2013。

     * @param isWorkCalendar   是否查看工作日程

     * @param isTaskCalendar   是否查看任务日程

     * @param isPrivateCalendar   是否查看私密日程

     * @param categorys   用户日程分类，多个以逗号相隔

     **/

    @GET("/v1/calendar/get_calendar_day_week_month")

    Observable<Void> getCalendarDayWeekMonth(

        @Query("access_token") String accessToken,

        @Query("date") String date,

        @Query("account_ids") String accountIds,

        @Query("week") String week,

        @Query("year") String year,

        @Query("is_work_calendar") Boolean isWorkCalendar,

        @Query("is_task_calendar") Boolean isTaskCalendar,

        @Query("is_private_calendar") Boolean isPrivateCalendar,

        @Query("categorys") String categorys);



    /**

     * 日程详情

     *

     * @param accessToken   当前登录用户访问令牌

     * @param calendarId   日程编号

     **/

    @GET("/v1/calendar/get_calendar_detail")

    Observable<Void> getCalendarDetail(

        @Query("access_token") String accessToken,

        @Query("calendar_id") String calendarId);



    /**

     * 获取登录用户未确认日程列表

     *

     * @param accessToken   当前登录用户访问令牌

     * @param pageindex   指定要返回的页数

     * @param pagesize   指定要返回的记录条数

     **/

    @GET("/v1/calendar/get_invite_calendars")

    Observable<Void> getInviteCalendars(

        @Query("access_token") String accessToken,

        @Query("pageindex") String pageindex,

        @Query("pagesize") String pagesize);



    /**

     * 获取用户所有分类

     *

     * @param accessToken   当前登录用户访问令牌

     **/

    @GET("/v1/calendar/get_user_all_cal_categories")

    Observable<Void> getUserAllCalCategories(

        @Query("access_token") String accessToken);



    /**

     * 获取冲突日程

     *

     * @param accessToken   当前登录用户访问令牌

     * @param calendarStartTime   日程开始时间

     * @param calendarEndTime   日程结束时间

     **/

    @GET("/v1/calendar/get_user_busy_calendar")

    Observable<Void> getUserBusyCalendar(

        @Query("access_token") String accessToken,

        @Query("calendar_start_time") String calendarStartTime,

        @Query("calendar_end_time") String calendarEndTime);



    /**

     * 创建一个新的日程

     *

     * @param accessToken   当前登录用户访问令牌

     * @param calendarName   日程主题

     * @param calendarStartTime   日程开始时间，精确到分。如：2013-05-05 10:25

     * @param calendarEndTime   日程结束时间，精确到分。如：2013-05-05 10:25

     * @param isAllDay   是否全天日程。0表示非全天，1表示全天 默认值0

     * @param calendarAddress   日程地点

     * @param calendarDescription   日程描述

     * @param calendarPrivate   是否私人日程。1表示私人，0表示非私人 默认值0

     * @param groupIds   私有日程分享群组 分享多群组用,隔开

     * @param calendarMemberIds   指定的日程成员 (多个成员用逗号隔开)。注：明道用户

     * @param calendarMemberEmails   指定的日程成员邮件 (多个成员用逗号隔开)。注：非明道用户

     * @param isRecur   是否为重复日程. 1:是 0:不是 默认值0

     * @param repeatFrequency   当is_recur为1 即为重复日程时该值必填，频率 1 表示Daily; 2 表示Weekly; 3 表示Monthly; 4 表示Yearly

     * @param repeatInterval   当is_recur为1 即为重复日程时该值选填，重复间隔 默认值 1。

     * @param repeatWeekDay   当 frequency=2 该值必填，周几重复 1:周一 2:周二 3:周三 4:周四 5:周五 6 周六 7:周日。多选用,隔开

     * @param repeatRecurCount   当 is_recur为1即为重复日程时该值选填，周几重复 1:周一 2:周二 3:周三 4:周四 5:周五 6 周六 7:周日。多选用|隔开

     * @param untilDate   当 is_recur为1 该值选填，结束日期 如果recur_count为0且until_date为null,则为永久重复

     * @param calendarRemindType   提醒类型

     * @param calendarRemindTime   提醒时间

     * @param calendarCategoryId   日程分类id

     **/

    @POST("/v1/calendar/create_calendar")

    Observable<Void> createCalendar(

        @Query("access_token") String accessToken,

        @Query("calendar_name") String calendarName,

        @Query("calendar_start_time") String calendarStartTime,

        @Query("calendar_end_time") String calendarEndTime,

        @Query("is_all_day") Boolean isAllDay,

        @Query("calendar_address") String calendarAddress,

        @Query("calendar_description") String calendarDescription,

        @Query("calendar_private") Boolean calendarPrivate,

        @Query("group_ids") String groupIds,

        @Query("calendar_member_ids") String calendarMemberIds,

        @Query("calendar_member_emails") String calendarMemberEmails,

        @Query("is_recur") Boolean isRecur,

        @Query("repeat_frequency") int repeatFrequency,

        @Query("repeat_interval") int repeatInterval,

        @Query("repeat_week_day") String repeatWeekDay,

        @Query("repeat_recur_count") int repeatRecurCount,

        @Query("until_date") String untilDate,

        @Query("calendar_remind_type") int calendarRemindType,

        @Query("calendar_remind_time") int calendarRemindTime,

        @Query("calendar_category_id") String calendarCategoryId);



    /**

     * 根据日程编号修改日程

     *

     * @param accessToken   当前登录用户访问令牌

     * @param calendarId   日程编号

     * @param calendarName   日程主题

     * @param calendarStartTime   日程开始时间，精确到分。如：2013-05-05 10:25

     * @param calendarEndTime   日程结束时间，精确到分。如：2013-05-05 10:25

     * @param isAllDay   是否全天日程。0表示非全天，1表示全天 默认值0

     * @param calendarAddress   日程地点

     * @param calendarDescription   日程描述

     * @param calendarPrivate   是否私人日程。1表示私人，0表示非私人 默认值0

     * @param groupIds   私有日程分享群组 分享多群组用,隔开

     * @param calendarMemberIds   指定的日程成员 (多个成员用逗号隔开)。注：明道用户

     * @param calendarMemberEmails   指定的日程成员邮件 (多个成员用逗号隔开)。注：非明道用户

     * @param isRecur   是否为重复日程. 1:是 0:不是 默认值0

     * @param repeatFrequency   当is_recur为1 即为重复日程时该值必填，频率 1 表示Daily; 2 表示Weekly; 3 表示Monthly; 4 表示Yearly

     * @param repeatInterval   当is_recur为1 即为重复日程时该值选填，重复间隔 默认值 1。

     * @param repeatWeekDay   当 frequency=2 该值必填，周几重复 1:周一 2:周二 3:周三 4:周四 5:周五 6 周六 7:周日。多选用,隔开

     * @param repeatRecurCount   当 is_recur为1即为重复日程时该值选填，周几重复 1:周一 2:周二 3:周三 4:周四 5:周五 6 周六 7:周日。多选用|隔开

     * @param untilDate   当 is_recur为1 该值选填，结束日期 如果recur_count为0且until_date为null,则为永久重复

     * @param calendarRemindType   提醒类型

     * @param calendarRemindTime   提醒时间

     * @param calendarCategoryId   日程分类id

     **/

    @POST("/v1/calendar/edit_calendar")

    Observable<Void> editCalendar(

        @Query("access_token") String accessToken,

        @Query("calendar_id") String calendarId,

        @Query("calendar_name") String calendarName,

        @Query("calendar_start_time") String calendarStartTime,

        @Query("calendar_end_time") String calendarEndTime,

        @Query("is_all_day") Boolean isAllDay,

        @Query("calendar_address") String calendarAddress,

        @Query("calendar_description") String calendarDescription,

        @Query("calendar_private") Boolean calendarPrivate,

        @Query("group_ids") String groupIds,

        @Query("calendar_member_ids") String calendarMemberIds,

        @Query("calendar_member_emails") String calendarMemberEmails,

        @Query("is_recur") Boolean isRecur,

        @Query("repeat_frequency") int repeatFrequency,

        @Query("repeat_interval") int repeatInterval,

        @Query("repeat_week_day") String repeatWeekDay,

        @Query("repeat_recur_count") int repeatRecurCount,

        @Query("until_date") String untilDate,

        @Query("calendar_remind_type") int calendarRemindType,

        @Query("calendar_remind_time") int calendarRemindTime,

        @Query("calendar_category_id") int calendarCategoryId);



    /**

     * 确认/拒绝一个日程

     *

     * @param accessToken   当前登录用户访问令牌

     * @param calendarId   日程id

     * @param chooseType   join=1/ deny=2

     **/

    @POST("/v1/calendar/join_or_deny_calendar")

    Observable<Void> joinOrDenyCalendar(

        @Query("access_token") String accessToken,

        @Query("calendar_id") String calendarId,

        @Query("choose_type") int chooseType);



    /**

     * 退出一个日程

     *

     * @param accessToken   当前登录用户访问令牌

     * @param calendarId   日程id

     **/

    @POST("/v1/calendar/exit_calendar")

    Observable<Void> exitCalendar(

        @Query("access_token") String accessToken,

        @Query("calendar_id") String calendarId);



    /**

     * 重新邀请一个日程与会人员

     *

     * @param accessToken   当前登录用户访问令牌

     * @param calendarId   日程id

     * @param calendarAccountId   邀请人id

     * @param calendarAccountEmail   邀请人邮箱

     **/

    @POST("/v1/calendar/reinvite_calendar_member")

    Observable<Void> reinviteCalendarMember(

        @Query("access_token") String accessToken,

        @Query("calendar_id") String calendarId,

        @Query("calendar_account_id") String calendarAccountId,

        @Query("calendar_account_email") String calendarAccountEmail);



    /**

     * 删除日程

     *

     * @param accessToken   当前登录用户访问令牌

     * @param calendarId   日程id

     **/

    @POST("/v1/calendar/delete_calendar")

    Observable<Void> deleteCalendar(

        @Query("access_token") String accessToken,

        @Query("calendar_id") String calendarId);



    /**

     * 删除/添加/修改用户日程分类

     *

     * @param accessToken   当前登录用户访问令牌

     * @param calendarId   日程id

     * @param categoryName   分类名

     * @param color   分类颜色

     * @param categoryId    用户日程分类id

     * @param chooseType   操作类型 删除=0/添加=1/修改=2

     **/

    @POST("/v1/calendar/user_category_operate")

    Observable<Void> userCategoryOperate(

        @Query("access_token") String accessToken,

        @Query("calendar_id") String calendarId,

        @Query("category_name") String categoryName,

        @Query("color") String color,

        @Query("category_id") String categoryId,

        @Query("choose_type") String chooseType);



    /**

     * 更新日程提醒

     *

     * @param accessToken   当前登录用户访问令牌

     * @param calendarId   日程id

     * @param calendarRemindTime   提醒时间

     * @param calendarRemindType   提醒类型

     **/

    @POST("/v1/calendar/update_calendar_remind")

    Observable<Void> updateCalendarRemind(

        @Query("access_token") String accessToken,

        @Query("calendar_id") String calendarId,

        @Query("calendar_remind_time") int calendarRemindTime,

        @Query("calendar_remind_type") int calendarRemindType);

    /**
     * 获取当前登录用户待办日程列表
     *
     * @param accessToken   当前登录用户访问令牌
     * @param rsscal   是否订阅待办日程。0表不订阅；以列表形式返回，1表示订阅，直接返回订阅链接url
     * @param groupTime   是否以日程的起始时间分组显示。0表不；以列表形式返回，1表示分组，以分组列表显示
     * @param accountIds   用户编号 查看其他同事的日程，多个以逗号相隔
     * @param isWorkCalendar   是否查看工作日程
     * @param isTaskCalendar   是否查看任务日程
     * @param isPrivateCalendar   是否查看私密日程
     * @param categorys   用户日程分类，多个以逗号相隔
     **/
    @GET("/v1/calendar/get_calendar_to_do")
    Observable<Void> getCalendarToDo(
        @Query("access_token") String accessToken,
        @Query("rsscal") Boolean rsscal,
        @Query("group_time") Boolean groupTime,
        @Query("account_ids") String accountIds,
        @Query("is_work_calendar") Boolean isWorkCalendar,
        @Query("is_task_calendar") Boolean isTaskCalendar,
        @Query("is_private_calendar") Boolean isPrivateCalendar,
        @Query("categorys") String categorys);

    /**
     * 获取当前登录用户某日/某周/某月 日程列表
     *
     * @param accessToken   当前登录用户访问令牌
     * @param date   日期字符串。默认值为今天。如：2013-05-05。
     * @param accountIds   用户编号 查看其他同事的日程，多个以逗号相隔
     * @param week   某年第几周数。默认值为当前日期周数。
     * @param year   日期年数字。默认值为当前年。如：2013。
     * @param isWorkCalendar   是否查看工作日程
     * @param isTaskCalendar   是否查看任务日程
     * @param isPrivateCalendar   是否查看私密日程
     * @param categorys   用户日程分类，多个以逗号相隔
     **/
    @GET("/v1/calendar/get_calendar_day_week_month")
    Observable<Void> getCalendarDayWeekMonth(
        @Query("access_token") String accessToken,
        @Query("date") String date,
        @Query("account_ids") String accountIds,
        @Query("week") String week,
        @Query("year") String year,
        @Query("is_work_calendar") Boolean isWorkCalendar,
        @Query("is_task_calendar") Boolean isTaskCalendar,
        @Query("is_private_calendar") Boolean isPrivateCalendar,
        @Query("categorys") String categorys);

    /**
     * 日程详情
     *
     * @param accessToken   当前登录用户访问令牌
     * @param calendarId   日程编号
     **/
    @GET("/v1/calendar/get_calendar_detail")
    Observable<Void> getCalendarDetail(
        @Query("access_token") String accessToken,
        @Query("calendar_id") String calendarId);

    /**
     * 获取登录用户未确认日程列表
     *
     * @param accessToken   当前登录用户访问令牌
     * @param pageindex   指定要返回的页数
     * @param pagesize   指定要返回的记录条数
     **/
    @GET("/v1/calendar/get_invite_calendars")
    Observable<Void> getInviteCalendars(
        @Query("access_token") String accessToken,
        @Query("pageindex") String pageindex,
        @Query("pagesize") String pagesize);

    /**
     * 获取用户所有分类
     *
     * @param accessToken   当前登录用户访问令牌
     **/
    @GET("/v1/calendar/get_user_all_cal_categories")
    Observable<Void> getUserAllCalCategories(
        @Query("access_token") String accessToken);

    /**
     * 获取冲突日程
     *
     * @param accessToken   当前登录用户访问令牌
     * @param calendarStartTime   日程开始时间
     * @param calendarEndTime   日程结束时间
     **/
    @GET("/v1/calendar/get_user_busy_calendar")
    Observable<Void> getUserBusyCalendar(
        @Query("access_token") String accessToken,
        @Query("calendar_start_time") String calendarStartTime,
        @Query("calendar_end_time") String calendarEndTime);

    /**
     * 创建一个新的日程
     *
     * @param accessToken   当前登录用户访问令牌
     * @param calendarName   日程主题
     * @param calendarStartTime   日程开始时间，精确到分。如：2013-05-05 10:25
     * @param calendarEndTime   日程结束时间，精确到分。如：2013-05-05 10:25
     * @param isAllDay   是否全天日程。0表示非全天，1表示全天 默认值0
     * @param calendarAddress   日程地点
     * @param calendarDescription   日程描述
     * @param calendarPrivate   是否私人日程。1表示私人，0表示非私人 默认值0
     * @param groupIds   私有日程分享群组 分享多群组用,隔开
     * @param calendarMemberIds   指定的日程成员 (多个成员用逗号隔开)。注：明道用户
     * @param calendarMemberEmails   指定的日程成员邮件 (多个成员用逗号隔开)。注：非明道用户
     * @param isRecur   是否为重复日程. 1:是 0:不是 默认值0
     * @param repeatFrequency   当is_recur为1 即为重复日程时该值必填，频率 1 表示Daily; 2 表示Weekly; 3 表示Monthly; 4 表示Yearly
     * @param repeatInterval   当is_recur为1 即为重复日程时该值选填，重复间隔 默认值 1。
     * @param repeatWeekDay   当 frequency=2 该值必填，周几重复 1:周一 2:周二 3:周三 4:周四 5:周五 6 周六 7:周日。多选用,隔开
     * @param repeatRecurCount   当 is_recur为1即为重复日程时该值选填，周几重复 1:周一 2:周二 3:周三 4:周四 5:周五 6 周六 7:周日。多选用|隔开
     * @param untilDate   当 is_recur为1 该值选填，结束日期 如果recur_count为0且until_date为null,则为永久重复
     * @param calendarRemindType   提醒类型
     * @param calendarRemindTime   提醒时间
     * @param calendarCategoryId   日程分类id
     **/
    @POST("/v1/calendar/create_calendar")
    Observable<Void> createCalendar(
        @Query("access_token") String accessToken,
        @Query("calendar_name") String calendarName,
        @Query("calendar_start_time") String calendarStartTime,
        @Query("calendar_end_time") String calendarEndTime,
        @Query("is_all_day") Boolean isAllDay,
        @Query("calendar_address") String calendarAddress,
        @Query("calendar_description") String calendarDescription,
        @Query("calendar_private") Boolean calendarPrivate,
        @Query("group_ids") String groupIds,
        @Query("calendar_member_ids") String calendarMemberIds,
        @Query("calendar_member_emails") String calendarMemberEmails,
        @Query("is_recur") Boolean isRecur,
        @Query("repeat_frequency") int repeatFrequency,
        @Query("repeat_interval") int repeatInterval,
        @Query("repeat_week_day") String repeatWeekDay,
        @Query("repeat_recur_count") int repeatRecurCount,
        @Query("until_date") String untilDate,
        @Query("calendar_remind_type") int calendarRemindType,
        @Query("calendar_remind_time") int calendarRemindTime,
        @Query("calendar_category_id") String calendarCategoryId);

    /**
     * 根据日程编号修改日程
     *
     * @param accessToken   当前登录用户访问令牌
     * @param calendarId   日程编号
     * @param calendarName   日程主题
     * @param calendarStartTime   日程开始时间，精确到分。如：2013-05-05 10:25
     * @param calendarEndTime   日程结束时间，精确到分。如：2013-05-05 10:25
     * @param isAllDay   是否全天日程。0表示非全天，1表示全天 默认值0
     * @param calendarAddress   日程地点
     * @param calendarDescription   日程描述
     * @param calendarPrivate   是否私人日程。1表示私人，0表示非私人 默认值0
     * @param groupIds   私有日程分享群组 分享多群组用,隔开
     * @param calendarMemberIds   指定的日程成员 (多个成员用逗号隔开)。注：明道用户
     * @param calendarMemberEmails   指定的日程成员邮件 (多个成员用逗号隔开)。注：非明道用户
     * @param isRecur   是否为重复日程. 1:是 0:不是 默认值0
     * @param repeatFrequency   当is_recur为1 即为重复日程时该值必填，频率 1 表示Daily; 2 表示Weekly; 3 表示Monthly; 4 表示Yearly
     * @param repeatInterval   当is_recur为1 即为重复日程时该值选填，重复间隔 默认值 1。
     * @param repeatWeekDay   当 frequency=2 该值必填，周几重复 1:周一 2:周二 3:周三 4:周四 5:周五 6 周六 7:周日。多选用,隔开
     * @param repeatRecurCount   当 is_recur为1即为重复日程时该值选填，周几重复 1:周一 2:周二 3:周三 4:周四 5:周五 6 周六 7:周日。多选用|隔开
     * @param untilDate   当 is_recur为1 该值选填，结束日期 如果recur_count为0且until_date为null,则为永久重复
     * @param calendarRemindType   提醒类型
     * @param calendarRemindTime   提醒时间
     * @param calendarCategoryId   日程分类id
     **/
    @POST("/v1/calendar/edit_calendar")
    Observable<Void> editCalendar(
        @Query("access_token") String accessToken,
        @Query("calendar_id") String calendarId,
        @Query("calendar_name") String calendarName,
        @Query("calendar_start_time") String calendarStartTime,
        @Query("calendar_end_time") String calendarEndTime,
        @Query("is_all_day") Boolean isAllDay,
        @Query("calendar_address") String calendarAddress,
        @Query("calendar_description") String calendarDescription,
        @Query("calendar_private") Boolean calendarPrivate,
        @Query("group_ids") String groupIds,
        @Query("calendar_member_ids") String calendarMemberIds,
        @Query("calendar_member_emails") String calendarMemberEmails,
        @Query("is_recur") Boolean isRecur,
        @Query("repeat_frequency") int repeatFrequency,
        @Query("repeat_interval") int repeatInterval,
        @Query("repeat_week_day") String repeatWeekDay,
        @Query("repeat_recur_count") int repeatRecurCount,
        @Query("until_date") String untilDate,
        @Query("calendar_remind_type") int calendarRemindType,
        @Query("calendar_remind_time") int calendarRemindTime,
        @Query("calendar_category_id") int calendarCategoryId);

    /**
     * 确认/拒绝一个日程
     *
     * @param accessToken   当前登录用户访问令牌
     * @param calendarId   日程id
     * @param chooseType   join=1/ deny=2
     **/
    @POST("/v1/calendar/join_or_deny_calendar")
    Observable<Void> joinOrDenyCalendar(
        @Query("access_token") String accessToken,
        @Query("calendar_id") String calendarId,
        @Query("choose_type") int chooseType);

    /**
     * 退出一个日程
     *
     * @param accessToken   当前登录用户访问令牌
     * @param calendarId   日程id
     **/
    @POST("/v1/calendar/exit_calendar")
    Observable<Void> exitCalendar(
        @Query("access_token") String accessToken,
        @Query("calendar_id") String calendarId);

    /**
     * 重新邀请一个日程与会人员
     *
     * @param accessToken   当前登录用户访问令牌
     * @param calendarId   日程id
     * @param calendarAccountId   邀请人id
     * @param calendarAccountEmail   邀请人邮箱
     **/
    @POST("/v1/calendar/reinvite_calendar_member")
    Observable<Void> reinviteCalendarMember(
        @Query("access_token") String accessToken,
        @Query("calendar_id") String calendarId,
        @Query("calendar_account_id") String calendarAccountId,
        @Query("calendar_account_email") String calendarAccountEmail);

    /**
     * 删除日程
     *
     * @param accessToken   当前登录用户访问令牌
     * @param calendarId   日程id
     **/
    @POST("/v1/calendar/delete_calendar")
    Observable<Void> deleteCalendar(
        @Query("access_token") String accessToken,
        @Query("calendar_id") String calendarId);

    /**
     * 删除/添加/修改用户日程分类
     *
     * @param accessToken   当前登录用户访问令牌
     * @param calendarId   日程id
     * @param categoryName   分类名
     * @param color   分类颜色
     * @param categoryId    用户日程分类id
     * @param chooseType   操作类型 删除=0/添加=1/修改=2
     **/
    @POST("/v1/calendar/user_category_operate")
    Observable<Void> userCategoryOperate(
        @Query("access_token") String accessToken,
        @Query("calendar_id") String calendarId,
        @Query("category_name") String categoryName,
        @Query("color") String color,
        @Query("category_id") String categoryId,
        @Query("choose_type") String chooseType);

    /**
     * 更新日程提醒
     *
     * @param accessToken   当前登录用户访问令牌
     * @param calendarId   日程id
     * @param calendarRemindTime   提醒时间
     * @param calendarRemindType   提醒类型
     **/
    @POST("/v1/calendar/update_calendar_remind")
    Observable<Void> updateCalendarRemind(
        @Query("access_token") String accessToken,
        @Query("calendar_id") String calendarId,
        @Query("calendar_remind_time") int calendarRemindTime,
        @Query("calendar_remind_type") int calendarRemindType);
}

