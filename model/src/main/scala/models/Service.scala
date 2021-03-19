package models

import core.Identifier
import zio.ZIO

object Service {

  trait Env extends BlogRepository with ImageRepository

  def createBlog(title: String, content: String, imageurl: String): ZIO[Env, Nothing, Identifier[Blog]] = for {
    imageId <- ImageRepository.allocate()
    _ <- ImageRepository.create(Image(imageId, imageurl))
    blogId <- BlogRepository.allocate()
    blog = Blog(blogId, title, content, imageId)
    _ <- BlogRepository.create(blog)
  } yield blogId

}
