package com.zwc.sellsys.android.entity;

/**
 * 分页类
 */
public class PagerBean {

    /**
     * 一页显示多少条
     */
    public static final int PAGE_SIZE = 20;

    /**
     * 总共有多少页
     */
    private int pageCount;

    /**
     * 当前是第几页
     */
    private int currentPage;

    /**
     * 总条目数
     */
    private int totalCount;


    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    /**
     * 如果当前页小于或者等于第一页，则不可以进行前一页，否则可以进行前一页
     *
     * @return 当前页数
     */
    public int getPreviousPage() {
        if (this.currentPage <= 1) {
            this.currentPage = 1;
        } else {
            this.currentPage--;
        }
        return currentPage;
    }

    /**
     * 如果当前页大于等于总页数，则当前页为总页数，否则当前页可以进行下一页
     *
     * @return 当前页数
     */
    public int getNextPage() {
        if (this.currentPage >= this.pageCount) {
            this.currentPage = pageCount;
        } else {
            this.currentPage++;
        }
        return currentPage;
    }

    /**
     * 获取第一页
     *
     * @return 返回第一页
     */
    public int getFirstPage() {
        return this.currentPage = 1;
    }

    /**
     * 是否还有下一页数据，
     *
     * @return 是否还可以进行下一页，如果当前页大于等于总页数，则没有下一页，否则有下一页
     */
    public boolean hasMore() {
        return this.currentPage >= this.pageCount ? false : true;
    }

    /**
     * 是否有必要一直刷新第一页数据
     *
     * @return 如果当前页小于或者等于第一页，则没有必要刷新，否则可以继续刷新
     */
    public boolean ifNeedFresh() {
        return this.currentPage <= 1 ? false : true;
    }
}
