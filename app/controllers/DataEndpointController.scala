package controllers

import com.google.inject.Inject
import jakarta.inject.Singleton
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, Request}
import services.UserService

import scala.concurrent.ExecutionContext

@Singleton
class DataEndpointController @Inject()(
                                        val controllerComponents: ControllerComponents,
                                        val userService: UserService
                                      )(implicit ec: ExecutionContext)  extends BaseController {
  var counter: Int = 0
  private val logger = Logger(getClass)

  import models.User._

  def getUsers: Action[AnyContent] = Action.async  { _ =>
    counter += 1
    logger.info(s"Users request: ${counter}")
    userService.getAll.map { users =>
      val usersAsJson = Json.toJson(users)
      Ok(usersAsJson)
    }
  }

}
