(in-ns 'conceit.commons)
(clojure.core/use 'clojure.core)

(defn assoc-meta [obj & kvs]
  (apply vary-meta obj assoc kvs))
