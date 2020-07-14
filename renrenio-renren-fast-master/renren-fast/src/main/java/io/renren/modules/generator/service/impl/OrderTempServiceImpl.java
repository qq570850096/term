package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.OrderTempDao;
import io.renren.modules.generator.entity.OrderTempEntity;
import io.renren.modules.generator.service.OrderTempService;


@Service("orderTempService")
public class OrderTempServiceImpl extends ServiceImpl<OrderTempDao, OrderTempEntity> implements OrderTempService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderTempEntity> page = this.page(
                new Query<OrderTempEntity>().getPage(params),
                new QueryWrapper<OrderTempEntity>()
        );

        return new PageUtils(page);
    }

}