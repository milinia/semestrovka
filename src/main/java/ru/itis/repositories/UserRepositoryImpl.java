package ru.itis.repositories;

import org.springframework.jdbc.core.PreparedStatementSetter;
import ru.itis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    @Autowired
//    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
//        this.jdbcTemplate = jdbcTemplate;
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }

    private static final String SQL_INSERT = "insert into account(name, surname, email, password) values (:name, :surname, :email, :password)";
    private static final String SQL_SELECT = "select * from account where id=?";
    private static final String SQL_SELECT_BY_ID = "select * from account where id in (:ids)";
    private static final String SQL_SELECT_ALL = "select * from account";
    private static final String SQL_DELETE_BY_ID = "delete from account where id=?";
    private static final String SQL_DELETE_BY_ID2 = "delete from account where id in (:ids)";
    private static final String SQL_DELETE = "delete from account where id=:id and name=:name and email=:email and password=:password";
    private static final String SQL_DELETE_ALL1 = "delete from account where id=:id";
    private static final String SQL_DELETE_ALL2 = "delete from account";
    private static final String SQL_UPDATE_PASSWORD = "update account set password=? where id=?";
    private static final String SQL_SELECT_BY_EMAIL_AND_PASSWORD = "select * from account where email=:email and password=:password";

    private RowMapper<User> userRowMapper = ((resultSet, i) -> {
        return User.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .build();
    });

    @Override
    public <S extends User> S save(S user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(SQL_INSERT, namedParameters, keyHolder, new String[]{"id"});
        user.setId(keyHolder.getKey().longValue());
        return null;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> users) {
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(userList.toArray());
        namedParameterJdbcTemplate.batchUpdate(SQL_INSERT, batch);
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT, userRowMapper, id);
        } catch (DataAccessException ex) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public boolean existsById(Long id) {
        Optional<User> user = findById(id);
        return user.isPresent();
    }

    @Override
    public Iterable<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> ids) {
        SqlParameterSource parameters = new MapSqlParameterSource("ids", ids);
        return namedParameterJdbcTemplate.query(SQL_SELECT_BY_ID, parameters, userRowMapper);
    }

    @Override
    public long count() {
        List<User> users = jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
        return users.size();
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public void delete(User user) {
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(SQL_DELETE, namedParameters);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        List<Long> idList = new ArrayList<>();
        ids.forEach(idList::add);
        //SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(idList.toArray());
        //namedParameterJdbcTemplate.batchUpdate(SQL_DELETE_BY_ID, batch);
        jdbcTemplate.batchUpdate(SQL_DELETE_BY_ID,
                new BatchPreparedStatementSetter(){
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, idList.get(i));
                    }

                    @Override
                    public int getBatchSize() {
                        return idList.size();
                    }
                });
    }

//    @Override
//    public void deleteAllById(Iterable<? extends Long> ids) {
//        SqlParameterSource parameters = new MapSqlParameterSource("ids", ids);
//        namedParameterJdbcTemplate.update(SQL_DELETE_BY_ID2, parameters);
//    }

    @Override
    public void deleteAll(Iterable<? extends User> users) {
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(userList.toArray());
        namedParameterJdbcTemplate.batchUpdate(SQL_DELETE_ALL1, batch);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(SQL_DELETE_ALL2);
    }

    @Override
    public void updatePassword(Long id, String password) {
//        Object[] params = {password, id};
//        int[] types = {Types.VARCHAR, Types.BIGINT};
        jdbcTemplate.update(SQL_UPDATE_PASSWORD, password, id);

//        User user = new User(id, password);
//        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
//        namedParameterJdbcTemplate.update(SQL_UPDATE_PASSWORD,namedParameters);
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        User user = new User(email, password);
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        try{
            return namedParameterJdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL_AND_PASSWORD, namedParameters, userRowMapper);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
