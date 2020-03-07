package models

import play.api.libs.json._

case class Item(id: Int, name: String)


object Item {
  implicit val itemFormat = Json.format[Item]
}