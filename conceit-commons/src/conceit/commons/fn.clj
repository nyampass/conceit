(in-ns 'conceit.commons)
(clojure.core/use 'clojure.core)

(defn apply-repeatedly
  "Returns a value applied the function `f` `times` times."
  [times f initial-value]
  (nth (iterate f initial-value) times))
