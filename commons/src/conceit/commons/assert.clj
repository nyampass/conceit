(ns conceit.commons.assert
  "Utilities about assertion.")

(defmacro assert-multi [& forms]
  `(do ~@(for [form forms] `(assert ~form))))
