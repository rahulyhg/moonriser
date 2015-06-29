package be.persgroep.pe.service.data.dao;

import be.persgroep.pe.service.data.queries.UserQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer selectIdByAuthorIdOrDefault(final Long authorId, final Integer defaultValue) {
        Integer peUserId = defaultValue;

        if (authorId != null) {
            try {
                peUserId =
                        this.jdbcTemplate.queryForObject(
                                UserQueries.getInstance().getSelectUserIdByAuthorId(),
                                Integer.class,
                                authorId);
            } catch (final EmptyResultDataAccessException e) {
            }
        }

        return peUserId;
    }
}
