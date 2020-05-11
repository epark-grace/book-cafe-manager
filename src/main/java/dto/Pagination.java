package dto;

public class Pagination {

    private final int POST_LIMIT = 10;
    private final int PAGE_LIMIT = 10;
    private int totalPost;
    private int totalPage;
    private int currentPage;
    private int firstPage;
    private int lastPage;

    public Pagination(int totalPost, int currentPage) {
        this.currentPage = currentPage;
        this.totalPost = totalPost;
        totalPage = (totalPost - 1) / POST_LIMIT + 1;
        if (this.currentPage > totalPage) {
            this.currentPage = totalPage;
        }
        firstPage = (this.currentPage - 1) / PAGE_LIMIT * PAGE_LIMIT + 1;
        lastPage = firstPage + PAGE_LIMIT - 1;
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
