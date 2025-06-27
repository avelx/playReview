package models

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

case class User(id: String, name: String)

object User {

  implicit val userReads: Reads[User] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "name").read[String]
    )(User.apply _)

  implicit val userWrites: Writes[User] = (
    (JsPath \ "id").write[String] and
        (JsPath \ "name").write[String]
      )(x => (x.id, x.name))


}