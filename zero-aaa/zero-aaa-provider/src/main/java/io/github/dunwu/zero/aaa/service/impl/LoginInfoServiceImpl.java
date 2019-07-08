package io.github.dunwu.zero.aaa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.dunwu.zero.aaa.dao.LoginInfoDao;
import io.github.dunwu.zero.aaa.entity.LoginInfo;
import io.github.dunwu.zero.aaa.service.LoginInfoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p>
 * 登录信息表 服务实现类
 * </p>
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-06-16
 */
@Service(version = "1.0.1")
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoDao, LoginInfo> implements LoginInfoService {

}
