package org.txlcn.demo.common.db.domain;

import com.codingapi.txlcn.common.util.id.RandomUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */

public class Demo implements Serializable {

    private static final long serialVersionUID =-1l;

    private Long id;
    private String kid = RandomUtils.randomKey();
    private String demoField;
    private String groupId;
    private Date createTime;
    private String appName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getDemoField() {
        return demoField;
    }

    public void setDemoField(String demoField) {
        this.demoField = demoField;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
