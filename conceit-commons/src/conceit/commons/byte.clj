(in-ns 'conceit.commons)
(clojure.core/use 'clojure.core)
(load "commons/type")

(defmulti bytes-from
  "Returns an array of bytes converted from `x`, or nil if `x` can not to be converted to an array of bytes."
  (fn [x] (type x)))
(defmethod bytes-from :default [x] nil)
(defmethod bytes-from (array-type :byte) [x] x)
(defmethod bytes-from String [x]
  (.getBytes x))
(defmethod bytes-from clojure.lang.IPersistentCollection [x]
  (into-array Byte/TYPE (map byte x)))

(defn hex-from-bytes [bytes]
  (let [chars "0123456789abcdef"]
    (letfn [(chars-seq [rest-bytes]
              (if (empty? rest-bytes)
                nil
                (let [byte (first rest-bytes)
                      byte (if (< byte 0) (+ byte (* 2 (inc Byte/MAX_VALUE))) byte)
                      idx1 (Math/floor (/ byte 16))
                      idx2 (mod byte 16)]
                  (lazy-seq (cons (.charAt chars idx1)
                                  (cons (.charAt chars idx2)
                                        (chars-seq (rest rest-bytes))))))))]
      (apply str (chars-seq bytes)))))

(defn bytes-from-hex [hex]
  (into-array Byte/TYPE
              (map (fn [[c1 c2]]
                     (let [b (+ (* (Integer/parseInt (str c1) 16) 16) (Integer/parseInt (str c2) 16))]
                       (if (> b Byte/MAX_VALUE) (+ Byte/MIN_VALUE (- b Byte/MAX_VALUE 1)) b)))
                   (partition-all 2 hex))))
