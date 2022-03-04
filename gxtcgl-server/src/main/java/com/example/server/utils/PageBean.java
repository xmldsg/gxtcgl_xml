package com.example.server.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>分页公共返回对象</p>
 *
 * @author : xueminglu
 * @date : 2021-12-27 17:06
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {

    //总条数
    private Long total;

    //数据
    private List<?> data;

}
