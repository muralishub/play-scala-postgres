package controllers

import javax.inject._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc._
import repository.ProductRepository
import scala.concurrent.{ExecutionContext, Future}


class ProductController @Inject()(productRepository: ProductRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {


  val itemForm: Form[CreateItemForm] = Form {
    mapping(
      "name" -> nonEmptyText
    )(CreateItemForm.apply)(CreateItemForm.unapply)
  }

  def index() = Action { implicit request =>
    Ok(views.html.index(itemForm))
  }

  def addItem() = Action.async { implicit request =>
   itemForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(Ok(views.html.index(errorForm)))
      },
      item => {
        productRepository.add(item.name).map { _ =>
          Redirect(routes.ProductController.index).flashing("success" -> "item.created")
        }
      }
    )
  }

  def getItems() = Action.async { implicit request =>
    productRepository.list().map { item =>
      Ok(Json.toJson(item))
    }}

}
case class CreateItemForm(name: String)