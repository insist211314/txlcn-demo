package org.txlcn.demo.common.db.mapper;

import org.apache.ibatis.annotations.*;
import org.txlcn.demo.common.db.domain.Demo;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */
@Mapper
public interface BaseDemoMapper {

    @Insert("insert into t_demo(kid, demo_field, group_id, create_time,app_name) values(#{kid}, #{demoField}, #{groupId}, #{createTime},#{appName})")
    void save(Demo demo);

    @Insert("insert into t_demo(kid, demo_field, group_id,app_name) values(#{kid}, #{demoField}, #{groupId}, #{appName})")
    @Options(useGeneratedKeys = true, keyProperty = "kid")
    void save2(Demo demo);

    @Select(" select count(*) from t_demo")
    int getCount();

    @Update("update t_demo set app_name=#{appName} where group_id=#{groupId}")
    int updateByGourpId(Demo demo);

    @Delete("delete from t_demo where id=#{id}")
    void deleteByKId(Long id);
}
