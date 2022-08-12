package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Item;
import com.login.loginAPI.domain.Member;
import com.login.loginAPI.repository.ItemRepository;
import com.login.loginAPI.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class itemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/create")
    public String createItem(Item item){
        Date day = new Date();
        item.setCreatedDate(day);
        item.setLastModifiedDate(day);

        if (itemService.createItem(item)){
            System.out.println("success");
        }else {
            System.out.println("fail");
        }
        return "home";
    }

    @GetMapping("/itemList")
    public String itemList(Model model){
        List<Item> items = itemService.itemAll();
        model.addAttribute("items",items);
        return "item/itemList";
    }

    @PostMapping("/searchItem")
    public String searchItem(@RequestParam(value = "search", defaultValue = "") String search,
                             @RequestParam(value = "status", defaultValue = "") String status,Model model){
        List<Item> items;
        if (status.equals("name")){
            items = itemService.searchItemName(search);
        }else if (status.equals("age")){
            items = itemService.searchItemPrice(Integer.parseInt(search));
        }else {
            items = new ArrayList<>();
        }
        model.addAttribute("items",items);
        return "item/itemList";
    }
}
