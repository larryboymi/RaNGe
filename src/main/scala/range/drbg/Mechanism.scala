package range.drbg

import scala.concurrent.Future

/**
 * Created by loande on 9/26/15.
 */
trait Mechanism {
  val instantiated = false
  val seed : Int

  def instantiate(seed: Int)
  def uninstantiate
  def generate : Future[Int] =
    if (instantiated)
      Future.successful(1)
    else
      Future.failed(UninstantiatedMechanism("Mechanism needs to be instantiated prior to generation."))

  def heathTest
  //optional
  def reseed(newSeed: Int) : Future[Boolean] =
    if (newSeed == seed)
      Future.failed(InvalidSeed("The seed used to reseed is the same as the initial seed."))
    else
      Future.successful(true)
}
