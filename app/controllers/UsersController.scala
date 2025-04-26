package controllers

import models.{User, Users}
import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents}

class UsersController(userServices: Users, cc: ControllerComponents) extends AbstractController(cc) {

  implicit val userWrites: Writes[User] = Json.writes[User]
  implicit val userReads: Reads[User] = (
    Reads.pure(0L) and
      (JsPath \ "name").read[String] and
      (JsPath \ "isActive").read[Boolean]
  )(User.apply _)

  def get(id: Long) = Action { _ =>
    userServices.find(id) match {
      case None       => NotFound
      case Some(user) => Ok(Json.toJson(user))
    }
  }

  def list = Action { _ =>
    Ok(Json.toJson(userServices.list))
  }

  def create = Action(parse.json) { request =>
    Json
      .fromJson[User](request.body)
      .fold(
        invalid => BadRequest,
        user => {
          val userCreated = userServices.create(user)
          Created.withHeaders(LOCATION -> s"/users/${userCreated.id}")
        }
      )
  }

  def delete(id: Long) = Action { _ =>
    userServices.find(id) match {
      case None => NotFound
      case Some(user) =>
        userServices.delete(user)
        NoContent
    }
  }

  def update(id: Long) = Action(parse.json) { request =>
    Json
      .fromJson[User](request.body)
      .fold(
        invalid => BadRequest,
        user => {
          userServices.update(user.copy(id = id))
          NoContent
        }
      )
  }
}
