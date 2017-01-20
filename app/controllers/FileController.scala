package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.{Action, AnyContent, Controller}

import scala.concurrent.Future

@Singleton
class FileController @Inject() extends Controller {

  import play.api.libs.concurrent.Execution.Implicits._

  def index: Action[AnyContent] = Action.async {
    Future {
      Ok("Hello, world!")
    }
  }
}
