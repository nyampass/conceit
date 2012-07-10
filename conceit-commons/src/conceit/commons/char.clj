(ns conceit.commons.char)

(defn char-range
  "Returns a lazy seq of characters start to end, by step, where step defaults to 1. An lazy seq includes both start and end."
  ([start end step]
     (map char (range (int start) (+ (int end) step) step)))
  ([start end]
     (char-range start end 1)))
