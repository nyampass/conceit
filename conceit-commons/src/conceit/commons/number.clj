(in-ns 'conceit.commons)
(clojure.core/use 'clojure.core)

(defmulti int-from
  "Returns an integer value converted from `x`, or nil if `x` can not to be converted to an integer value."
  (fn [x] (type x)))
(defmethod int-from :default [x]
  (long x))
(defmethod int-from Character [c]
  (int c))
(defmethod int-from nil [x]
  nil)
(defmethod int-from String [s]
  (try (Long/parseLong s)
       (catch Exception _ nil)))

(defmulti double-from
  "Returns an double value converted from `x`, or nil if `x` can not to be converted to an double value."
  (fn [x] (type x)))
(defmethod double-from :default [x]
  (double x))
(defmethod double-from nil [x]
  nil)
(defmethod double-from String [s]
  (try (Double/parseDouble s)
       (catch Exception _ nil)))

(defn in-range [n & {:keys [min max]}]
  (cond (and min (> min n)) min
        (and max (< max n)) max
        :else n))
