package microbucks.customerservice.controller;

import microbucks.customerservice.action.BuyerAction;
import microbucks.customerservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private BuyerAction buyerAction;

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String buyCoffee() {
        return buyerAction.buy();
    }
}
