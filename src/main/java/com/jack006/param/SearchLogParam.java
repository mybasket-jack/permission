package com.jack006.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/2/4 22:05
 */
@Getter
@Setter
@ToString
public class SearchLogParam {

    private Integer type;

    private String beforeSeg;

    private String afterSeg;

    private String operator;

    private String fromTime; // yyyy-MM-dd HH:mm:ss

    private String toTime;
}
