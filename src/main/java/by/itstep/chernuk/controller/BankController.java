package by.itstep.chernuk.controller;

import by.itstep.chernuk.domain.Bank;
import by.itstep.chernuk.service.BankService;
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

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("bank")
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping
    public String listOfBank(
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC)Pageable pageable
    ){
        Page<Bank> page = bankService.getAll(pageable);
        model.addAttribute("url", "/bank");
        model.addAttribute("page", page);

        return "bank";
    }

    @PostMapping
    public String addOrUpdateBank(
            @Valid Bank bank,
            BindingResult bindingResult,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC)Pageable pageable
    ){
        Page<Bank> page = bankService.getAll(pageable);
        model.addAttribute("url", "/bank");
        model.addAttribute("page", page);

        if (bindingResult.hasErrors()){

            model.addAttribute("page", page);
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("aBank", bank);

            return "bank";
        } else {
            if (bankService.blank(bank)) {
                if (bankService.saveBank(bank)) {
                    return "redirect:bank";

                } else {
                    model.addAttribute("page", page);
                    model.addAttribute("savingReport", "Bank with such DATA is exists!");
                    model.addAttribute("aBank", bank);

                    return "bank";
                }
            } else {
                model.addAttribute("page", page);
                model.addAttribute("savingReport", "Field must be fill in correctly!");
                model.addAttribute("aBank", bank);

                return "bank";
            }
        }
    }
}