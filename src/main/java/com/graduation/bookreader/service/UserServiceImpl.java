package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.bookreader.model.User;
import com.graduation.bookreader.model.UserAuthority;
import com.graduation.bookreader.model.params.BookUnUpParam;
import com.graduation.bookreader.model.params.QueryParam;
import com.graduation.bookreader.repo.UserAuthorityMapper;
import com.graduation.bookreader.repo.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-22
 * Time: 20:35
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserAuthorityMapper userAuthorityMapper;

    @Override
    public boolean register(User user) {
        String md5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5);
        if (user.getId() == null) {
            int insert = userMapper.insert(user);
            UserAuthority userAuthority = new UserAuthority();
            userAuthority.setUserId(insert);
            this.defaultAuth(userAuthority, user.getAge());
            userAuthorityMapper.insert(userAuthority);
        } else {
            userMapper.updateById(user);
            UserAuthority userAuthority = new UserAuthority();
            userAuthority.setUserId(user.getId());
            userAuthority.setDeleted(0);
            UserAuthority real = userAuthorityMapper.selectOne(new QueryWrapper<>(userAuthority));
            real.setDeleted(1);
            userAuthorityMapper.updateById(real);
        }
        //判断年龄
        //区别年龄段
        //往权限表插入一条数据
        return true;
    }

    @Override
    public IPage<User> listUsers(QueryParam queryParam) {
        Page<User> page = new Page<>(queryParam.getPageIndex(), queryParam.getPageSize());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",queryParam.getName());
        return userMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void updateUser(User user) {
        User oldUser = userMapper.selectById(user.getId());
        if(!oldUser.getPassword().equals(user.getPassword())){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        userMapper.updateById(user);
    }

    @Override
    public void deleteUser(BookUnUpParam bookUnUpParam) {
        List<Integer> ids = bookUnUpParam.getIds();
        ids.forEach(id -> {
            User user = new User();
            user.setId(id);
            user.setDeleted(bookUnUpParam.getType());
            userMapper.updateById(user);
        });
    }

    private void defaultAuth(UserAuthority userAuthority, Integer age) {
        if (age > 18 && age <= 25) {
            userAuthority.setReadAuthority(2);
        } else if (age > 25) {
            userAuthority.setReadAuthority(3);
        }
        userAuthority.setWriteAuthority(1);
    }
}
