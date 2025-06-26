package services

import models._

import scala.concurrent.Future

class PlaceService {

  def getAll: Future[List[Place]] = {
    var list: List[Place] = {
      List(
        Place(
          "Sandleford",
          Location(51.377797, -1.318965)
        ),
        Place(
          "Watership Down",
          Location(51.235685, -1.309197)
        )
      )
    }
    Future.successful(list)
  }
}
