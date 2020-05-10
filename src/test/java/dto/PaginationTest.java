package dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaginationTest {

    @DisplayName("일반적인 경우의 총 페이지 수 계산")
    @ParameterizedTest(name = "총 게시글 수: {0}, 총 페이지 수: {1}")
    @CsvSource({"42, 5", "150, 15", "30, 3"})
    void getTotalPage_generalCase(int totalPost, int totalPage) {
        Pagination pagination = new Pagination(totalPost, 1);
        assertEquals(totalPage, pagination.getTotalPage());
    }

    @DisplayName("총 게시글 수가 한 페이지당 노출되는 게시글 최대 개수 이하인 경우 총 페이지 수는 1")
    @ParameterizedTest(name = "총 게시글 수: {0}, 총 페이지 수: {1}")
    @CsvSource({"0, 1", "1, 1", "10, 1"})
    void getTotalPost_totalPostIsPostLimitOrBelow_ReturnOne(int totalPost, int totalPage) {
        Pagination pagination = new Pagination(totalPost, 1);
        assertEquals(totalPage, pagination.getTotalPage());
    }

    @DisplayName("일반적인 경우의 화면에 노출되는 첫 페이지 번호 계산")
    @ParameterizedTest(name = "총 게시글 수: {0}, 현재 페이지 번호: {1}, 첫 페이지 번호: {2}")
    @CsvSource({"200, 15, 11", "353, 34, 31", "400, 31, 31"})
    void getFirstPage_generalCase(int totalPost, int currentPage, int firstPage) {
        Pagination pagination = new Pagination(totalPost, currentPage);
        assertEquals(firstPage, pagination.getFirstPage());
    }

    @DisplayName("현재 페이지가 화면에 노출되는 마지막 페이지인 경우 화면에 노출되는 첫 페이지 번호 계산")
    @ParameterizedTest(name = "현재 페이지 번호: {1}, 첫 페이지 번호: {2}")
    @CsvSource({"200, 20, 11", "300, 30, 21"})
    void getFirstPage_currentPageIsLastPage(int totalPost, int currentPage, int firstPage) {
        Pagination pagination = new Pagination(totalPost, currentPage);
        assertEquals(firstPage, pagination.getFirstPage());
    }

}
