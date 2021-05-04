package com.changgou.controller;

import com.changgou.goods.pojo.Album;
import com.changgou.service.AlbumService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: changgou
 * @description:
 * @author: YW
 * @create: 2021-05-04 21:09
 **/
@RestController
@RequestMapping(value = "/album")
@CrossOrigin
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Album>> findPage(@RequestBody(required = false) Album album, @PathVariable int page, @PathVariable int size) {
        final PageInfo<Album> page1 = albumService.findPage(album, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功！", page1);
    }

    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Album>> findPage(@PathVariable int page, @PathVariable int size) {
        final PageInfo<Album> page1 = albumService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功！", page1);
    }

    @PostMapping(value = "/search")
    public Result<List<Album>> findList(@RequestBody Album album) {
        final List<Album> list = albumService.findList(album);
        return new Result<>(true, StatusCode.OK, "查询成功！", list);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        albumService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功！");
    }

    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Album album, @PathVariable Long id) {
        album.setId(id);
        albumService.update(album);
        return new Result(true, StatusCode.OK, "修改成功！");
    }

    @PostMapping
    public Result add(@RequestBody Album album) {
        albumService.add(album);
        return new Result(true, StatusCode.OK, "添加成功！");
    }

    @GetMapping(value = "/{id}")
    public Result<Album> findById(@PathVariable Long id) {
        final Album byId = albumService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功！", byId);
    }

    @GetMapping
    public Result<List<Album>> findAll() {
        final List<Album> all = albumService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功！", all);
    }
}
