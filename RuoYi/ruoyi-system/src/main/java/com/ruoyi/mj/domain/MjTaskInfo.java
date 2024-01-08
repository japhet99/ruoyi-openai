package com.ruoyi.mj.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * midjourney 绘画任务对象 mj_task_info
 * 
 * @author ruoyi
 * @date 2023-09-21
 */
@Data
public class MjTaskInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 公司id */
    @Excel(name = "公司id")
    private Long companyId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 任务id */
    @Excel(name = "任务id")
    private String taskId;

    /** 魔咒 */
    @Excel(name = "魔咒")
    private String prompt;

    /** 生成的url */
    @Excel(name = "生成的url")
    private String taskUrl;

    /** 0 - 普通 1 变换 */
    @Excel(name = "0 - 普通 1 变换")
    private Integer type;

    /** 父亲id */
    @Excel(name = "父亲id")
    private String parentId;

    /** 父亲照片 */
    @Excel(name = "父亲照片")
    private String parentPhoto;

    /** 父亲索引 */
    @Excel(name = "父亲索引")
    private Integer parentIndex;

    /** state */
    @Excel(name = "state")
    private String state;

    /** action */
    @Excel(name = "action")
    private String action;

    /** 任务状态 0 -SUBMITTED 已提交,1-IN_PROGRESS 执行中,2-SUCCESS 完成,-1 -失败 */
    @Excel(name = "任务状态 0 -SUBMITTED 已提交,1-IN_PROGRESS 执行中,2-SUCCESS 完成,3-delete 删除 -1 -失败 ")
    private Integer status;

    /** 任务完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishTime;


}
