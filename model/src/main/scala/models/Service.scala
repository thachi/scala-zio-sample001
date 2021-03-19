package models

import core.Identifier
import zio.ZIO

object Service {

  // IntelliJで警告が出るので戻り値を書いているが書かない方がよさそう
  def createBlog(title: String, content: String, imageurl: String): ZIO[BlogRepository with ImageRepository, Nothing, Identifier[Blog]] = for {
    imageId <- ImageRepository.allocate()
    _ <- ImageRepository.create(Image(imageId, imageurl))
    blogId <- BlogRepository.allocate()
    blog = Blog(blogId, title, content, imageId)
    _ <- BlogRepository.create(blog)
  } yield blogId

}
