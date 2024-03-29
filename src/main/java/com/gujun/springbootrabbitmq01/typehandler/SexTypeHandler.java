package com.gujun.springbootrabbitmq01.typehandler;

import com.gujun.springbootrabbitmq01.entity.Enum.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//mybatis类型转换器
@MappedJdbcTypes(JdbcType.TINYINT)
@MappedTypes(value = SexEnum.class)
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,sexEnum.getId());
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int sex=resultSet.getInt(s);
        if(sex!=1&&sex!=2){
            return null;
        }
        return SexEnum.getEnumById(sex);
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int sex=resultSet.getInt(i);
        if(sex!=1&&sex!=2){
            return null;
        }
        return SexEnum.getEnumById(sex);
    }

    //通过存储过程读取
    @Override
    public SexEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int sex=callableStatement.getInt(i);
        if(sex!=1&&sex!=2){
            return null;
        }
        return SexEnum.getEnumById(sex);
    }



}
