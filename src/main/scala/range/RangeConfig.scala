package range

import com.typesafe.config.ConfigFactory
import range.drbg.TwoFiftySixBits

object RangeConfig {
  val config = ConfigFactory.load()
  val maxSecurityBits = TwoFiftySixBits
  val maxPersonalizationStringLength = config.getInt("range.max-personalization-string-length")
  val maxReseedAdditionalInputLength = config.getInt("range.max-reseed-additional-input-length")
}
