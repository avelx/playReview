package helper

import connector.UserConnector
import helpers.FakeUserConnectorImpl
import models.User
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.when
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Injecting

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


trait ControllerHelper extends PlaySpec with MockitoSugar with GuiceOneAppPerTest with Injecting{
  val fakeUserConnectorImpl: UserConnector = new FakeUserConnectorImpl()

  def setUpMock(): UserConnector = {
    val mockUserConnectorImpl: UserConnector = mock[UserConnector]
    when(mockUserConnectorImpl.getUserById(anyString())).thenReturn(Future.successful(Right(User("1", "TestUser"))))
    when(mockUserConnectorImpl.getAllUsers).thenReturn(Future.successful(Right(List(User("1", "TestUser")))))
    mockUserConnectorImpl
  }
}
