(ns conceit.commons.digest
  (use conceit.commons)
  (import [java.security MessageDigest]
          [javax.crypto.spec SecretKeySpec]
          [javax.crypto Mac]))

(defn digest [algorithm source]
  (.digest (doto (MessageDigest/getInstance algorithm)
             (.update (bytes-from source)))))

(defn hex-digest [algorithm source]
  (format "%x" (BigInteger. 1 (digest algorithm source))))

(defn mac-digest [key algorithm source]
  (let [spec (SecretKeySpec. (bytes-from key) algorithm)]
    (.doFinal (doto (Mac/getInstance (.getAlgorithm spec))
                (.init spec))
              (bytes-from source))))

(defn hex-mac-digest [key algorithm source]
  (format "%x" (BigInteger. 1 (mac-digest key algorithm source))))
