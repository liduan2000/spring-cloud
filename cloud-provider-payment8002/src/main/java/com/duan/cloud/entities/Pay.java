package com.duan.cloud.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Table：t_pay
 */
@Table(name = "t_pay")
@Data
public class Pay {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "pay_no")
    private String payNo;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "user_id")
    private Integer userId;

    private BigDecimal amount;

    /**
     * default:0 not deleted, 1 deleted
     */
    private Byte deleted;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}