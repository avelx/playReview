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
                                userService: UserService,
                                placeService: PlaceService
                              )(implicit ex: ExecutionContext) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action.async { implicit request: Request[AnyContent] =>
    val usersFuture = userService.getAll
    usersFuture.map{ users =>
      val viewModel : UserViewModel = UserViewModel(users = users)
      Ok(views.html.index(viewModel))
    }
  }

  def place() = Action.async{ implicit  request: Request[AnyContent] =>
    val allPlacesFuture = placeService.getAll
    allPlacesFuture.map{ places =>
      Ok(views.html.places(places))
    }
  }


}
