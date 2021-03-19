import zio.Has

package object models {

  type BlogRepository = Has[BlogRepository.Service]
  type ImageRepository = Has[ImageRepository.Service]

}
