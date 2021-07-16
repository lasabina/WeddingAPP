package com.sda.weddingApp.controller;

import com.sda.weddingApp.model.Wedding;
import com.sda.weddingApp.service.AccountService;
import com.sda.weddingApp.service.WeddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/wedding")
@RequiredArgsConstructor
public class WeddingController {
    private final WeddingService weddingService;
    private final AccountService accountService;

    @GetMapping("/weddings")
    public String getAllWeddings(Model model) {
        List<Wedding> weddingList = weddingService.getAll();

        model.addAttribute("weddingsList", weddingList);
        return "wedding-list";
    }

    @GetMapping("/remove")
    public String removeWedding(@RequestParam(name = "id") Long identyfikatorek) {
        weddingService.removeWedding(identyfikatorek);

        return "redirect:/wedding";
    }

    @GetMapping("/edit")
    public String editWedding(Model model, @RequestParam(name = "id_wedding_to_edit") Long id) {
        Optional<Wedding> weddingToEdit = weddingService.getWeddingWithId(id);
        if (weddingToEdit.isPresent()) {
            model.addAttribute("new_wedding", weddingToEdit.get());

            log.info("Slub do edycji: " + weddingToEdit);
            return "wedding-add";
        }

        return "redirect:/wedding";
    }

    @GetMapping("/add")
    public String addWedding(Model model) {
        Wedding wedding = new Wedding();

        model.addAttribute("new_wedding", wedding);
        return "wedding-add";
    }

}