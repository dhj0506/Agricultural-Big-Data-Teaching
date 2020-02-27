package cn.edu.seu.market.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @since 2020-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "userid")
    private String userid;

    private String name;

    @TableField("openId")
    private String openId;

    private String accid;

    private String token;

    private String weixinname;

    private String weixinid;

    private String weixinpic;

    @TableField("phoneNumber")
    private String phoneNumber;

    private LocalDateTime registdate;

    private LocalDateTime updatedate;

    @TableField("QRcodepath")
    private String QRcodepath;

    private Integer parentid;

    private Integer userlevel;

    private Integer teamnum;

    private Integer straightpush;

    private Integer userclient;

    private String other1;

    private String other2;

    public User() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getWeixinname() {
        return weixinname;
    }

    public void setWeixinname(String weixinname) {
        this.weixinname = weixinname;
    }

    public String getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid;
    }

    public String getWeixinpic() {
        return weixinpic;
    }

    public void setWeixinpic(String weixinpic) {
        this.weixinpic = weixinpic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getRegistdate() {
        return registdate;
    }

    public void setRegistdate(LocalDateTime registdate) {
        this.registdate = registdate;
    }

    public LocalDateTime getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(LocalDateTime updatedate) {
        this.updatedate = updatedate;
    }

    public String getQRcodepath() {
        return QRcodepath;
    }

    public void setQRcodepath(String QRcodepath) {
        this.QRcodepath = QRcodepath;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(Integer userlevel) {
        this.userlevel = userlevel;
    }

    public Integer getTeamnum() {
        return teamnum;
    }

    public void setTeamnum(Integer teamnum) {
        this.teamnum = teamnum;
    }

    public Integer getStraightpush() {
        return straightpush;
    }

    public void setStraightpush(Integer straightpush) {
        this.straightpush = straightpush;
    }

    public Integer getUserclient() {
        return userclient;
    }

    public void setUserclient(Integer userclient) {
        this.userclient = userclient;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }
}
