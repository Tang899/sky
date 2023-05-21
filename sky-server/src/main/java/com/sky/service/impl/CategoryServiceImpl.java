package com.sky.service.impl;


import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<Category> selectCategoryByPage(CategoryPageQueryDTO categoryPageQueryDTO) {

        return categoryMapper.queryCategoryByPage(categoryPageQueryDTO);

    }
}
