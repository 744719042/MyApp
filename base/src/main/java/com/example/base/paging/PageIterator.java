package com.example.base.paging;

/**
 * 负责管理分页数据的迭代器
 */
public class PageIterator {
    private static final int DEFAULT_PAGE_INDEX = 1;
    private static final int DEFAULT_PAGE_SIZE = 5;

    private int pageSize;
    private int page = DEFAULT_PAGE_INDEX;
    // 总共条目数还未初始化
    private int total = -1;
    private int lastCount;

    private PageLoader pageLoader;

    public PageIterator(PageLoader pageLoader) {
        this(DEFAULT_PAGE_SIZE, pageLoader);
    }

    public PageIterator(int pageSize, PageLoader pageLoader) {
        this.pageSize = pageSize;
        this.pageLoader = pageLoader;
    }

    public void onLoaded(int total, int lastCount) {
        this.total = total;
        this.lastCount = lastCount;
        page++;
    }

    /**
     * 判断是否还有下一页数据
     */
    private boolean hasNext() {
        if (total == -1) {
            if (lastCount <= pageSize) {
                return true;
            }
            return false;
        }

        int pages = total / pageSize;
        int totalPage = total % pageSize == 0 ? pages : (pages + 1);
        // 如果下一页页码小于等于总页面数，就表明还有下一页
        if (page + 1 <= totalPage) {
            return true;
        }
        return false;
    }

    /**
     * 获取下一页的页码
     */
    public void next() {
        if (pageLoader != null) {
            if (hasNext()) {
                pageLoader.loadNext(page, pageSize);
            }
        }
    }

    /**
     * 当页面请求失败后需要回退到上一页，这样下一次请求的还是失败这次的页码
     */

    public boolean isFirstPage() {
        return page == DEFAULT_PAGE_INDEX;
    }

    public void reset() {
        total = -1;
        lastCount = 0;
    }
}
