package com.mybitop.gameversioncontrol.core.mapper;


import com.mybitop.gameversioncontrol.core.entity.Sysuserrole;

import java.util.List;

public interface SysuserroleMapper {


    int insert(Sysuserrole record);

    List<Sysuserrole> select(long uid);

}