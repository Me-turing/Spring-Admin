package com.ocbc.les.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.les.modules.system.entity.CodeLibrary;
import com.ocbc.les.modules.system.service.CodeLibraryService;
import com.ocbc.les.modules.system.mapper.CodeLibraryMapper;
import org.springframework.stereotype.Service;

/**

* 针对表【code_library(码值库表)】的数据库操作Service实现
*/
@Service
public class CodeLibraryServiceImpl extends ServiceImpl<CodeLibraryMapper, CodeLibrary>
    implements CodeLibraryService{

}




