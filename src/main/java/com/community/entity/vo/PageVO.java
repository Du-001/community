package com.community.entity.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class PageVO<T> extends Page {
    private Long countPage = 0L;
    private Long start;
    private Long end;
    private Long previous;
    private Long next;

    public void init() {
        countPage = super.getTotal() % super.getSize() == 0 ? super.getTotal() / super.getSize() : super.getTotal() / super.getSize() + 1;
        if (countPage != 0) {
            start = (((super.getCurrent() - 1) / 5) * 5) + 1;
            end = ((super.getCurrent() - 1) / 5 + 1) * 5 > countPage ? countPage : ((super.getCurrent() - 1) / 5 + 1) * 5;
            previous = start == 1 ? null : (start / 5 - 1) * 5 + 1;
            next = end >= countPage ? null : end + 1;
        } else {
            start = end = 1L;
            previous = next = null;
        }
    }

    public PageVO(long current, long size, long total) {
        super(current, size);
        super.setTotal(total);
        super.setRecords(new ArrayList());
    }
}
