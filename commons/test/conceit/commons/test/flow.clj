(ns conceit.commons.test.flow
  (use conceit.commons.flow
       conceit.commons.test
       clojure.test))

(deftest* do0-test
  (with-local-vars [x 0]
    (= 100 (do0 (+ 40 60)
                (var-set x 10)))
    (= 10 (var-get x)))
  (with-local-vars [x nil
                    y nil]
    (= "foo" (do0 (str "f" "o" "o")
                  (var-set x "x")
                  (var-set y "y")))
    (= "x" (var-get x))
    (= "y" (var-get y)))
  (with-local-vars [x 0]
    (thrown? Exception (do0 (throw (Exception.))
                            (var-set x 10)))
    (= 0 (var-get x)))
  (with-local-vars [x nil
                    y nil
                    z nil]
    (thrown? Exception (do0 (var-set x 10)
                            (var-set y 20)
                            (throw (Exception.))
                            (var-set z 30)))
    (= 10 (var-get x))
    (= 20 (var-get y))
    (nil? (var-get z))))

(deftest* ?->-test
  (= 2 (?-> 2))
  (= 3 (?-> 2
            true inc))
  (= 30 (?-> 2
             true inc
             true (* 10)))
  (= 20 (?-> 2
             (= 9 (* 3 4)) inc
             (= 7 (+ 3 4)) (* 10)))
  (= "foo" (?-> "aaafoobbb"
                true (.substring 3)
                false (.substring 0 2)
                true (.substring 0 3))))

(deftest* ?->>-test
  (= [1 2 3] (?->> [1 2 3]))
  (= [3 2 1] (?->> [1 2 3]
                   true reverse))
  (= [3 2] (?->> [1 2 3]
                 true reverse
                 true (take 2)))
  (= [1 2] (?->> [1 2 3]
                 (= 9 (* 3 4)) reverse
                 (= 7 (+ 3 4)) (take 2)))
  (= [5 4 3 2 1] (?->> [1 2 3]
                       true reverse
                       false (take 2)
                       true (concat [5 4])))
  (= 20 (let [x 5] (?->> (* 2 x)
                         (= x 5) (let [x 10]))))
  (= 10 (let [x 5] (?->> (* 2 x)
                         (= x 6) (let [x 10])))))

;; (run-tests)
