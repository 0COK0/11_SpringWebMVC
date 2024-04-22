package com.ohgiraffers.crud.menu.model.service;


import com.ohgiraffers.crud.menu.model.dao.MenuMapper;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {


    private final MenuMapper menuMapper;

    @Autowired
    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<MenuDTO> findAllMenu() {

        return menuMapper.findAllMenu();
    }

    public List<CategoryDTO> findAllCategory() {


        return menuMapper.findAllCategory();
    }

    @Transactional // 서비스 오기전에 이 어노테이션이 발생하면서 콜백을 할지 롤백을 할지
    public void registNewMenu(MenuDTO newMenu) {


        menuMapper.registNewMenu(newMenu);
    }

    @Transactional
    public void modifyOneMenu(MenuDTO modify) {

       menuMapper.modifyOneMenu(modify);
    }

    @Transactional
    public void deleteOneMenu(MenuDTO delete) {

        menuMapper.deleteOneMenu(delete);
    }
}
