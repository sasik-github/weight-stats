package sasik.stats.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sasik.stats.config.security.UserPrincipal;
import sasik.stats.services.ChartService;
import sasik.stats.vo.ChartRangeRequest;

@Controller
@RequestMapping("chart")
public class ChartController
{
    private final ChartService chartService;

    public ChartController(ChartService chartService) {
        this.chartService = chartService;
    }

    @GetMapping("/")
    public String index(Model model,
                        ChartRangeRequest range,
                        @AuthenticationPrincipal UserPrincipal userPrincipal) {
        model.addAttribute("statByMonths", range.filter(chartService, userPrincipal.getUser()));
        model.addAttribute("ranges", ChartRangeRequest.Range.values());
        model.addAttribute("selectedRange", range.getRange());

        return "chart/chart";
    }
}
