(ns conceit.commons.assert)

(defmacro assert-multi
  "Asserts multiple values."
  [& forms]  
  `(do ~@(for [form forms] `(assert ~form))))
