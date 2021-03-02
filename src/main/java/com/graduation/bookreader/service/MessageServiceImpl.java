package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.bookreader.model.Book;
import com.graduation.bookreader.model.Message;
import com.graduation.bookreader.model.User;
import com.graduation.bookreader.model.vo.DashboardVo;
import com.graduation.bookreader.repo.BookMapper;
import com.graduation.bookreader.repo.MessageMapper;
import com.graduation.bookreader.util.UserSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2021-03-02
 * Time: 22:24
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private BookMapper bookMapper;

    @Resource
    private UserSession userSession;

    @Override
    public DashboardVo buildDashboard() {
        User user = userSession.localUser();
        Message message = new Message();
        message.setUserId(user.getId());
        message.setDeleted(0);
        Integer messageCount = messageMapper.selectCount(new QueryWrapper<>(message));

        Book book = new Book();
        book.setDeleted(0);
        Integer bookCount = bookMapper.selectCount(new QueryWrapper<>(book));

        DashboardVo dashboardVo = new DashboardVo();
        dashboardVo.setBookCount(bookCount);
        dashboardVo.setMsgCount(messageCount);
        return dashboardVo;
    }
}
