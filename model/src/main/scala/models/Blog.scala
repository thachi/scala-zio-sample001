package models

import core.{Entity, Identifier, Repository, Undefined}
import zio.ZIO

case class BlogId(id: String) extends Identifier[Blog]

case class Blog(id: Identifier[Blog] = Undefined, title: String, content: String, image: Identifier[Image]) extends Entity[Blog]


trait BlogRepository {
  val blogRepository: Repository[Blog]
}

object BlogRepository {

  trait Service extends Repository[Blog]

  def allocate(): ZIO[BlogRepository, Nothing, Identifier[Blog]] =
    ZIO.accessM(e => e.blogRepository.allocate())

  def create(entity: Blog): ZIO[BlogRepository, Nothing, Unit] =
    ZIO.accessM(e => e.blogRepository.create(entity))
}