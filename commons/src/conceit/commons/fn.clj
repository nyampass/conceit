(ns conceit.commons.fn
  "Utilities about function.")

(defn apply-repeatedly [times f initial-value]
  (nth (iterate f initial-value) times))
