package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.bookreader.model.Barrage;
import com.graduation.bookreader.model.User;
import com.graduation.bookreader.model.UserAuthority;
import com.graduation.bookreader.repo.BarrageMapper;
import com.graduation.bookreader.repo.UserAuthorityMapper;
import com.graduation.bookreader.util.UserSession;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-01
 * Time: 21:22
 */
@Service
public class BarrageServiceImpl implements BarrageService {

    @Resource
    private BarrageMapper barrageMapper;

    @Resource
    private UserAuthorityMapper userAuthorityMapper;

    @Resource
    private UserSession userSession;

    @Override
    public IPage<Barrage> listBarrage(Integer chapterId, String comment, Integer pageNum, Integer pageSize) {
        Page<Barrage> page = new Page<>(pageNum, pageSize);
        Barrage barrage = new Barrage();
        barrage.setChapterId(chapterId);
        //第1章 武魂觉醒
        barrage.setContentCode(DigestUtils.md5DigestAsHex(comment.trim().getBytes()));
        barrage.setDeleted(0);
        //加入分级
        User user = userSession.localUser();
        if (Objects.isNull(user)) {
            //没有登录的情况下，只可以看1级
            barrage.setLevel(1);
        } else {
            UserAuthority userAuthority = new UserAuthority();
            userAuthority.setUserId(user.getId());
            userAuthority.setDeleted(0);
            QueryWrapper<UserAuthority> queryWrapper = new QueryWrapper<>(userAuthority);
            UserAuthority real = userAuthorityMapper.selectOne(queryWrapper);
            barrage.setLevel(real.getReadAuthority());
        }
        QueryWrapper<Barrage> queryWrapper = new QueryWrapper<>(barrage);

        return barrageMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void addBarrage(Barrage barrage) {
        // TODO 看是前端计算MD5还是后端计算
        barrage.setCreateTime(new Date());
        barrage.setUpdateTime(new Date());
        barrageMapper.insert(barrage);
    }
}
