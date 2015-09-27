package range.drbg

import range.RangeConfig
import scala.collection.mutable
import scala.concurrent.Future

trait Mechanism {
  val instantiated = false
  val seed : Seed
  val usedPersonalizationStrings: mutable.TreeSet[String]
  val numRequests: Int

  def instantiate(instantiatedSecurityStrength: SecurityStrength,
                  personalizationString: Option[String]): Future[Unit] = {
    if (!validateSecurityStrength(instantiatedSecurityStrength))
      Future.failed(new IllegalArgumentException(s"Security strength requested is greater than the maximum supported by this mechanism (${RangeConfig.maxSecurityBits})."))
    if (personalizationString.isDefined && !validatePersonalizationString(personalizationString.get))
      Future.failed(new IllegalArgumentException(s"Personalization string length is greater than the maximum supported by this mechanism (${RangeConfig.maxPersonalizationStringLength})."))

    instantiateAlgo(obtainEntropyInput(instantiatedSecurityStrength.bitValue), obtainNonce, personalizationString, instantiatedSecurityStrength)
  }

  private def instantiateAlgo(entInput: Entropy, nonce: Option[String],
                              personalizationString: Option[String], securityStrength: SecurityStrength): Future[Unit] = {
    Future.successful() //TODO
  }

  private def obtainEntropyInput(min_entropy: Int, min_length: Int, max_length: Int): Future[Entropy] =
    Future.successful(Entropy(1)) //TODO

  private def obtainNonce: Option[String] = Some("") //TODO

  private def validateSecurityStrength(strength: SecurityStrength) =
    SecurityStrength.StrengthOrdering.gt(RangeConfig.maxSecurityBits, strength)

  private def validatePersonalizationString(personalizationString: String) = {
    val valid = !usedPersonalizationStrings.contains(personalizationString) && personalizationString.length < RangeConfig.maxPersonalizationStringLength
    usedPersonalizationStrings += personalizationString
    valid
  }

  def uninstantiate
  def generate : Future[Int] =
    if (instantiated)
      Future.successful(1)
    else
      Future.failed(UninstantiatedMechanism("Mechanism needs to be instantiated prior to generation."))

  def heathTest
  //optional
  def reseed(additionalInput: String) : Future[Boolean] = {
    val entropy = obtainEntropyInput // Prediction resistance
    Future.successful(true) // TODO
  }
}
