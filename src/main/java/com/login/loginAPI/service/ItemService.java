package com.login.loginAPI.service;

import com.login.loginAPI.domain.Item;
import com.login.loginAPI.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public boolean createItem(Item item){
        try {
            itemRepository.save(item);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Item> itemAll(){
        return itemRepository.findAll();
    }
    public List<Item> searchItemName(String search){return itemRepository.findItemByNameContaining(search);}
    public List<Item> searchItemPrice(int search){return itemRepository.findItemByPriceLike(search);}
}
