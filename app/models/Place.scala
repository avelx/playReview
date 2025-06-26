package models


case class Location(lat: Double, long: Double)

object Location {
  def unapply(l: Location): Option[(Double, Double)] = Some(l.lat, l.long)
}

case class Place(name: String, location: Location)