(ns conceit.commons.test.boolean
  (:use conceit.commons
        conceit.commons.test
        clojure.test))

(deftest* boolean?-test
  (true? (boolean? true))
  (true? (boolean? false))
  (false? (boolean? nil))
  (false? (boolean? "true"))
  (false? (boolean? 0))
  (false? (boolean? [])))
