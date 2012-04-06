(ns conceit.commons.number)

(defmulti int-from (fn [x] (type x)))
(defmethod int-from :default [x]
  (long x))
(defmethod int-from Character [c]
  (int c))
(defmethod int-from nil [x]
  nil)
(defmethod int-from String [s]
  (try (Long/parseLong s)
       (catch Exception _ nil)))

(defmulti double-from (fn [x] (type x)))
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
