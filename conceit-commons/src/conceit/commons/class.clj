(ns conceit.commons.class)

(defn static-field-value
  "Returns the value of the static field `field` on `class`."
  [class field]
  (.get (.getField class (name field)) class))
