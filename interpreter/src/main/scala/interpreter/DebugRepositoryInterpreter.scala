package interpreter

import core._
import models.{Blog, BlogRepository, Image, ImageRepository, Service}
import zio.{Has, UIO}

import scala.collection.mutable

// Env? 名前の付け方に迷う
class DebugRepositoryInterpreter {

  private val images = mutable.HashMap.empty[Identifier[Image], Image]
  private val blogs = mutable.HashMap.empty[Identifier[Blog], Blog]

  def getImages: Seq[Image] = images.values.toSeq
  def getBlogs: Seq[Blog] = blogs.values.toSeq

  private val image = Has(new ImageRepository.Service {
    override def allocate(): UIO[Identifier[Image]] = UIO.effectTotal(StringIdentifier("image-" + (images.size + 1)))

    override def create(entity: Image): UIO[Unit] = UIO.effectTotal(images += entity.id -> entity)
  })

  private val blog = Has(new BlogRepository.Service {
    override def allocate(): UIO[Identifier[Blog]] = UIO.effectTotal(StringIdentifier("blog-" + (blogs.size + 1)))

    override def create(entity: Blog): UIO[Unit] = UIO.effectTotal(blogs += entity.id -> entity)
  })

  val env: Has[ImageRepository.Service] with Has[BlogRepository.Service] = image ++ blog
}



