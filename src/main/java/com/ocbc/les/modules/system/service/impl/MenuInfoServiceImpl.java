package com.ocbc.les.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.les.modules.system.entity.MenuInfo;
import com.ocbc.les.modules.system.service.MenuInfoService;
import com.ocbc.les.modules.system.mapper.MenuInfoMapper;
import org.springframework.stereotype.Service;

/**

* 针对表【menu_info(菜单表)】的数据库操作Service实现
*/
@Service
public class MenuInfoServiceImpl extends ServiceImpl<MenuInfoMapper, MenuInfo>
    implements MenuInfoService{

}




