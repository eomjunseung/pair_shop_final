package fianl.shop.api;


import fianl.shop.Result;
import fianl.shop.domain.item.*;
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

    @PostMapping("/items/iptv")
    public Result createIPtV(@RequestBody IPTvForm ipTvForm){
        IPTv ipTv = new IPTv();
        ipTv.setName(ipTvForm.getName());
        ipTv.setPrice(ipTvForm.getPrice());
        ipTv.setIpTvGrade(ipTvForm.getIpTvGrade());
        itemRepository.save(ipTv);
        return new Result(1, ipTv, "create "+ipTvForm.getName()+"_iptv comp");
    }
    @PostMapping("/items/iot")
    public Result createIoT(@RequestBody IoTForm ioTForm){
        IoT iot = new IoT();
        iot.setName(ioTForm.getName());
        iot.setPrice(ioTForm.getPrice());
        iot.setIotCategory(iot.getIotCategory());
        itemRepository.save(iot);
        return new Result(1, iot, "create "+ioTForm.getName()+"_iot comp");

    }
    @PostMapping("/items/internet")
    public Result createInternet(@RequestBody InternetForm internetForm){
        Internet internet = new Internet();
        internet.setName(internetForm.getName());
        internet.setPrice(internetForm.getPrice());
        internet.setInternetType(internetForm.getInternetType());
        itemRepository.save(internet);

        return new Result(1, internet, "create "+internetForm.getName()+"_iptv comp");

    }
}
