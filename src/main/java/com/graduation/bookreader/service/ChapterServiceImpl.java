package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.bookreader.model.Barrage;
import com.graduation.bookreader.model.Book;
import com.graduation.bookreader.model.Chapter;
import com.graduation.bookreader.model.vo.ChapterDetailVo;
import com.graduation.bookreader.model.vo.ChapterVo;
import com.graduation.bookreader.model.vo.ContentVo;
import com.graduation.bookreader.model.vo.LineVo;
import com.graduation.bookreader.repo.BarrageMapper;
import com.graduation.bookreader.repo.BookMapper;
import com.graduation.bookreader.repo.ChapterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-01
 * Time: 21:13
 */
@Service
public class ChapterServiceImpl implements ChapterService {

    private static final Logger logger = LoggerFactory.getLogger(ChapterServiceImpl.class);

    @Resource
    private ChapterMapper chapterMapper;

    @Resource
    private BookMapper bookMapper;

    @Resource
    private BarrageMapper barrageMapper;

    @Override
    public IPage<Chapter> listChapterbyBookId(Integer bookId, Integer pageNum, Integer pageSize) {
        Page<Chapter> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "book_id", "chapter_name").eq("book_id", bookId);
        return chapterMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Chapter getChapterById(Integer id) {
        return chapterMapper.selectById(id);
    }

    @Override
    public ChapterDetailVo chapterDetail(Integer chapterId) {
        Chapter chapter = chapterMapper.selectById(chapterId);
        ChapterDetailVo chapterDetailVo = new ChapterDetailVo();
        chapterDetailVo.setId(chapterId);

        String[] split = chapter.getContent().split("   ");
        List<LineVo> lineVos = new ArrayList<>();
        for (String line : split) {
            String lineMd5 = DigestUtils.md5DigestAsHex(line.trim().getBytes());
            QueryWrapper<Barrage> barrageQueryWrapper = new QueryWrapper<>();
            //TODO 这里将来增加level的获取
            barrageQueryWrapper.select("id", "book_id", "chapter_id").eq("chapter_id", chapterId).eq("content_code", lineMd5);
            List<Barrage> barrages = barrageMapper.selectList(barrageQueryWrapper);

            LineVo lineVo = new LineVo();
            lineVo.setLine(line);
            lineVo.setCommitNum(barrages.size());
            lineVos.add(lineVo);
        }
        chapterDetailVo.setLines(lineVos);
        chapterDetailVo.setBookId(chapter.getBookId());
        return chapterDetailVo;
    }

    @Override
    public List<ChapterVo> chapterVosByBookId(Integer bookId) {
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.select("id", "chapter_name").eq("book_id", bookId);
        List<Chapter> chapters = chapterMapper.selectList(chapterQueryWrapper);

        Book book = bookMapper.selectById(bookId);
        List<ChapterVo> chapterVos = new ArrayList<>();
        int order = 1;
        for (Chapter chapter : chapters) {
            ChapterVo chapterVo = this.buildChapterVo(chapter, book.getBookName());
            chapterVo.setOrder(order++);
            chapterVos.add(chapterVo);
        }
        return chapterVos;
    }

    @Override
    public ContentVo chapterContentVoByChapterId(Integer chapterId) {
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Chapter> first = queryWrapper.eq("id", chapterId);
        Chapter chapter = chapterMapper.selectOne(first);

        QueryWrapper<Chapter> firstOneQuery = new QueryWrapper<>();
        firstOneQuery.eq("book_id",chapter.getBookId()).orderByAsc("id").last("limit 1");
        Chapter firstOne = chapterMapper.selectOne(firstOneQuery);

        ContentVo contentVo = new ContentVo();
        contentVo.setChapterTitle(chapter.getChapterName());
        contentVo.setContent(chapter.getContent());
        contentVo.setOrder(chapterId - firstOne.getId() + 1);
        contentVo.setIsVip(false);
        return contentVo;
    }

    private ChapterVo buildChapterVo(Chapter chapter, String bookName) {
        ChapterVo chapterVo = new ChapterVo();
        chapterVo.setId(chapter.getId());
        chapterVo.setBookName(bookName);
        chapterVo.setIsVip(false);
        chapterVo.setLink(chapter.getId());
        chapterVo.setTitle(chapter.getChapterName());
        chapterVo.setOrder(chapter.getId());
        return chapterVo;
    }
}
