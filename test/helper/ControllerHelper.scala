package helper

import connector.UserConnector
import helpers.FakeUserConnectorImpl
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Injecting

import scala.concurrent.ExecutionContext.Implicits.global


trait ControllerHelper extends PlaySpec with GuiceOneAppPerTest with Injecting{
  val fakeUserConnectorImpl: UserConnector = new FakeUserConnectorImpl()
}
