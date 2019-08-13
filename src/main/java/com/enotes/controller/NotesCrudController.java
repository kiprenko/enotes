package com.enotes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.enotes.note.Note;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/note")
@Controller
public class NotesCrudController {

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createNewNote(Model model) {
        LOGGER.info("Create page joined");
        model.addAttribute(model);
        return "notecrud/createNewNote.html";
    }

    @RequestMapping("/{id}")
    public String viewNote(@RequestParam Integer id){
        //get Note from DB using id;
        return "viewNote.html";
    }
}
