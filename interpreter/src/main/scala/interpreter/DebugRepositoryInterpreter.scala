package interpreter

import core._
import models.{Blog, Image, ImageRepository, Service}
import zio.{Has, UIO}

import scala.collection.mutable

class DebugEnv extends Service.Env {


  private val images = mutable.HashMap.empty[Identifier[Image], Image]
  private val blogs = mutable.HashMap.empty[Identifier[Blog], Blog]

  def getImages: Seq[Image] = images.values.toSeq
  def getBlogs: Seq[Blog] = blogs.values.toSeq

  override val imageRepository: Repository[Image] = new Repository[Image] {
    override def allocate(): UIO[Identifier[Image]] = UIO.effectTotal(StringIdentifier("image-" + (images.size + 1)))

    override def create(entity: Image): UIO[Unit] = UIO.effectTotal(images += entity.id -> entity)
  }
  override val blogRepository: Repository[Blog] = new Repository[Blog] {
    override def allocate(): UIO[Identifier[Blog]] = UIO.effectTotal(StringIdentifier("blog-" + (blogs.size + 1)))

    override def create(entity: Blog): UIO[Unit] = UIO.effectTotal(blogs += entity.id -> entity)
  }
}



