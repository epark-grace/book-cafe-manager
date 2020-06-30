package dev.park.e.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PaginationDao {

    private SqlSession sqlSession;

    public PaginationDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int selectBookCount() {
        return sqlSession.selectOne("dev.park.e.mapper.PaginationMapper.selectBookCount", Integer.class);
    }
}
