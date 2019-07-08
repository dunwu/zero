package io.github.dunwu.zero.aaa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.dunwu.zero.aaa.dao.UserInfoDao;
import io.github.dunwu.zero.aaa.entity.UserInfo;
import io.github.dunwu.zero.aaa.service.UserInfoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-06-16
 */
@Service(version = "1.0.1")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {

}
