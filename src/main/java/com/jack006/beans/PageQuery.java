package com.jack006.beans;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/1/29 9:41
 */
public class PageQuery {

    @Getter
    @Setter
    @Min(value=1, message = "当前页码不合法")
    private int pageNo =1;

    @Getter
    @Setter
    @Min(value = 1, message = "每页展示数量不合法")
    private int pageSize = 10;

    @Setter
    private int offset;

    // mysql 中分页的偏移量
    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }
}
