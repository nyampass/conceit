(ns conceit.commons.named)

(defn named? [x]
  (instance? clojure.lang.Named x))

(defn name-or-str [x]
  ((if (named? x) name str) x))
