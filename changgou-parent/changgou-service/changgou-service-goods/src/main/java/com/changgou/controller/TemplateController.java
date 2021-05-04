package com.changgou.controller;

import com.changgou.goods.pojo.Template;
import com.changgou.service.TemplateService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;

/**
 * @program: changgou
 * @description:
 * @author: YW
 * @create: 2021-05-04 22:24
 **/
@RestController
@RequestMapping(value = "/template")
@CrossOrigin
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Template>> findPage(@RequestBody(required = false) Template template, @PathVariable int page, @PathVariable int size) {
        final PageInfo<Template> page1 = templateService.findPage(template, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功！", page1);
    }

    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Template>> findPage(@PathVariable int page, @PathVariable int size) {
        final PageInfo<Template> page1 = templateService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功！", page1);
    }

    @PostMapping(value = "/search")
    public Result<List<Template>> findList(@RequestBody Template template) {
        final List<Template> list = templateService.findList(template);
        return new Result<>(true, StatusCode.OK, "查询成功！", list);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        templateService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功！");
    }

    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Template template, @PathVariable Integer id) {
        template.setId(id);
        templateService.update(template);
        return new Result<>(true, StatusCode.OK, "修改成功！");
    }

    @PostMapping
    public Result add(@RequestBody Template template) {
        templateService.add(template);
        return new Result<>(true, StatusCode.OK, "添加成功！");
    }

    @GetMapping
    public Result findAll() {
        final List<Template> all = templateService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功！", all);
    }
}
