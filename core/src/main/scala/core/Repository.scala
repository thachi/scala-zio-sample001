package core

import zio.UIO

trait Repository[A <: Entity[A]] {

  def allocate(): UIO[Identifier[A]]

  def create(entity: A): UIO[Unit]

}


trait Entity[+E]

trait Identifier[+E <: Entity[E]]

case class StringIdentifier[E <: Entity[E]](value: String) extends Identifier[E]

case object Undefined extends Identifier[Nothing]

