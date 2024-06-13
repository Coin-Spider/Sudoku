package com.example.no0001.Services;

import com.example.no0001.Domain.Trans.UserTrans;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {
    UserTrans Login(String token,int userId);
}
