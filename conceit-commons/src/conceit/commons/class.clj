(in-ns 'conceit.commons)
(clojure.core/use 'clojure.core)

(defn static-field-value
  "Returns the value of the static field `field` on `class`."
  [class field]
  (.get (.getField class (name field)) class))
