package com.rymcu.subject.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 服务过程中特殊处理返回数据
 *
 * @author 🐛
 */
@Data
@AllArgsConstructor
public class RespResult {
    private String message;
    private boolean respFlag;
    private Object respData;

}
