package com.changgou.service.impl;

import com.changgou.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class  BrandServiceImpl implements  BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * @description: 查询所有
     *
     * @return: java.util.List<com.changgou.goods.pojo.Brand>
     * @author: yw
     * @date: 2021/4/7 19:15
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * @description:
     * @param id
     * @return: com.changgou.goods.pojo.Brand
     * @author: yw
     * @date: 2021/4/7 19:47
     */
    @Override
    public Brand findById(Integer id) {
        final Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }

    @Override
    public void addBrand(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void deleteBrand(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Brand> findList(Brand brand) {
        final Example example = new Example(Brand.class);
        final Example.Criteria criteria = example.createCriteria();

        if(brand != null) {
            if(!StringUtils.isEmpty(brand.getName())) {
             criteria.andLike("name", "%"+brand.getName()+"%");
            }
            if(!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());
            }
        }
        return brandMapper.selectByExample(example);
    }

    /**
     * @description:  分页操作
     * @param: page
     * @param: size
     * @return: com.github.pagehelper.PageInfo<com.changgou.goods.pojo.Brand>
     * @author: yw
     * @date: 2021/4/7 20:58
     */
    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        final List<Brand> brands = brandMapper.selectAll();
        return new PageInfo<>(brands);
    }


}
