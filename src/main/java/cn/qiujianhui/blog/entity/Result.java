package cn.qiujianhui.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Result {
    /**
     * 返回状态码
     */
    private String code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;
    /**
     * 时间戳
     */
    private String timeStamp;
    /**
     * 分页参数
     */
    private PageParam pageParam;

    private static Result data(String code, String message, Object data) {
        Result result = new Result();
        if (code != null) {
            result.setCode(code);
        }
        if (message != null) {
            result.setMessage(message);
        }
        if (data != null) {
            result.setData(data);
        }
        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 获取当前时间并格式化
        String formattedTime = LocalDateTime.now().format(formatter);
        result.setTimeStamp(formattedTime);
        return result;
    }

    public static Result success(Object data) {
        return data("00", "请求处理成功", data);
    }

    public static Result success(String message, Object data) {
        return data("00", message, data);
    }

    public static Result fail(String message) {
        return data("01", message, null);
    }

    public static Result fail(String message, Object data) {
        return data("01", message, data);
    }

    public static Result unknown() {
        return data("01", "未知错误", null);
    }
}
