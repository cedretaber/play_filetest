package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.{Action, AnyContent, Controller}

@Singleton
class FileController @Inject() extends Controller {

  def index: Action[AnyContent] = Action {
    Ok("Hello, world!")
  }
}
