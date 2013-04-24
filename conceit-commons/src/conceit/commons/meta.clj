(ns conceit.commons.meta)

(defn assoc-meta [obj & kvs]
  (apply vary-meta obj assoc kvs))

(defn dissoc-meta [obj & keys]
  (apply vary-meta obj dissoc keys))

(defn with-meta* [obj meta]
  (if (instance? clojure.lang.IMeta obj)
    (with-meta obj meta)
    obj))

(defmacro keep-meta [obj & body]
  `(with-meta* (do ~@body) (meta ~obj)))
