package controllers

import com.google.inject.Inject
import jakarta.inject.Singleton
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, Request}
import services.UserService

import scala.concurrent.ExecutionContext

@Singleton
class DataEndpointController @Inject()(
                                        val controllerComponents: ControllerComponents,
                                        val userService: UserService
                                      )(implicit ec: ExecutionContext)  extends BaseController {

  import models.User._

  def getUsers: Action[AnyContent] = Action.async  { _ =>
    userService.getAll.map { users =>
      val usersAsJson = Json.toJson(users)
      Ok(usersAsJson)
    }
  }

}
