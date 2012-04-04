(ns conciet.commons.test.fn
  (use conceit.commons.fn
       conceit.commons.test
       clojure.test))

(deftest* apply-repeatedly-test
  (= 10 (apply-repeatedly 10 inc 0))
  (= 16 (apply-repeatedly 3 #(* 2 %) 2))
  (= 2 (apply-repeatedly 1 inc 1))
  (= 5 (apply-repeatedly 0 #(+ 100 %) 5)))

;; (run-tests)
