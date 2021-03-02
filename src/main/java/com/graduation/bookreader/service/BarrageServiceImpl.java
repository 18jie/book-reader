package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.bookreader.algorithm.TextClassify;
import com.graduation.bookreader.model.Barrage;
import com.graduation.bookreader.model.User;
import com.graduation.bookreader.model.UserAuthority;
import com.graduation.bookreader.model.vo.BarrageVo;
import com.graduation.bookreader.repo.BarrageMapper;
import com.graduation.bookreader.repo.UserAuthorityMapper;
import com.graduation.bookreader.repo.UserMapper;
import com.graduation.bookreader.util.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private final static Logger logger = LoggerFactory.getLogger(BarrageServiceImpl.class);

    @Resource
    private BarrageMapper barrageMapper;

    @Resource
    private UserAuthorityMapper userAuthorityMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserSession userSession;

    @Override
    public IPage<BarrageVo> listBarrage(Integer chapterId, String comment, Integer pageNum, Integer pageSize) {
        Page<Barrage> page = new Page<>(pageNum, pageSize);
        Barrage barrage = new Barrage();
        barrage.setChapterId(chapterId);
        //第1章 武魂觉醒
        barrage.setContentCode(DigestUtils.md5DigestAsHex(comment.trim().getBytes()));
        //加入分级
        User user = userSession.localUser();
        if (Objects.isNull(user)) {
            //没有登录的情况下，只可以看1级
            barrage.setLevel(2);
        } else {
            UserAuthority userAuthority = new UserAuthority();
            userAuthority.setUserId(user.getId());
            userAuthority.setDeleted(0);
            QueryWrapper<UserAuthority> queryWrapper = new QueryWrapper<>(userAuthority);
            UserAuthority real = userAuthorityMapper.selectOne(queryWrapper);
            if (real == null) {
                barrage.setLevel(2);
            } else {
                barrage.setLevel(real.getReadAuthority());
            }
        }
        QueryWrapper<Barrage> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("level", barrage.getLevel()).eq("deleted", 0).eq("content_code", barrage.getContentCode());
        Page<Barrage> barragePage = barrageMapper.selectPage(page, queryWrapper);
        List<Barrage> records = barragePage.getRecords();
        List<BarrageVo> barrageVos = new ArrayList<>();
        records.forEach(record -> {
            BarrageVo barrageVo = new BarrageVo();
            BeanUtils.copyProperties(record, barrageVo);
            User user1 = userMapper.selectById(record.getUserId());
            barrageVo.setUserName(user1.getUserName());
            barrageVos.add(barrageVo);
        });
        Page<BarrageVo> page1 = new Page<>(pageNum, pageSize);
        page1.setRecords(barrageVos);
        page1.setTotal(barragePage.getTotal());
        page1.setCurrent(barragePage.getCurrent());

        return page1;
    }

    @Override
    public void addBarrage(Barrage barrage) {
        logger.info("barrage={}", barrage);
        //1. 对文字语言编码  md5
        barrage.setContentCode(DigestUtils.md5DigestAsHex(barrage.getContentCode().trim().getBytes()));
        User user = userSession.localUser();
        barrage.setUserId(user.getId());
        // 通过神经网络判断分级
        barrage.setLevel(TextClassify.classify(barrage.getContent()));
        logger.info("barrage={}", barrage);
        barrage.setCreateTime(new Date());
        barrage.setUpdateTime(new Date());
        barrageMapper.insert(barrage);
    }
}
