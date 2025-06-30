package forms

import play.api.data.Form
import play.api.data.Forms._

case class UserDataForm(name: String, age: Int)

object UserDataForm {
  def unapply(u: UserDataForm): Option[(String, Int)] = Some((u.name, u.age))

  val userFormWithConstraint: Form[UserDataForm] = Form(
    mapping(
      "name" -> nonEmptyText,
      "age" -> number(min = 5, max = 100)
    )(UserDataForm.apply)(UserDataForm.unapply)
  )
}