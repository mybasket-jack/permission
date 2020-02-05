package com.jack006.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/2/4 22:07
 */
@Setter
@Getter
@ToString
public class SearchLogDto {

    private Integer type; // LogType

    private String beforeSeg;

    private String afterSeg;

    private String operator;

    private Date fromTime; // yyyy-MM-dd HH:mm:ss

    private Date toTime;}
