package org.shardingdatabasestables;

import org.common.domain.User;
import org.common.factory.YamlDataSourceFactory;
import org.common.service.UserService;
import org.common.service.impl.UserServiceImpl;
import org.common.type.ShardingType;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author : Jieker
 * @Description :
 * @date : 2021/1/4 9:52
 */
public class ShardingDatabasesTablesYamlTest {

    private final ShardingType SHARDING_DATABASES_AND_TABLES = ShardingType.SHARDING_DATABASES_AND_TABLES;
    private UserService userService;

    @Before
    public void init() throws IOException, SQLException {
        DataSource dataSource = YamlDataSourceFactory.newInstance(SHARDING_DATABASES_AND_TABLES, "/META-INF/sharding-databases-tables.yml");
        userService = new UserServiceImpl(dataSource);
    }

    @Test
    public void selectAllTest() {
        List<User> users = userService.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void insertTest() {
        userService.saveUser();
    }

    @Test
    public void deleteTest() {
        userService.deleteUser((long) 7);
    }

}
