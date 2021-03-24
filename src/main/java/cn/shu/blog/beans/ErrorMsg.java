package cn.shu.blog.beans;

import lombok.Builder;
import lombok.Data;

/**
 * 错误信息
 *
 * @author SXS
 * @since 3/24/2021
 */

@Data
public class ErrorMsg {
    /**
     * 消息描述
     */
    private String desc ;
    /**
     * 错误代码
     */
    private int code ;
}
