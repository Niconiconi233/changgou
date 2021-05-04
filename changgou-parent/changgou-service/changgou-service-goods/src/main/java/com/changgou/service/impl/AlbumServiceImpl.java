package com.changgou.service.impl;

import com.changgou.dao.AlbumMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.service.AlbumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.nio.file.Path;
import java.util.List;

/**
 * @program: changgou
 * @description:
 * @author: YW
 * @create: 2021-05-04 20:49
 **/
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public PageInfo<Album> findPage(Album album, int page, int size) {
        PageHelper.startPage(page, size);
        final Example example = createExample(album);
        return new PageInfo<Album>(albumMapper.selectByExample(example));

    }

    @Override
    public PageInfo<Album> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<Album>(albumMapper.selectAll());
    }

    @Override
    public List<Album> findList(Album album) {
        final Example example = createExample(album);
        return albumMapper.selectByExample(example);
    }

    @Override
    public void delete(Long id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }

    @Override
    public void add(Album album) {
        albumMapper.insertSelective(album);
    }

    @Override
    public Album findById(Long id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }

    private Example createExample(Album album) {
        final Example example = new Example(Album.class);
        final Example.Criteria criteria = example.createCriteria();

        if(album != null) {
            if(!StringUtils.isEmpty(album.getId())) {
                criteria.andEqualTo("id", album.getId());
            }
            if(!StringUtils.isEmpty(album.getTitle())) {
                criteria.andLike("title", "%"+album.getTitle()+"%");
            }
            if(!StringUtils.isEmpty(album.getImage())) {
                criteria.andEqualTo("image", album.getImage());
            }
            if(!StringUtils.isEmpty(album.getImageItems())) {
                criteria.andEqualTo("imageItems", album.getImageItems());
            }
        }
        return example;
    }
}
