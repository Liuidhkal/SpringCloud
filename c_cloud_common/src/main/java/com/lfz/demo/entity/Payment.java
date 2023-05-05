package com.lfz.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    /**
     * 订单编号
     */
    private Integer id;
    /**
     * ⽀付状态
     */
    private String message;
}
