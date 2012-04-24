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
