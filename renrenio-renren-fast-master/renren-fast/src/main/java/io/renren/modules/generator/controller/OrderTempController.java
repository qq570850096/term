package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.OrderTempEntity;
import io.renren.modules.generator.service.OrderTempService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 订单临时表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@RestController
@RequestMapping("app/ordertemp")
public class OrderTempController {
    @Autowired
    private OrderTempService orderTempService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:ordertemp:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderTempService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:ordertemp:info")
    public R info(@PathVariable("id") Integer id){
		OrderTempEntity orderTemp = orderTempService.getById(id);

        return R.ok().put("orderTemp", orderTemp);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:ordertemp:save")
    public R save(@RequestBody OrderTempEntity orderTemp){
		orderTempService.save(orderTemp);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:ordertemp:update")
    public R update(@RequestBody OrderTempEntity orderTemp){
		orderTempService.updateById(orderTemp);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:ordertemp:delete")
    public R delete(@RequestBody Integer[] ids){
		orderTempService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
