package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Item;
import com.login.loginAPI.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/item/")
@RequiredArgsConstructor
public class itemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("create")
    public String createItem(Item item, Authentication authentication){
        item.setRegister(authentication.getName());

        if (itemService.createItem(item)){
            System.out.println("success");
        }else {
            System.out.println("fail");
        }
        return "redirect:/";
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
        System.out.println("search = " + search);
        List<Item> items;
        if (status.equals("name")){
            items = itemService.searchItemName(search);
        }else if (status.equals("price")){
            items = itemService.searchItemPrice(Integer.parseInt(search));
        }else {
            items = new ArrayList<>();
        }
        model.addAttribute("items",items);
        return "item/itemList";
    }

    @PostMapping("/editItem")
    public String editItem(Item item, Model model){
        Optional<Item> flag = itemService.item(item.getItemID());

        model.addAttribute("item",flag.get());
        return "item/editItem";
    }
    @PostMapping("/editItem.do")
    public String editItemDo(Item item, Model model){

        System.out.println(item);

        Item flag = itemService.item(item.getItemID()).get();
        flag.setName(item.getName());
        flag.setPrice(item.getPrice());
        flag.setVolume(item.getVolume());

        itemService.itemSave(flag);
        List<Item> items = itemService.itemAll();
        model.addAttribute("items",items);
        return "item/itemList";
    }
    @PostMapping("/deleteItem.do")
    public String deleteItem(Item item, Model model){
        System.out.println("item = " + item);
        itemService.deleteItem(item);

        List<Item> items = itemService.itemAll();
        model.addAttribute("items",items);
        return "item/itemList";
    }
}
