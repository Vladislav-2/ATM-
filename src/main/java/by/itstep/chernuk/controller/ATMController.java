package by.itstep.chernuk.controller;

import by.itstep.chernuk.domain.ATM;
import by.itstep.chernuk.repository.ATMRepo;
import by.itstep.chernuk.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("atm")
public class ATMController {

    @Autowired
    ATMService atmService;

    @GetMapping
    public String listOfATM(
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ){
        Page<ATM> page = atmService.getAll(pageable);
        model.addAttribute("url", "/atm");
        model.addAttribute("page", page);

        return "atm";
    }

    @PostMapping
    public String addOrUpdateATM(
            @Valid ATM atm,
            BindingResult bindingResult,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC)Pageable pageable
    ){
        Page<ATM> page = atmService.getAll(pageable);
        model.addAttribute("url", "/atm");
        model.addAttribute("page", page);
        if (bindingResult.hasErrors()){

            model.addAttribute("page", page);
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("anATM", atm);

            return "atm";
        } else {

            if (atmService.saveATM(atm)) {
                return "redirect:atm";

            } else {
                model.addAttribute("page", page);
                model.addAttribute("savingReport", "ATM with such DATA is exists!");
                model.addAttribute("anATM", atm);

                return "atm";
            }
        }
    }
}
