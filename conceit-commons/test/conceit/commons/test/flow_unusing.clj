(ns conceit.commons.test.flow-unusing
  (use conceit.commons.test
       clojure.test)
  (require [conceit.commons :as commons]))

(deftest* ?->-test-unusing
  (= 30 (commons/?-> 2
                     true inc
                     true (* 10))))

;; (run-tests)
