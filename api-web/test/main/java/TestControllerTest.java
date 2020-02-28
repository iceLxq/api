package main.java;

import com.api.job.QuartzJob;
import com.api.service.daylyService.DaylyService;
import com.api.service.shareServcie.ShareService;
import main.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by 李显琪 on 2020/2/5.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestControllerTest {

    @Autowired
    private DaylyService daylyService;

    @Autowired
    private QuartzJob quartzJob;


    @Test
    public void test(){
        daylyService.cacheDayFollow();
    }

    @Test
    public void test1(){
        quartzJob.syncSecuritiesCode();
    }


}