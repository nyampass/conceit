(ns conceit.commons.class)

(defn static-field-value [class field]
  (.get (.getField class (name field)) class))
