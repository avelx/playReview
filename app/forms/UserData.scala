package forms

import play.api.data.Form
import play.api.data.Forms._

case class UserData(name: String, age: Int)

object UserData {
  def unapply(u: UserData): Option[(String, Int)] = Some((u.name, u.age))

//  val userForm = Form(
//    mapping(
//      "name" -> text,
//      "age" -> number
//    )(UserData.apply)(UserData.unapply)
//  )

  val userFormWithConstraint: Form[UserData] = Form(
    mapping(
      "name" -> nonEmptyText,
      "age" -> number(min = 0, max = 100)
    )(UserData.apply)(UserData.unapply)
  )
}