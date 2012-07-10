(ns conceit.commons.test.ns.sample1)

(defn sample1-fn [x] [:sample1-fn x])
(def sample1-value 42)
(defonce sample1-once 123)
(defstruct sample1-struct :name :age)
(defmacro sample1-macro [x]
  `(list :sample1-macro ~x))
