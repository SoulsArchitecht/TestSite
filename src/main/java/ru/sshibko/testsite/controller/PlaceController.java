package ru.sshibko.testsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sshibko.testsite.model.entity.Place;
import ru.sshibko.testsite.service.PlaceService;


@Controller
@RequestMapping("/api/place")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @RequestMapping(value = "api/place/save", method = RequestMethod.POST)
    public String savePlace(@ModelAttribute("place") Place place) {
        placeService.savePlace(place);

        return "redirect:/";
    }

    @RequestMapping("/new")
    public String showNewPlaceForm(Model model) {
        Place place = new Place();
        model.addAttribute("place", place);

        return "new-place";
    }

    @RequestMapping("/edit/{city}")
    public ModelAndView showEditForm(@PathVariable(name = "city") String city) {
        ModelAndView modelAndView = new ModelAndView("edit-user");

        Place place = placeService.getPlaceByCity(city);
        modelAndView.addObject("place", place);

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{city}", method = RequestMethod.DELETE)
    public String deletePlace(@PathVariable(name = "city") String city) {
        placeService.deleteByCity(city);

        return  "redirect:/";
    }

//    @RequestMapping("/search")
//    public ModelAndView searchPlace (@RequestParam String keyword) {
//        List<User> searchResult = userService.search(keyword);
//        ModelAndView modelAndView = new ModelAndView("search-user");
//        modelAndView.addObject("searchResult", searchResult);
//
//        return modelAndView;
//    }
}
