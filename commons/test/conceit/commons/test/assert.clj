(ns conceit.commons.test.assert
  (use conceit.commons.assert
       conceit.commons.test
       clojure.test))

(deftest* assert-multi-test
  (nil? (assert-multi "hello"))
  (nil? (assert-multi "hello" "world"))
  (nil? (assert-multi (= 10 (+ 1 2 3 4)) (= 20 (* 4 5))))
  (nil? (assert-multi))
  (thrown? AssertionError (assert-multi (= 1 2)))
  (thrown? AssertionError (assert-multi (= 2 2) (= 2 3)))
  (thrown? AssertionError (assert-multi (= 2 3) (= 2 2))))

;; (run-tests)
