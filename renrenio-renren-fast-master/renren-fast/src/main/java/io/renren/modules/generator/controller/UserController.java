package io.renren.modules.generator.controller;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.generator.form.LoginForm;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.UserEntity;
import io.renren.modules.generator.service.UserService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 用户表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:00:02
 */
@RestController
@RequestMapping("app/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 登录
     */
    @RequestMapping("/login")
    public R login(@RequestBody LoginForm form) {
        //验证接收的数据
        ValidatorUtils.validateEntity(form);
        HashMap map = new HashMap();
        map.put("appid", "wx996a2c219846efa6");
        map.put("secret", "40beb2a1850254f6354ebcd04dd47fd9");
        map.put("js_code", form.getCode());
        map.put("grant_type", "authorization_code");
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String res = HttpUtil.get(url, map); //使用renren-fast提供的工具发起get请求，获取到JSON字符串
        JSONObject json = JSONUtil.parseObj(res);   //通过工具类转换成JSON对象
        String openid = json.getStr("openid");  //从JSON对象里得到openid
        if(openid == null || openid.length() == 0) {
            return R.error("获取登录凭证错误");
        }
        // 查询数据库中是否存在此openid
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("open_id", openid);
        int count = userService.count(wrapper);
        //注册
        UserEntity user = new UserEntity();
        if(count == 0) {
            user.setOpenId(openid);
            user.setNickName(form.getNickName());
            user.setPhoto(form.getAvatarUrl());
            user.setCreateTime(new Date());
            userService.save(user); //执行插入
        }
        //登录
        user = userService.getOne(wrapper);
        long userId = user.getUserId();
        String token = jwtUtils.generateToken(userId);  //生成令牌
        return R.ok().put("token", token);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:user:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("generator:user:info")
    public R info(@PathVariable("userId") Long userId) {
        UserEntity user = userService.getById(userId);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:user:save")
    public R save(@RequestBody UserEntity user) {
        userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:user:update")
    public R update(@RequestBody UserEntity user) {
        userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:user:delete")
    public R delete(@RequestBody Long[] userIds) {
        userService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
