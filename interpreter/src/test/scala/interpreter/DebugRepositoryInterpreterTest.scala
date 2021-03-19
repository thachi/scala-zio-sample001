package interpreter

import core.StringIdentifier
import org.scalatest.funsuite.AnyFunSuite
import models._
import zio._
import zio.internal.Platform


class DebugRepositoryInterpreterTest extends AnyFunSuite {

  test("ざっくり試してみる") {

    val env = new DebugEnv

    val p = Service.createBlog("たいとる", "コンテンツ", "https://image.example.com/path/to/image.png")
    val runtime = Runtime.apply(env, Platform.default)
    runtime.unsafeRun(p)


    assert(env.getBlogs.size == 1)
    assert(env.getBlogs.head.id == StringIdentifier("blog-1"))
    assert(env.getImages.size == 1)
    assert(env.getImages.head.id == StringIdentifier("image-1"))
  }

}
