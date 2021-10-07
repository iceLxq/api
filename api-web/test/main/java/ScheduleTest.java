package main.java;

import com.api.job.SharePriceAlarmSchedule;
import main.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 李显琪 on 2021/10/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ScheduleTest {

    @Autowired
    private SharePriceAlarmSchedule sharePriceAlarmSchedule;

    @Test
    public void test(){
        sharePriceAlarmSchedule.priceAlarm();
    }


}
