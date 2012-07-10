(ns conceit.commons.test.ns.sample2)

(defn sample2-fn [x] [:sample2-fn x])
(def sample2-value 420)
(defonce sample2-once 1230)
(defstruct sample2-struct :price :color)
(defmacro sample2-macro [x]
  `(set [:sample2-macro ~x]))
