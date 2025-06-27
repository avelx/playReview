package services

import connector.{UserConnector, UserConnectorImpl}
import helpers.FakeUserConnectorImpl
import models.User
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserServiceSpec extends AnyFlatSpec with ScalaFutures with MockitoSugar {

  // Using fakes
  val fakeUserConnectorImpl: UserConnector = new FakeUserConnectorImpl()

  "Get a single not existing user" should "return error" in {
    val serviceUnderTest = UserService(fakeUserConnectorImpl)
    val actualResult = serviceUnderTest.getById("notExistingId")
    assert(actualResult.futureValue == Left("No user with notExistingId found."))
  }

  "Get a single existing use" should "return user" in {
    val serviceUnderTest = UserService(fakeUserConnectorImpl)
    val actualResult = serviceUnderTest.getById("1")
    assert(actualResult.futureValue == Right(User("1", "Alex")))
  }

}
