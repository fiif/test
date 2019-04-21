package com.pczzz.service;

import com.pczzz.domain.WXUser;

public interface WechartService {
    WXUser wxLogin(String code);
}
