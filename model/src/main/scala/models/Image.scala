package models

import core.{Entity, Identifier, Repository, Undefined}
import zio.ZIO


case class ImageId(id: String) extends Identifier[Image]

case class Image(id: Identifier[Image] = Undefined, url: String, alt: Option[String] = None) extends Entity[Image]


object ImageRepository {

  trait Service extends Repository[Image]

  def allocate(): ZIO[ImageRepository, Nothing, Identifier[Image]] =
    ZIO.accessM(e => e.get[ImageRepository.Service].allocate())

  def create(entity: Image): ZIO[ImageRepository, Nothing, Unit] =
    ZIO.accessM(e => e.get[ImageRepository.Service].create(entity))
}