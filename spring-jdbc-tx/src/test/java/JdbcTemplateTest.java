import com.lvck1.spring6.jdbc.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(locations = "classpath:beans.xml")
public class JdbcTemplateTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //添加，修改，删除操作
    @Test
    public void testUpdate() {
        //添加操作
        //第一步：编写SQL语句
        String sql = "insert into t_emp values(null,?,?,?)";
        //第二步：调用jdbcTemplete方法，传入相关参数
//        Object[] params = {"东方不败", 20, "未知"};
//        int rows = jdbcTemplate.update(sql, params);

        //返回受影响的行数
        int rows = jdbcTemplate.update(sql, "林平之", 20, "未知");
        System.out.println(rows);

    }

    @Test
    public void testUpdate2() {
        //修改操作
        String sql = "update t_emp set name = ? where id = ?";
        int rows = jdbcTemplate.update(sql, "林平之666", 3);
        System.out.println(rows);
    }

    @Test
    public void testUpdate3() {
        //删除操作
        String sql = "delete from t_emp where id = ?";
        int rows = jdbcTemplate.update(sql, 3);
        System.out.println(rows);
    }

    //查询操作，返回对象
    @Test
    public void testSelectObject() {
        //写法一，自定义封装类
        String sql = "select * from t_emp where id = ?";
        Emp empResult = jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> {
                    Emp emp = new Emp();
                    emp.setId(rs.getInt("id"));
                    emp.setName(rs.getString("name"));
                    emp.setAge(rs.getInt("age"));
                    emp.setSex(rs.getString("sex"));
                    return emp;
                },
                1);
        System.out.println(empResult);

        //写法二，使用已有的封装类
        empResult = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Emp.class), 1);
        System.out.println(empResult);
    }

    //查询操作，返回list集合
    @Test
    public void testSelectList() {
        String sql = "select * from t_emp";
        List<Emp> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Emp.class));
        System.out.println(list);
    }

    //查询操作，返回单个值
    @Test
    public void testSelectValue(){
        String sql = "select count(*) from t_emp";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }
}
