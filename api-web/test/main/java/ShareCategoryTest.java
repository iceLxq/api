package main.java;

import com.api.domain.Category;
import com.api.htmlParse.XQHqParse;
import com.api.service.shareServcie.CategoryService;
import main.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by 李显琪 on 2021/9/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ShareCategoryTest {


    @Autowired
    private CategoryService categoryService;

    @Test
    public void insertShareCategory(){
        List<Category> shareCategoryList = XQHqParse.parseHtml(null);
        categoryService.insertRecord(shareCategoryList);
    }

}
