package example.service;

import com.example.service.HBaseServiceImpl;
import org.apache.hadoop.hbase.TableName;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by linghang.kong on 2016/12/28.
 */
public class HBaseServiceImplTest {
    @Test
    public void getTableNames() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        HBaseServiceImpl hBaseService = context.getBean("HBaseServiceImpl", HBaseServiceImpl.class);
        List<TableName> tableNameList = hBaseService.getTableNames();

        for (TableName t : tableNameList
                ) {
            System.out.println(t.getNameAsString());
        }

    }

}