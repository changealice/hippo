package com.change.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * User: change.long Date: 16/4/14 Time: 下午1:50
 */
public class Correlation implements Serializable {


    private static final long serialVersionUID = 6982729537138390719L;

    /**
     * memberCode 会员编号
     */
    private Long memberCode;

    /**
     * 统一账户平台UAP的用户主键Id
     */
    private Long PWID;
    /**
     * 新增时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(Long memberCode) {
        this.memberCode = memberCode;
    }

    public Long getPWID() {
        return PWID;
    }

    public void setPWID(Long PWID) {
        this.PWID = PWID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Correlation{" +
                "memberCode=" + memberCode +
                ", PWID=" + PWID +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
