package mytest;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by linghang.kong on 2016/12/29.
 */
public class MyTest {

    public static void main(String[] args) throws IOException {
//        Test.testIP("shyp-bigdata-b-cn01", 2181);
        System.out.println("++++++++++++++++++++++++++");
        System.err.println(Arrays.asList(HBaseUtils.getConnection().getTableNames()));
        System.out.println("++++++++++++++++++++++++++");
    }
}
