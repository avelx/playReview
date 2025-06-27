package controllers

import helper.ControllerHelper
import org.scalatestplus.play.*
import play.api.test.*
import play.api.test.Helpers.*
import services.{PlaceService, UserService}

import scala.concurrent.ExecutionContext

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class HomeControllerSpec extends ControllerHelper {

  "HomeController GET" should {

    "render the index page from a new instance of controller" in {
      val ec = inject[ExecutionContext]
      val userService = UserService(fakeUserConnectorImpl)(ec)
      val placeService = PlaceService()

      val controller = new HomeController(stubControllerComponents(), userService, placeService)(ec)
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }

    "render the index page from the application" in {
      val controller = inject[HomeController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }

  }
}
