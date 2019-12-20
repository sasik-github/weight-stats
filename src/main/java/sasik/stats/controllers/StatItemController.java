package sasik.stats.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sasik.stats.config.security.UserPrincipal;
import sasik.stats.domain.StatItem;
import sasik.stats.services.StatItemService;
import sasik.stats.vo.StatItemRequest;


@Controller
@RequestMapping("stats")
public class StatItemController
{
    private final StatItemService statItemService;

    public StatItemController(StatItemService statItemService) {

        this.statItemService = statItemService;
    }

    @GetMapping("/")
    public String index(Model model, Pageable pageable,
                        @AuthenticationPrincipal UserPrincipal user) {
        final Page<StatItem> all = statItemService.findAll(pageable, user.getUser());
        model.addAttribute("statsPage", all);

        return "stats/index";
    }

    @GetMapping("/create")
    public String createPage(Model model) {

        model.addAttribute("stat", new StatItemRequest());
        return "stats/create";
    }

    @PostMapping("/create")
    public String create(
        @ModelAttribute StatItemRequest statItem,
        @AuthenticationPrincipal UserPrincipal authentication
    ) {
        statItem.setUser(authentication.getUser());
        statItemService.create(statItem);
        return "redirect:/stats/";
    }
}
