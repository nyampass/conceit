(ns conceit.commons.test.digest
  (use conceit.commons.digest
       conceit.commons
       conceit.commons.test
       clojure.test))

(deftest* digest-test
  (= (vec (bytes-from-hex "aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d")) (vec (digest "SHA-1" "hello")))
  (= (vec (bytes-from-hex "7e240de74fb1ed08fa08d38063f6a6a91462a815")) (vec (digest "SHA-1" "aaa")))
  (= (vec (bytes-from-hex "da39a3ee5e6b4b0d3255bfef95601890afd80709")) (vec (digest "SHA-1" "")))
  (= (vec (bytes-from-hex "5d41402abc4b2a76b9719d911017c592")) (vec (digest "MD5" "hello")))
  (= (vec (bytes-from-hex "47bce5c74f589f4867dbd57e9ca9f808")) (vec (digest "MD5" "aaa")))
  (= (vec (bytes-from-hex "d41d8cd98f00b204e9800998ecf8427e")) (vec (digest "MD5" "")))
  (= (vec (bytes-from-hex "9b71d224bd62f3785d96d46ad3ea3d73319bfbc2890caadae2dff72519673ca72323c3d99ba5c11d7c7acc6e14b8c5da0c4663475c2e5c3adef46f73bcdec043")) (vec (digest "SHA-512" "hello")))
  (= (vec (bytes-from-hex "d6f644b19812e97b5d871658d6d3400ecd4787faeb9b8990c1e7608288664be77257104a58d033bcf1a0e0945ff06468ebe53e2dff36e248424c7273117dac09")) (vec (digest "SHA-512" "aaa")))
  (= (vec (bytes-from-hex "cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e")) (vec (digest "SHA-512" ""))))

(deftest* hex-digest-test
  (= "aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d" (hex-digest "SHA-1" "hello"))
  (= "7e240de74fb1ed08fa08d38063f6a6a91462a815" (hex-digest "SHA-1" "aaa"))
  (= "da39a3ee5e6b4b0d3255bfef95601890afd80709" (hex-digest "SHA-1" ""))
  (= "5d41402abc4b2a76b9719d911017c592" (hex-digest "MD5" "hello"))
  (= "47bce5c74f589f4867dbd57e9ca9f808" (hex-digest "MD5" "aaa"))
  (= "d41d8cd98f00b204e9800998ecf8427e" (hex-digest "MD5" ""))
  (= "9b71d224bd62f3785d96d46ad3ea3d73319bfbc2890caadae2dff72519673ca72323c3d99ba5c11d7c7acc6e14b8c5da0c4663475c2e5c3adef46f73bcdec043" (hex-digest "SHA-512" "hello"))
  (= "d6f644b19812e97b5d871658d6d3400ecd4787faeb9b8990c1e7608288664be77257104a58d033bcf1a0e0945ff06468ebe53e2dff36e248424c7273117dac09" (hex-digest "SHA-512" "aaa"))
  (= "cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e" (hex-digest "SHA-512" "")))

(deftest* mac-digest-test
  (= (vec (bytes-from-hex "750c783e6ab0b503eaa86e310a5db738")) (vec (mac-digest "Jefe" "HmacMD5" "what do ya want for nothing?")))
  (= (vec (bytes-from-hex "effcdf6ae5eb2fa2d27416d5f184df9c259a7c79")) (vec (mac-digest "Jefe" "HmacSHA1" "what do ya want for nothing?"))))

(deftest* hex-mac-digest-test
  (= "750c783e6ab0b503eaa86e310a5db738" (hex-mac-digest "Jefe" "HmacMD5" "what do ya want for nothing?"))
  (= "effcdf6ae5eb2fa2d27416d5f184df9c259a7c79" (hex-mac-digest "Jefe" "HmacSHA1" "what do ya want for nothing?")))

;; (run-tests)
