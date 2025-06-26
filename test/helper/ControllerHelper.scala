package helper

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Injecting

trait ControllerHelper extends PlaySpec with GuiceOneAppPerTest with Injecting{
  //val ec = app.injector.instanceOf[ExecutionContext]
}
