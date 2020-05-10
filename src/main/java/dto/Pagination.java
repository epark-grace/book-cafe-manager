package dto;

public class Pagination {

    private final int POST_LIMIT = 10;
    private int totalPost;
    private int totalPage;

    public Pagination(int totalPost) {
        this.totalPost = totalPost;
        this.totalPage = (this.totalPost - 1) / POST_LIMIT + 1;
    }

    public int getTotalPage() {
        return this.totalPage;
    }
}
