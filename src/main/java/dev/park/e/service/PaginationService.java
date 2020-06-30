package dev.park.e.service;

import dev.park.e.dao.PaginationDao;
import dev.park.e.dto.Pagination;
import org.springframework.stereotype.Service;

@Service
public class PaginationService {

    private PaginationDao paginationDao;

    public PaginationService(PaginationDao paginationDao) {
        this.paginationDao = paginationDao;
    }

    public Pagination getPagination(int currentPage) {
        return new Pagination(paginationDao.selectBookCount(), currentPage);
    }
}
