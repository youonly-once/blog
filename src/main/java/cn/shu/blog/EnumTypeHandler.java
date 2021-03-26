package cn.shu.blog;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author SXS
 * @since 3/26/2021
 */

/**
 * 自定义枚举类型转换
 */
public class EnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<Enum<E>> {
    private final Class<E> type;

    public EnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        } else {
            this.type = type;
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Enum<E> eEnum, JdbcType jdbcType) throws SQLException {
        try {
            //将枚举的code属性的值作为数据库short类型的查询字段
            Class<? extends Enum> aClass = eEnum.getClass();
            Field field = aClass.getDeclaredField("code");
            field.setAccessible(true);
            short aShort = Short.parseShort(field.get(eEnum).toString());
            preparedStatement.setShort(i, aShort);
        } catch (Exception e) {
            preparedStatement.setShort(i, (short) 0);
        }
    }

    @Override
    public Enum<E> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        //获取数据库返回的结果
        //封装成Enum
        short aShort = resultSet.getShort(s);
        return matchENUM(aShort);
    }

    @Override
    public Enum<E> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        //获取数据库返回的结果
        //封装成Enum
        short aShort = resultSet.getShort(i);
        return matchENUM(aShort);
    }

    @Override
    public Enum<E> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        //获取数据库返回的结果
        //封装成Enum
        short aShort = callableStatement.getShort(i);
        return matchENUM(aShort);
    }

    /**
     * 根据code值封装Enum
     * @param code code值
     * @return 枚举
     */
    private Enum<E> matchENUM(short code){
        for (E enumConstant : type.getEnumConstants()) {
            Field value = null;
            try {
                value = enumConstant.getClass().getDeclaredField("code");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return null;
            }
            value.setAccessible(true);
            short aShort1 = 0;
            try {
                aShort1 = value.getShort(enumConstant);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
            if (code == aShort1){
                return enumConstant;
            }
        }
        return null;
    }
}