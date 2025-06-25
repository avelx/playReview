package services

import com.google.inject.Singleton
import models.User

import scala.concurrent.Future

@Singleton
class UserService {

  def getAll: Future[List[User]] = {
    val userList : List[User] = List(
      User("1", "Alex"),
      User("3", "Fox"),
      User("4", "Dog")
    )
    Future.successful(userList)
  }
}
