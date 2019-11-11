package by.itstep.chernuk.controller;

import by.itstep.chernuk.domain.BankCard;
import by.itstep.chernuk.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("card")
public class BankCardController {

    @Autowired
    BankCardService bankCardService;

    @GetMapping
    public String listOfBankCard(
            Model model,
            @RequestParam (required = false, defaultValue = "") BankCard remove,
            @RequestParam (required = true, defaultValue = "") BankCard reaper,
            @RequestParam (required = false, defaultValue = "") BankCard block,
            @RequestParam (required = true, defaultValue = "") BankCard unBlock,
            @PageableDefault(sort = {"cardUser"}, direction = Sort.Direction.ASC) Pageable pageable
    ){
        Page<BankCard> page = bankCardService.getAll(pageable);
        model.addAttribute("url", "/card");
        model.addAttribute("page", page);

        /*
        Remove exists card
         */
        if (remove != null){
            bankCardService.remove(remove);
        }
        /*
        Reaper exists card
         */
        if (reaper != null) {
            bankCardService.reaper(reaper);
        }
        /*
        Block exists card
         */
        if (block != null){
            bankCardService.block(block);
        }
        /*
        Un block exists card
         */
        if (unBlock != null){
            bankCardService.unBlock(unBlock);
        }

        return "card";
    }

    @PostMapping
    public String addOrUpdateBankCard(
            @Valid BankCard bankCard,
            BindingResult bindingResult,
            Model model,
            @PageableDefault(sort = {"cardUser"}, direction = Sort.Direction.ASC)Pageable pageable
    ){
        Page<BankCard> page = bankCardService.getAll(pageable);
        model.addAttribute("url", "/card");
        model.addAttribute("page", page);
        if (bindingResult.hasErrors()){

            model.addAttribute("page", page);
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("aCard", bankCard);

            return "card";
        } else {

            if (bankCardService.saveBankCard(bankCard)) {
                return "redirect:card";

            } else {
                model.addAttribute("page", page);
                model.addAttribute("savingReport", "Card with such DATA is exists!");
                model.addAttribute("aCard", bankCard);

                return "card";
            }
        }
    }
}
