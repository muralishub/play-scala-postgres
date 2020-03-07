package repository

import javax.inject.{Inject, Singleton}
import models.Item
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

@Singleton
class ProductRepository @Inject()(dbConfigProvider: DatabaseConfigProvider) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class ItemTable(tag: Tag) extends Table[Item](tag, "item") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")


    override def * = (id, name) <> ((Item.apply _).tupled, Item.unapply)
  }

  private val items = TableQuery[ItemTable]


  def add(name: String): Future[Item] = db.run {
    (items.map(i => i.name)
      returning items.map(_.id)
      into ((name, id) => Item(id, name))
      ) += (name)
  }

  def list(): Future[Seq[Item]] = db.run {
    items.result
  }

}
