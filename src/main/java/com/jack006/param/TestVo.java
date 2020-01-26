package com.jack006.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/1/22 22:13
 */
@Getter
@Setter
public class TestVo {

    @NotBlank
    private String name;

    @NotNull
    private Integer id;
}
