import io.getquill.{H2JdbcContext, MysqlJdbcContext, SnakeCase}

package object db {

  type DbContext = MysqlJdbcContext[SnakeCase]

}
