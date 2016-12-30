package example.dao;


import com.example.dao.HBaseDaoImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by linghang.kong on 2016/12/21.
 */
public class HBaseDaoImplTest {
    @Test
    public void close() throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        HBaseDaoImpl hbaseDaoImpl = context.getBean("hbaseDaoImpl", HBaseDaoImpl.class);
        hbaseDaoImpl.close();

    }

}