package range.drbg

// Table 1 of Section 8.4 - http://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-90Ar1.pdf

sealed trait SecurityStrength {
  def bitValue: Int
}
case object OneTwelveBits extends SecurityStrength {
  override def bitValue = 112
  override def toString = "112-bits"
}
case object OneTwentyEightBits extends SecurityStrength {
  override def bitValue = 128
  override def toString = "128-bits"
}
case object OneNinetyTwoBits extends SecurityStrength {
  override def bitValue = 192
  override def toString = "192-bits"
}
case object TwoFiftySixBits extends SecurityStrength {
  override def bitValue = 256
  override def toString = "256-bits"
}

object SecurityStrength {
  implicit val StrengthOrdering: Ordering[SecurityStrength] = Ordering.by{ _.bitValue }
}