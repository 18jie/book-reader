package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.bookreader.model.Recommend;
import com.graduation.bookreader.repo.RecommendMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2021-03-04
 * Time: 22:45
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    @Resource
    private RecommendMapper recommendMapper;

    @Override
    public List<Recommend> list() {
        Recommend recommend = new Recommend();
        recommend.setDeleted(0);
        QueryWrapper<Recommend> query = new QueryWrapper<>(recommend);
        return recommendMapper.selectList(query);
    }
}
