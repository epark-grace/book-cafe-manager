package dto;

public class Pagination {

    private final int POST_LIMIT = 10;
    private final int PAGE_LIMIT = 10;
    private int totalPost;
    private int totalPage;
    private int currentPage;
    private int firstPage;

    public Pagination(int totalPost, int currentPage) {
        this.totalPost = totalPost;
        this.currentPage = currentPage;
        this.totalPage = (this.totalPost - 1) / POST_LIMIT + 1;
        this.firstPage = (this.currentPage - 1) / PAGE_LIMIT * PAGE_LIMIT + 1;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getFirstPage() {
        return firstPage;
    }
}
