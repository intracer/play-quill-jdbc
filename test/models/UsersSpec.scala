package models

import com.dimafeng.testcontainers.{ForAllTestContainer, MariaDBContainer}
import org.scalatest.TestData
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.components.OneAppPerTestWithComponents
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import org.testcontainers.utility.DockerImageName
import play.api._
import play.api.inject.guice.GuiceApplicationBuilder

class UsersSpec
    extends PlaySpec
    with OneAppPerTestWithComponents
    with ForAllTestContainer {

  override val container: MariaDBContainer =
    MariaDBContainer(dockerImageName = DockerImageName.parse("mariadb:10.3.39"))

  override def newAppForTest(testData: TestData): Application =
    GuiceApplicationBuilder()
      .configure(
        Map("db.default.username" -> container.username,
            "db.default.password" -> container.password,
            "db.default.url" -> container.jdbcUrl,
            "useSSL" -> "false")
      )
      .build()

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

  override def components: BuiltInComponents = {
    val appLoader = new AppLoader
  }
}
