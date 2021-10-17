package main.java;

import com.api.domain.share.Share;
import com.api.job.QuartzJob;
import com.api.service.RuleService;
import com.api.service.daylyService.DaylyService;
import com.api.service.shareServcie.ShareService;
import main.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;


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

    @Autowired
    private ShareService shareService;

    @Autowired
    private RuleService ruleService;

    /**
     * 点这个
     */
    @Test

    public void SyncData(){
        quartzJob.syncSecuritiesCode();
      //  daylyService.cacheDayFollow();
       // quartzJob.syncLHBData();
    }

    @Test
    public void test(){
//        daylyService.cacheDayFollow();
//        daylyService.jyyMethod();
        daylyService.jyyMethodUpdate();
        //daylyService.cacheDayFollow();
//        daylyService.cache60Breach();
    }

    @Test
    public void test3(){
      //  ruleService.comparePrice(5);
        List<Share> shareList = new ArrayList<>();
        quartzJob.updateStartPrice(shareList);

    }

    @Test
    public void test1(){
        quartzJob.syncSecuritiesCode();
    }

    @Test
    public void test2() throws ParseException {
        ReentrantLock lock = new ReentrantLock();
        CountDownLatch count = new CountDownLatch(2);
        count.countDown();
        String[] arr = {"2020-03-05","2020-03-04","2020-03-03","2020-03-02","2020-02-28"};
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date date4 = smf.parse(arr[0]);
        List<Share> shareList4 = shareService.getShareByDate(date4);
        Date date3 = smf.parse(arr[1]);
        List<Share> shareList3 = shareService.getShareByDate(date3);

        List<Share> shares4 = new ArrayList<>();
        shareList4.forEach(share4 -> {
            if(null != share4.getCurrent() && null != share4.getPercent() && share4.getPercent() > 0){
                shareList3.forEach(share3 ->{
                    if (share3.getName().equals(share4.getName())){
                        if (share4.getCurrent() >share3.getCurrent()){
                            shares4.add(share3);
                        }
                    }
                } );
            }

        } );
        List<Share> shares3 = new ArrayList<>();
        Date date2 = smf.parse(arr[2]);
        List<Share> shareList2 = shareService.getShareByDate(date2);
        shares4.forEach(share -> {
            shareList2.forEach(share2 ->{
               if(share2.getName().equals(share.getName())){
                   if (share.getCurrent() > share2.getCurrent()){
                       shares3.add(share2);
                   }
               }
            });
        });

        List<Share> shares228 = new ArrayList<>();
        Date date228 = smf.parse(arr[3]);
        List<Share> shareList228 = shareService.getShareByDate(date228);
        shares3.forEach(share -> {
            shareList228.forEach(share228 ->{
                if(share228.getName().equals(share.getName())){
                    if (share.getCurrent() > share228.getCurrent()){
                        shares228.add(share228);
                    }
                }
            });
        });

        Map<String,String> map = new HashMap<>();
        List<Share> shares227 = new ArrayList<>();
        Date date227 = smf.parse(arr[4]);
        List<Share> shareList227 = shareService.getShareByDate(date227);
        shares228.forEach(share -> {
            shareList227.forEach(share227 ->{
                if(share227.getName().equals(share.getName())){
                    if (share.getCurrent() > share227.getCurrent()){
                        shares227.add(share227);
                        System.out.println(share227.getName());
                    }
                }
            });
        });
        System.out.println(shares227.size());
    }

    @Test
    public void shareServiceTest(){
        List<Share> sharePercent3DayIncr = shareService.getSharePercentDayIncr(3, null);
        List<Share> sharePercent5DayIncr = shareService.getSharePercentDayIncr(5, null);
        List<Share> sharePercent10DayIncr = shareService.getSharePercentDayIncr(10, null);
        System.out.println(sharePercent5DayIncr);
    }



}