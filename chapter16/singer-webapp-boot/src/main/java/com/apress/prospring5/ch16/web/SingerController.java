package com.apress.prospring5.ch16.web;

import com.apress.prospring5.ch16.entities.Singer;
import com.apress.prospring5.ch16.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by iuliana.cosmina on 7/16/17.
 */
@Controller
@RequestMapping(value = "/singers")
public class SingerController {

	private final Logger logger = LoggerFactory.getLogger(SingerController.class);
	@Autowired SingerService singerService;

	@GetMapping
	public String list(Model uiModel) {
		logger.info("Listing singers");
		List<Singer> singers = singerService.findAll();
		uiModel.addAttribute("singers", singers);
		logger.info("No. of singers: " + singers.size());
		return "singers";
	}

	@GetMapping(value = "/{id}")
	public String show(@PathVariable("id") Long id, Model uiModel) {
		Singer singer = singerService.findById(id);
		uiModel.addAttribute("singer", singer);
		return "show";
	}

	@GetMapping(value = "/edit/{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("singer", singerService.findById(id));
		return "update";
	}

	@GetMapping(value = "/new")
	public String createForm(Model uiModel) {
		Singer singer = new Singer();
		uiModel.addAttribute("singer", singer);
		return "update";
	}

	@PostMapping
	public String saveSinger(@Valid Singer singer) {
		singerService.save(singer);
		return "redirect:/singers/" + singer.getId();
	}

	@GetMapping(value = "/delete/{id}")
	public String createDeleteForm(@PathVariable("id") Long id, Model model) {
        logger.debug("Delete form created");
		Singer singer = singerService.findById(id);
		model.addAttribute("singer", singer);
		return "delete";
	}

	@PostMapping(value = "/delete/{id}")
	public String deleteSinger(@Valid Long id) {
	    logger.debug("Now in delete");
		singerService.delete(singerService.findById(id));
		return "redirect:/singers/";
	}
}
