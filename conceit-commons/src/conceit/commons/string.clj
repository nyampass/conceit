(in-ns 'conceit.commons)
(clojure.core/use 'clojure.core)

(defn non-empty-string?
  "Returns true if `s` is string and not empty otherwise false."
  [s]
  (boolean (and (string? s) (not-empty s))))

(defn suffix-array
  "Returns a suffix-array of `s`."
  [s]
  (map (partial apply str) (take-while (complement empty?) (iterate rest s))))
