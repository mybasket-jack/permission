package com.jack006.beans;

import lombok.*;

import java.util.Set;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/1/29 20:09
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private String subject;

    private String message;

    private Set<String> receivers;
}
