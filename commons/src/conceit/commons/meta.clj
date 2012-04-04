(ns conceit.commons.meta)

(defn meta? [obj]
  (instance? clojure.lang.IMeta obj))

(defn assoc-meta [obj & kvs]
  (apply vary-meta obj assoc kvs))
