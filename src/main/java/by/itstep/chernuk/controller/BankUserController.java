package by.itstep.chernuk.controller;

import by.itstep.chernuk.domain.BankUser;
import by.itstep.chernuk.service.BankUserService;
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
@RequestMapping("bankUser")
public class BankUserController {

    @Autowired
    BankUserService bankUserService;

    @GetMapping
    public String listOfBankUser(
            Model model,
            @RequestParam(required = false, defaultValue = "") BankUser remove,
            @RequestParam (required = true, defaultValue = "") BankUser reaper,
            @PageableDefault(sort = {"surname"}, direction = Sort.Direction.ASC) Pageable pageable
    ){
        Page<BankUser> page = bankUserService.getAll(pageable);
        model.addAttribute("url", "/bankUser");
        model.addAttribute("page", page);

        /*
        Remove exists bankUser
         */
        if (remove != null){
            bankUserService.remove(remove);
        }
        /*
        Reaper exists bankUser
         */
        if (reaper != null) {
            bankUserService.reaper(reaper);
        }

        return "bankUser";
    }

    @PostMapping
    public String addOrUpdateBankUser(
            @Valid BankUser bankUser,
            BindingResult bindingResult,
            Model model,
            @PageableDefault(sort = {"surname"}, direction = Sort.Direction.ASC) Pageable pageable
    ){
        Page<BankUser> page = bankUserService.getAll(pageable);
        model.addAttribute("url", "/bankUser");
        model.addAttribute("page", page);

        if (bindingResult.hasErrors()){

            model.addAttribute("page", page);
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("aBankUser", bankUser);

            return "bankUser";
        } else {

            if (bankUserService.blank(bankUser)) {
                if (bankUserService.saveCardUser(bankUser)) {
                    return "redirect:bankUser";

                } else {
                    model.addAttribute("page", page);
                    model.addAttribute("savingReport", "Bank user with such DATA is exists!");
                    model.addAttribute("aBankUser", bankUser);

                    return "bankUser";
                }
            } else {
                model.addAttribute("page", page);
                model.addAttribute("savingReport", "Field must be fill in correctly!");
                model.addAttribute("aBankUser", bankUser);

                return "bankUser";
            }
        }
    }
}
