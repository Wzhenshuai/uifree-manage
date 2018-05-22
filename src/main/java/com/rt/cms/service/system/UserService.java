package com.rt.cms.service.system;

import com.rt.cms.common.base.Page;
import com.rt.cms.common.base.PageResultSet;
import com.rt.cms.model.system.BizException;
import com.rt.cms.model.system.User;
import com.rt.cms.model.system.UserExample;
import com.rt.cms.web.dto.system.UserDto;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> selectByExample(UserExample example);

    PageResultSet<UserDto> findByPage(Page page);

    /**
     * 创建用户
     * @param user
     */
    public int createUser(User user) throws BizException;

    public int updateUser(User user);

    public void deleteUser(Long userId);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    void changePassword(Long userId, String newPassword);


    User findOne(Long userId);

    List<User> findAll();

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);

}