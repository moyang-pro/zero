package com.moyang.zero.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.moyang.zero.common.enums.DelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@ApiModel(value="Template对象", description="用户信息表")
@Slf4j
public class Template{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createAt;

    @ApiModelProperty(value = "创建者emy账号")
    private String createBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateAt;

    @ApiModelProperty(value = "更新者 emy账号")
    private String updateBy;

    @ApiModelProperty(value = "删除标识 0 未删除 1已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "记录说明")
    private String remarks;

    /**
     *  创建新记录
     * @param emy 用户账号
     * @param remarks 记录描述
     */
    public void recordCreateInfo(String emy, String remarks){
        LocalDateTime now = LocalDateTime.now();
        this.createBy = emy;
        this.createAt = now;
        this.updateBy = emy;
        this.updateAt = now;
        this.remarks = remarks;
        log.info(emy + "在" + now + remarks);
    }

    /**
     *  创建新记录
     * @param emy 用户账号
     */
    public void recordCreateInfo(String emy){
        LocalDateTime now = LocalDateTime.now();
        this.createBy = emy;
        this.createAt = now;
        this.updateBy = emy;
        this.updateAt = now;
        this.remarks = emy + "在" + now + "创建新记录";
        log.info(remarks);
    }

    /**
     *  创建新记录
     * @param emy 用户账号
     * @param remarks 记录描述
     */
    public void recordUpdateInfo(String emy, String remarks){
        LocalDateTime now = LocalDateTime.now();
        this.updateBy = emy;
        this.updateAt = now;
        this.remarks = remarks;
        log.info(emy + "在" + now + remarks);
    }

    /**
     *  创建新记录
     * @param emy 用户账号
     */
    public void recordUpdateInfo(String emy){
        LocalDateTime now = LocalDateTime.now();
        this.updateBy = emy;
        this.updateAt = now;
        this.remarks = emy + "在" + now +"更新记录";
        log.info(remarks);
    }

    public void deleted(){
        this.delFlag = DelEnum.FALSE.getCode();
    }
    public void valid(){
        this.delFlag = DelEnum.TRUE.getCode();
    }

}
