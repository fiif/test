package com.pczzz.dao;

import com.pczzz.domain.WXUser;

public interface WechartDao {
    WXUser findByOpenid(String toString);

//    void save(WXUser user);
}
