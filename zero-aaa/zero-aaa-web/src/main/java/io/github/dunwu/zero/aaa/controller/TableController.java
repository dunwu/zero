package io.github.dunwu.zero.aaa.controller;

import io.github.dunwu.core.DataListResult;
import io.github.dunwu.core.ResultUtil;
import io.github.dunwu.util.mock.MockUtil;
import io.github.dunwu.util.number.RandomUtil;
import io.github.dunwu.util.time.DateFormatUtil;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-06-15
 */
@RestController
@RequestMapping("/table")
public class TableController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Data
    public class Element {
        private String id;
        private String title;
        private String author;
        private String status;
        private String display_time;
        private Integer pageviews;
    }

    @GetMapping("/list")
    public DataListResult logout(HttpServletRequest request) {
        LocalDateTime min = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        LocalDateTime max = LocalDateTime.of(2018, 12, 31, 23, 59, 59);

        String[] status = {"draft", "published", "deleted"};
        List<Element> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Element element = new Element();
            element.setId(UUID.randomUUID()
                              .toString());
            element.setAuthor(MockUtil.anyCName());
            element.setTitle(MockUtil.anySimpleCLetterString(5, 20));
            element.setDisplay_time(
                MockUtil.anyDate(min, max, DateFormatUtil.DatePattern.PATTERN_DEFAULT_ON_SECOND.pattern()));
            element.setStatus(MockUtil.mock(status));
            element.setPageviews(RandomUtil.nextInt(500, 20000));
            list.add(element);
        }
        return ResultUtil.successDataListResult(list);
    }
}
