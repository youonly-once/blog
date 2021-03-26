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
 * 在用resultMap处理结果集的时候 指定该类为TypeHandler，可将数据库返回的结果封装为指定的类
 */
@MappedTypes(Enum.class)
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
            Class<? extends Enum> aClass = eEnum.getClass();
            Field field = aClass.getDeclaredField("VALUE");
            field.setAccessible(true);
            short aShort = Short.parseShort(field.get(eEnum).toString());
            preparedStatement.setShort(i, aShort);
        } catch (Exception e) {
            preparedStatement.setShort(i, (short) 0);
        }
    }

    @Override
    public Enum<E> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        short aShort = 0;
        for (E enumConstant : type.getEnumConstants()) {
            Field value = null;
            try {
                value = enumConstant.getClass().getDeclaredField("VALUE");
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
            if (aShort == aShort1){
                return enumConstant;
            }
        }
        return null;
    }

    @Override
    public Enum<E> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        short aShort = resultSet.getShort(i);
        for (E enumConstant : type.getEnumConstants()) {
            Field value = null;
            try {
                value = enumConstant.getClass().getDeclaredField("VALUE");
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
            if (aShort == aShort1){
                return enumConstant;
            }
        }
        return null;
    }

    @Override
    public Enum<E> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {

        short aShort = callableStatement.getShort(i);
        for (E enumConstant : type.getEnumConstants()) {
            Field value = null;
            try {
                value = enumConstant.getClass().getDeclaredField("VALUE");
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
            if (aShort == aShort1){
                return enumConstant;
            }
        }
        return null;
    }
}