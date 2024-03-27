package com.duan.cloud.service;

import com.duan.cloud.entities.Pay;

import java.util.List;

public interface PayService {
    int add(Pay pay);

    int delete(Integer id);

    int update(Pay pay);

    Pay getById(Integer id);

    List<Pay> getAll();
}
