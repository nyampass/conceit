(ns conceit.commons.named)

(defn named? [x]
  (instance? clojure.lang.Named x))

(defn fullname [x]
  (if-let [ns (namespace x)]
    (str ns \/ (name x))
    (name x)))

(defn name-or-str [x]
  ((if (named? x) name str) x))

(defn fullname-or-str [x]
  ((if (named? x) fullname str) x))
