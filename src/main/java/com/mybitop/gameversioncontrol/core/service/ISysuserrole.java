package com.mybitop.gameversioncontrol.core.service;

import com.mybitop.gameversioncontrol.core.entity.Sysuserrole;

import java.util.List;

public interface ISysuserrole {
    int insert(Sysuserrole record);

    List<Sysuserrole> select(long uid);
}
