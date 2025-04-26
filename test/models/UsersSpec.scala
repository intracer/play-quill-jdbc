package models

import _root_.test._
import org.scalatest.TestData
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api._

class UsersSpec extends PlaySpec with GuiceOneAppPerTest {

  override def newAppForTest(testData: TestData): Application = fakeApp

  "Users" must {
    "create and find" in {
      val users = app.injector.instanceOf(classOf[Users])
      val user = users.create(User(0L, "test1", isActive = true))
      user.id !== 0L
      val userFound = users.find(user.id)
      userFound mustBe defined
      userFound.foreach(_.name mustBe "test1")
    }


  }
}
