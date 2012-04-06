(in-ns 'conceit.commons)
(clojure.core/use 'clojure.core)

(defmacro assert-multi
  "Asserts multiple values."
  [& forms]  
  `(do ~@(for [form forms] `(assert ~form))))
