package dev.park.e.dto;

public class Pagination {

    private final int ROW_LIMIT = 10;
    private final int PAGE_LIMIT = 10;
    private int totalRow;
    private int totalPage;
    private int currentPage;
    private int firstPage;
    private int lastPage;

    public Pagination(int totalRow, int currentPage) {
        this.currentPage = currentPage;
        this.totalRow = totalRow;
        totalPage = (totalRow - 1) / ROW_LIMIT + 1;
        if (this.currentPage > totalPage) {
            this.currentPage = totalPage;
        }
        firstPage = (this.currentPage - 1) / PAGE_LIMIT * PAGE_LIMIT + 1;
        lastPage = firstPage + PAGE_LIMIT - 1;
        if (lastPage > totalPage) {
            lastPage = totalPage;
        }
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
