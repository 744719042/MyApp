package com.example.base.paging;

public interface PageLoader {
    void loadNext(int page, int pageSize);
    void onLoaded(int totalCount, int lastCount);
}
