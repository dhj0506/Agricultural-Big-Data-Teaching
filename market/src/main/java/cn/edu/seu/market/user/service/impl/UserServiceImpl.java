package cn.edu.seu.market.user.service.impl;

import cn.edu.seu.market.user.entity.User;
import cn.edu.seu.market.user.mapper.UserMapper;
import cn.edu.seu.market.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @since 2020-02-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
