package com.changgou.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
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
 * @create: 2021-04-07 16:58
 **/
@RestController
@RequestMapping(value ="/brand")
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public Result<List<Brand>> findAll() {
        final List<Brand> all = brandService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功！", all);
    }

    @GetMapping(value = "/{id}")
    public Result<Brand> findById(@PathVariable(value = "id") Integer id) {
        final Brand byId = brandService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功！", byId);
    }

    @PostMapping
    public Result addBrand(@RequestBody Brand brand) {
        brandService.addBrand(brand);
        return new Result(true, StatusCode.OK, "添加成功！");
    }

    @PutMapping
    public Result updateBrand(@RequestBody Brand brand) {
        brandService.updateBrand(brand);
        return new Result(true, StatusCode.OK, "修改成功！");
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteBrand(@PathVariable(value = "id") Integer id) {
        brandService.deleteBrand(id);
        return new Result(true, StatusCode.OK, "删除成功！");
    }

    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand) {
        final List<Brand> list = brandService.findList(brand);
        return new Result<>(true, StatusCode.OK, "查询成功！", list);
    }

    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable("page") Integer page,@PathVariable("size") Integer size) {
        final PageInfo<Brand> page1 = brandService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "分页查询成功！", page1);
    }

    @GetMapping(value = "fxxk")
    public String fxxk() {
        int j = 1/0;
        return "fxxk";
    }
}
