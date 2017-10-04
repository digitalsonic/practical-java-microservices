package microbucks.orderservice.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceControllerDocsTest {
    @Rule
    public JUnitRestDocumentation restDocumentation =
            new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void testGetOrders() throws Exception {
        mockMvc.perform(get("/orders/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("index"));
    }

    @Test
    public void testGetOrder() throws Exception {
        mockMvc.perform(get("/orders/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("detail",
                        pathParameters(parameterWithName("id")
                                .description("the id of the order"))));
    }
}
