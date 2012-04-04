(ns conceit.commons.string)

(defn non-empty-string? [s]
  (boolean (and (string? s) (not-empty s))))

(defn suffix-array [s]
  (map (partial apply str) (take-while (complement empty?) (iterate rest s))))
