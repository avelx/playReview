package controllers

import javax.inject.*
import play.api.*
import play.api.mvc.*
import services.{PlaceService, UserService}
import viewModels.UserViewModel

import scala.concurrent.ExecutionContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(
                                val controllerComponents: ControllerComponents,
                                val userService: UserService,
                                val placeService: PlaceService
                              )(implicit ex: ExecutionContext) extends BaseController {

  private val logger = Logger(getClass)

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index(name: Option[String]): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    logger.info(s"[HomeController]::index => $name")
    val usersFuture = userService.getAll
    usersFuture.map{ users =>
      val viewModel : UserViewModel = UserViewModel(users = users)
      Ok(views.html.index(viewModel))
    }
  }

  def place(id: String): Action[AnyContent] = Action.async{ implicit request: Request[AnyContent] =>
    logger.info(s"[HomeController]::places => $id")
    val allPlacesFuture = placeService.getAll
    allPlacesFuture.map{ places =>
      Ok(views.html.places(places))
    }
  }


}
