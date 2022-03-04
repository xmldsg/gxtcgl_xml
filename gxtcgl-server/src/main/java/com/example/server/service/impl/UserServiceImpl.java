package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.config.security.component.JwtTokenUtil;
import com.example.server.entity.Role;
import com.example.server.entity.User;
import com.example.server.entity.UserInfo;
import com.example.server.mapper.RoleMapper;
import com.example.server.mapper.UserMapper;
import com.example.server.service.IUserService;
import com.example.server.utils.PageBean;
import com.example.server.utils.RespBean;
import com.example.server.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) {
            return RespBean.error("验证码错误，请重新输入");
        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用，请联系管理员");
        }

        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails
                , null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<String, String>();

        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);

        return RespBean.success("登录成功", tokenMap);
    }


    @Override
    public Role getRoles(Integer id) {
        return roleMapper.getRoles(id);
    }

    @Override
    public List<User> getAllAdmin(String keywords) {

        return userMapper.getAllAdmin(UserUtils.getCurrentUser().getId(), keywords);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public PageBean getUserPage(Integer currentPage, Integer size, UserInfo userInfo) {
        //开启分页
        Page<UserInfo> page = new Page<>(currentPage, size);
        IPage<UserInfo> userPage = userMapper.getUserPage(page, userInfo);
        PageBean respPageBean = new PageBean(userPage.getTotal(), userPage.getRecords());
        return respPageBean;

    }

    @Override
    public List<UserInfo> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public RespBean updateAdminPass(String oldPass, String pass, Integer id) {
        User user = userMapper.selectById(id);
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        if (encoder.matches(oldPass,user.getPassword())){
            user.setPassword(encoder.encode(pass));
            int i = userMapper.updateById(user);
            if (i==1){
                return RespBean.success("更新成功");
            }
        }
        return RespBean.error("更新失败");
    }


}
