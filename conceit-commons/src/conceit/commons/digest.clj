(ns conceit.commons.digest
  (use conceit.commons)
  (import [java.security MessageDigest]
          [javax.crypto.spec SecretKeySpec]
          [javax.crypto Mac]))

(defn hex-digest [algorithm source]
  (format "%x" (BigInteger. 1 (.digest (doto (MessageDigest/getInstance algorithm)
                                         (.update (bytes-from source)))))))

(defn hex-mac-digest [^bytes key algorithm source]
  (let [spec (SecretKeySpec. (bytes-from key) algorithm)]
    (format "%x" (BigInteger. 1 (.doFinal (doto (Mac/getInstance (.getAlgorithm spec))
                                            (.init spec))
                                          (bytes-from source))))))
