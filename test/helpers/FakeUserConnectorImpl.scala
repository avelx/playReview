package helpers

import connector.UserConnector
import models.User

import scala.concurrent.{ExecutionContext, Future}


class FakeUserConnectorImpl(implicit ec: ExecutionContext) extends UserConnector {

    private val userList: List[User] = List(
      User("1", "Alex"),
      User("3", "Fox"),
      User("4", "Mod"),
      User("7", "Dog"),
      User("9", "Sky"),
      User("11", "Plane")
    )

    def getUserById(userId: String): Future[Either[String, User]] = {
      Future.successful {
        userList.find(p => p.id == userId) match {
          case Some(user) => Right(user)
          case None => Left(s"No user with $userId found.")
        }
      }
    }

    def getAllUsers: Future[Either[Throwable, List[User]]] = {
      Future.successful(
        Right(userList)
      )
    }

  }
