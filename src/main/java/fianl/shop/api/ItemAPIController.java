package fianl.shop.api;


import fianl.shop.Result;
import fianl.shop.domain.item.IPTv;
import fianl.shop.domain.item.Item;
import fianl.shop.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemAPIController {

    private final ItemRepository itemRepository;

    @GetMapping("/items")
    public Result getAllItems(){
        List<Item> all = itemRepository.findAll();
        return new Result(all.size(),all,"find all items");
    }
//
//    @PostMapping("/items")
//    public Result createItems(@RequestBody ){
//        IPTv ipTv = new IPTv();
//        ipTv.setName();
//    }
}
