package services

import helpers.FakeUserConnector
import models.User
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.flatspec.AnyFlatSpec

import scala.concurrent.ExecutionContext.Implicits.global


class UserServiceSpec extends AnyFlatSpec with ScalaFutures {

  val fakeUserConnector = new FakeUserConnector()

  "Get a single not existing user" should "return error" in {
    val serviceUnderTest = UserService(fakeUserConnector)
    val actualResult = serviceUnderTest.getById("notExistingId")
    assert(actualResult.futureValue == Left("No user with notExistingId found."))
  }

  "Get a single existing use" should "return user" in {
    val serviceUnderTest = UserService(fakeUserConnector)
    val actualResult = serviceUnderTest.getById("1")
    assert(actualResult.futureValue == Right(User("1", "Alex")))
  }

}
