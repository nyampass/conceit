(ns conceit.commons.test.fn
  (use conceit.commons
       conceit.commons.test
       clojure.test))

(deftest* append-apply-test
  (= 10 (append-apply + [1 2 3] 4))
  (= 10 (append-apply + [1 2] 3 4))
  (= 15 (append-apply + [1] 2 3 4 5))
  (= 10 (append-apply + [1 2 3 4]))
  (= 10 (append-apply + [] 1 2 3 4))
  (= '(:foo :bar :baz) (append-apply list [:foo :bar] :baz))
  (= '(:foo :bar :baz) (append-apply list '(:foo :bar) :baz))
  (= '(:foo) (append-apply list [] :foo))
  (= '(:foo) (append-apply list () :foo))
  (= '(:foo) (append-apply list nil :foo)))
  
(deftest* apply-repeatedly-test
  (= 10 (apply-repeatedly 10 inc 0))
  (= 16 (apply-repeatedly 3 #(* 2 %) 2))
  (= 2 (apply-repeatedly 1 inc 1))
  (= 5 (apply-repeatedly 0 #(+ 100 %) 5)))

;; (run-tests)
