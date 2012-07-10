(ns conceit.commons.meta)

(defn assoc-meta [obj & kvs]
  (apply vary-meta obj assoc kvs))
