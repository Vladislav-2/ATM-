package by.itstep.chernuk.controller;

import by.itstep.chernuk.domain.BankAccount;
import by.itstep.chernuk.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    @RequestMapping("account")
    public String listOfAccount(
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC)Pageable pageable
            ){
        Page<BankAccount> page = bankAccountService.getAll(pageable);
        model.addAttribute("url", "/account");
        model.addAttribute("page", page);

        return "account";
    }

    @PostMapping
    public String addOrUpdateAccount(
            @Valid BankAccount bankAccount,
            BindingResult bindingResult,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC)Pageable pageable
    ){
        Page<BankAccount> page = bankAccountService.getAll(pageable);
        model.addAttribute("url", "/account");
        model.addAttribute("page", page);

        if (bindingResult.hasErrors()){

            model.addAttribute("page", page);
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("anAccount", bankAccount);

            return "account";
        } else {

            if (bankAccountService.blank(bankAccount)) {
                if (bankAccountService.saveBankAccount(bankAccount)) {
                    return "redirect:account";

                } else {
                    model.addAttribute("page", page);
                    model.addAttribute("savingReport", "Bank Account with such DATA is exists!");
                    model.addAttribute("anAccount", bankAccount);

                    return "account";
                }
            } else {
                model.addAttribute("page", page);
                model.addAttribute("savingReport", "Field must be fill in correctly!");
                model.addAttribute("anAccount", bankAccount);

                return "account";
            }
        }
    }
}
