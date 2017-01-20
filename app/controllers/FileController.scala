package controllers

import java.io.{BufferedReader, FileReader}
import javax.inject.{Inject, Singleton}

import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, Controller}

import scala.concurrent.Future

case class FooHoge(foo: String, hoge: String)

@Singleton
class FileController @Inject() extends Controller {

  import play.api.libs.concurrent.Execution.Implicits._

  def index: Action[AnyContent] = Action.async { implicit req =>
    Future {
      Ok("Hello, world!")
    }
  }

  def putFile: Action[AnyContent] = Action.async { implicit req =>
    import FileController.form

    val formData = req.body.asMultipartFormData.get

    formData.files.foreach { file =>
      val reader = new BufferedReader(new FileReader(file.ref.file))
      try {
        import collection.JavaConversions._
        println(reader.lines().toArray.mkString("\n"))
      } finally {
        reader.close()
      }
    }

    form.bind(Json.parse(formData.dataParts("json").head)).fold(
      e => println(e),
      s => println(s)
    )

    Future {
      Ok {
        Json.obj("status" -> "ok")
      }
    }
  }
}

object FileController {
  import play.api.data.Form
  import play.api.data.Forms._

  val form = Form(
    mapping(
      "foo" -> nonEmptyText,
      "hoge" -> text
    )(FooHoge.apply)(FooHoge.unapply)
  )
}
