package dev.park.e.bookcafemanager.dto;

import lombok.Getter;

@Getter
public class Pagination {

    public static final int ROW_LIMIT = 10;
    public static final int PAGE_LIMIT = 10;
    private final long bookCount;
    private final int pageCount;
    private int currentPage;
    private final int firstPage;
    private int lastPage;

    public Pagination(long bookCount, int currentPage) {
        this.currentPage = currentPage;
        this.bookCount = bookCount;
        pageCount = (int) ((bookCount - 1) / ROW_LIMIT + 1);
        if (this.currentPage > pageCount) {
            this.currentPage = pageCount;
        }
        firstPage = (this.currentPage - 1) / PAGE_LIMIT * PAGE_LIMIT + 1;
        lastPage = firstPage + PAGE_LIMIT - 1;
        if (lastPage > pageCount) {
            lastPage = pageCount;
        }
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "ROW_LIMIT=" + ROW_LIMIT +
                ", PAGE_LIMIT=" + PAGE_LIMIT +
                ", bookCount=" + bookCount +
                ", pageCount=" + pageCount +
                ", currentPage=" + currentPage +
                ", firstPage=" + firstPage +
                ", lastPage=" + lastPage +
                '}';
    }

    public static long getRowCount(int currentPage) {
        return ((long) currentPage - 1) * Pagination.ROW_LIMIT;
    }
}
