package com.api.service.shareServcie;

import com.api.dao.mapper.CategoryMapper;
import com.api.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 李显琪 on 2021/9/17.
 */

@Component
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public void insertRecord(List<Category> list) {
        categoryMapper.insertList(list);

    }
}
