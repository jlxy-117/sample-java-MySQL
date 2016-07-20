package spartan117.sample.controller;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author yecq
 */
@Import({UsedOrderController.class,UserInfoController.class, UnusedOrderController.class, LoggedController.class, UserListController.class})
@ImportResource("classpath:applicationContext.xml")
public class AllController {

}
