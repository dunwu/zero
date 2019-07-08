package io.github.dunwu.zero.aaa.controller;

import io.github.dunwu.core.*;
import io.github.dunwu.zero.aaa.entity.LoginInfo;
import io.github.dunwu.zero.aaa.service.LoginInfoService;
import io.github.dunwu.web.controller.CrudController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * 登录信息表 前端控制器
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-06-16
 */
@RestController
@RequestMapping("/login-info")
@Api(tags = "LoginInfo", description = "登录信息表 CRUD Controller")
public class LoginInfoController extends CrudController<LoginInfo> {
    private final LoginInfoService service;

    @Autowired
    public LoginInfoController(LoginInfoService service) {
        super(service);
        this.service = service;
    }

    @Override
    @GetMapping("count")
    @ApiOperation(value = "返回符合查询条件的 LoginInfo 记录数，如果 entity 为 null，返回所有记录数")
    public DataResult<Integer> count(LoginInfo entity) {
        return super.count(entity);
    }

    @Override
    @GetMapping("list")
    @ApiOperation(value = "返回符合查询条件的 LoginInfo 记录，如果 entity 为 null，返回所有记录")
    public DataListResult<LoginInfo> list(LoginInfo entity) {
        return super.list(entity);
    }

    @Override
    @GetMapping("page")
    @ApiOperation(value = "分页查询符合条件的 LoginInfo 记录，如果 entity 为 null，返回所有记录的分页查询结果")
    public PageResult<LoginInfo> page(LoginInfo entity, Page page) {
        return super.page(entity, page);
    }

    @Override
    @PostMapping("save")
    @ApiOperation(value = "插入一条 LoginInfo 记录（选择字段）")
    public BaseResult save(LoginInfo entity) {
        return super.save(entity);
    }

    @Override
    @PostMapping("saveBatch")
    @ApiOperation(value = "批量添加 LoginInfo 记录（选择字段）")
    public BaseResult saveBatch(Collection<LoginInfo> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @PostMapping("remove")
    @ApiOperation(value = "删除符合条件的 LoginInfo 记录")
    public BaseResult remove(LoginInfo entity) {
        return super.remove(entity);
    }

    @Override
    @PostMapping("removeById")
    @ApiOperation(value = "根据 ID 删除一条 LoginInfo 记录")
    public BaseResult removeById(Long id) {
        return super.removeById(id);
    }

    @Override
    @PostMapping("removeByIds")
    @ApiOperation(value = "根据 ID 批量删除 LoginInfo 记录")
    public BaseResult removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @PostMapping("update")
    @ApiOperation(value = "根据 origin 条件，更新一条 LoginInfo 记录 target")
    public BaseResult update(
        @RequestBody @ApiParam(name = "target", value = "LoginInfo 对象（json 格式）", required = true) LoginInfo target, LoginInfo origin) {
        return super.update(target, origin);
    }

    @Override
    @PostMapping("updateById")
    @ApiOperation(value = "根据 ID 选择修改一条 LoginInfo 记录")
    public BaseResult updateById(LoginInfo entity) {
        return super.updateById(entity);
    }

    @Override
    @PostMapping("updateBatchById")
    @ApiOperation(value = "根据 ID 批量修改 LoginInfo 记录")
    public BaseResult updateBatchById(Collection<LoginInfo> entityList) {
        return super.updateBatchById(entityList);
    }
}
