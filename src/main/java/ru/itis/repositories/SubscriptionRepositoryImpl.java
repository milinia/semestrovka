package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Subscription;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

@Service
public class SubscriptionRepositoryImpl implements SubscriptionRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SQL_INSERT = "insert into subscription(user_id,service_name,ending_date) values" +
            "(:userId, :serviceName, :endingDate)";
    private static final String SQL_DELETE = "delete from subscription where id=? and user_id=?";
    private static final String SQL_SELECT = "select * from subscription where user_id=?";

    public SubscriptionRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private RowMapper<Subscription> subscriptionRowMapper = ((resultSet, i) -> {
        return Subscription.builder()
                .subId(resultSet.getLong("id"))
                .userId(resultSet.getLong("user_id"))
                .serviceName(resultSet.getString("service_name"))
                .endingDate(resultSet.getDate("ending_date"))
                .build();
    });

    @Override
    public void createNewSub(Subscription subscription) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(subscription);
        namedParameterJdbcTemplate.update(SQL_INSERT, namedParameters, keyHolder, new String[]{"id"});
        subscription.setSubId(keyHolder.getKey().longValue());
    }

    @Override
    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT, subscriptionRowMapper, userId);
    }

    @Override
    public void deleteSub(Long subId, Long userId) {
        jdbcTemplate.update(SQL_DELETE, subId, userId);
    }
}
