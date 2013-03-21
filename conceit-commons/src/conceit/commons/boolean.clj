(ns conceit.commons.boolean)

(defn boolean?
  "Returns if a value is boolean."
  ([value]
     (instance? Boolean value)))
