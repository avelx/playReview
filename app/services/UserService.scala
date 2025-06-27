package services

import com.google.inject.Singleton
import connector.{UserConnector, UserConnectorImpl}
import models.User

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserService @Inject()(val connector: UserConnectorImpl)
                 (implicit val ec: ExecutionContext){

  def getAll: Future[List[User]] = {
    connector.getAllUsers.flatMap{
      case Right(users) =>
        Future.successful(users)
      case Left(err) =>
        throw err
    }
  }

  def getById(userId: String): Future[Either[String, User]] = {
    connector.getUserById(userId)
  }
}
