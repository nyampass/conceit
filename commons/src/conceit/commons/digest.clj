(ns conceit.commons.digest
  (import java.security.MessageDigest))

(defn hex-digest [algorithm s]
  (format "%x" (BigInteger. 1 (.digest (doto (MessageDigest/getInstance algorithm)
                                         (.update (.getBytes s)))))))
