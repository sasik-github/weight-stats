package sasik.stats.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class DefaultControllerTest
{


    @Test
    void home() throws Exception {


        MockMvc build = MockMvcBuilders.standaloneSetup(new DefaultController()).build();

        build.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/stats/"));
    }
}
