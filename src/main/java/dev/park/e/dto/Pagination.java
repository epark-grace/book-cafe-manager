package dev.park.e.dto;

public class Pagination {

    private final int ROW_LIMIT = 10;
    private final int PAGE_LIMIT = 10;
    private int bookCount;
    private int pageCount;
    private int currentPage;
    private int firstPage;
    private int lastPage;

    public Pagination(int bookCount, int currentPage) {
        this.currentPage = currentPage;
        this.bookCount = bookCount;
        pageCount = (bookCount - 1) / ROW_LIMIT + 1;
        if (this.currentPage > pageCount) {
            this.currentPage = pageCount;
        }
        firstPage = (this.currentPage - 1) / PAGE_LIMIT * PAGE_LIMIT + 1;
        lastPage = firstPage + PAGE_LIMIT - 1;
        if (lastPage > pageCount) {
            lastPage = pageCount;
        }
    }

    public int getPageCount() {
        return pageCount;
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
