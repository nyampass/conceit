(ns conceit.commons.fn)

(defn append-apply [f args & more-args]
  (apply f (concat args more-args)))

(defn apply-repeatedly
  "Returns a value applied the function `f` `times` times."
  [times f initial-value]
  (nth (iterate f initial-value) times))
