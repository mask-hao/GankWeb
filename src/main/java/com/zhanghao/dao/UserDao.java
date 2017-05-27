package com.zhanghao.dao;

import com.zhanghao.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by 张浩 on 2017/1/20.
 */

public interface UserDao {
   String findPassByAccount(@Param("userAccount")String userAccount);
   int insertUserIntoDb(@Param("userAccount")String userAccount,
                    @Param("userPassword")String userPassword,
                    @Param("userName")String userName);

   User findUserByAccount(@Param("userAccount")String userAccount);

   int updateUserImageByAccount(@Param("userImage")String userImage,
                                @Param("userAccount")String userAccount);

   int updateUserInfoByUserAccount(@Param("userName")String userName,
                                   @Param("userAccount")String userAccount);

   int updateUserPasswordByUserAccount(@Param("userAccount")String userAccount,
                                       @Param("userPassword")String password);
}
