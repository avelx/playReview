package controllers

import forms.UserDataForm
import forms.UserDataForm.*
import jakarta.inject.{Inject, Singleton}
import play.api.Logger
import play.api.i18n.{I18nSupport, Langs, MessagesApi}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import scala.concurrent.ExecutionContext

@Singleton
class UserController @Inject() (
                                 val controllerComponents: ControllerComponents,
                                 langs: Langs, messagesApi: MessagesApi
                               )(implicit ex: ExecutionContext) extends BaseController with I18nSupport  {

  private val logger = Logger(getClass)

  def show: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.user(userFormWithConstraint.fill(UserDataForm("", 0))))
  }

  def update: Action[AnyContent] = Action { implicit request =>

    userFormWithConstraint
      .bindFromRequest()
      .fold(
        formWithErrors => {
          // binding failure, you retrieve the form containing errors:
          BadRequest(views.html.user(formWithErrors))
        },
        userData => {
          /* binding success, you get the actual value. */
          //val newUser = models.UserData(userData.name, userData.age)
          //val id = models.User.create(newUser)
          //Redirect(routes.Application.home(id))
          logger.info(s"User data: $userData")
          Redirect(routes.HomeController.index(Some("name")))
        }
      )
  }


}
