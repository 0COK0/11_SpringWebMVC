package com.ohgiraffers.crud.menu.controller;


import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/list")
    public String findMenuList(Model model) {

        List<MenuDTO> menuList = menuService.findAllMenu();

        // 조회하려고
        for(MenuDTO menus : menuList) {
            System.out.println("menus = " + menus);
        }

        model.addAttribute("menuList", menuList);

        return "menu/list";
    }

    @GetMapping("/regist")
    public void registPage() {}

    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {


        menuService.findAllCategory().forEach(System.out::println);
        return menuService.findAllCategory();
    }

    @PostMapping("/regist") // 리다이렉트어트리뷰트 객체를 이용해야함
    public String registMenu(MenuDTO newMenu, RedirectAttributes rttr) {

        menuService.registNewMenu(newMenu);

        rttr.addFlashAttribute("successMessage", "신규 등록에 성공!");

        return "redirect:/menu/list";
    }

    @GetMapping("/modify")
    public void modifyPage() {}

    @PostMapping("/modify")
    public String modifyMenu(MenuDTO modify, RedirectAttributes rttr) {

        menuService.modifyOneMenu(modify);

        rttr.addFlashAttribute("successMessage", "메뉴 수정 성공!");

        return "redirect:/menu/list";
    }

    @GetMapping("/delete")
    public void deletePage() {}

    @PostMapping("/delete")
    public String deleteMenu(MenuDTO delete, RedirectAttributes rttr) {

        menuService.deleteOneMenu(delete);

        rttr.addFlashAttribute("successMessage", "메뉴 삭제 성공!");

        return "redirect:/menu/list";
    }
}
