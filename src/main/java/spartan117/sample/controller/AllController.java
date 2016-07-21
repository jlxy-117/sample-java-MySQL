package spartan117.sample.controller;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import spartan117.sample.controllerAdmin.AdminController;
import spartan117.sample.controllerAdmin.CityStationController;
import spartan117.sample.controllerAdmin.LineController;
import spartan117.sample.controllerAdmin.SpUserController;
import spartan117.sample.controllerAdmin.userController;

/**
 *
 * @author yecq
 */
@Import({UsedOrderController.class,UserInfoController.class, UnusedOrderController.class, LoggedController.class, UserListController.class,CashBindController.class,userController.class,LineController.class,SpUserController.class,CityStationController.class,AdminController.class})
@ImportResource("classpath:applicationContext.xml")
public class AllController {

}
