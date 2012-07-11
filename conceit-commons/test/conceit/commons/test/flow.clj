(ns conceit.commons.test.flow
  (use conceit.commons
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

(deftest* ignore-exceptions-test
  (nil? (ignore-exceptions (throw (Exception. ""))))
  (nil? (ignore-exceptions (+ nil nil)))
  (= 10 (ignore-exceptions (+ 5 5))))

(set-auto-assertion-block when-let* 1)

(deftest* when-let*-test
  (= 11
     (when-let* [x (+ 5 6)]
       x))
  (= [30 150]
     (when-let* [a (+ 10 20)
                 b (* a 5)]
       [a b]))
  (= [5 8 20] (when-let* [base 5
                          height 8
                          area (/ (* base height) 2)]
                [base height area]))
  (= 30 (when-let* [] 30))
  (nil? (when-let* [x nil] 40))
  (nil? (when-let* [a 20
                    b nil]
          100))
  (nil? (when-let* [a nil b nil]
          200))
  (nil? (when-let* [a nil b 30]
          300))
  (= [1 2] (let [a 10 b 20]
             (when-let* [a 1 b (* 2 a)]
               [a b])))
  (when-let* [x 10
              y 20
              z 30]
    (= 10 x)
    (= 20 y)
    (= 30 z))
  (testing "short circuit"
    (with-local-vars [a :unmodified]
      (= [10 20] (when-let* [x 10
                             y (do (var-set a :modified)
                                   20)]
                   [x y]))
      (= :modified @a))
    (with-local-vars [a :unmodified]
      (nil? (when-let* [x nil
                        y (do (var-set a :modified)
                              20)]
              [x y]))
      (= :unmodified @a))
    (with-local-vars [a :unmodified]
      (nil? (when-let* [x (do (var-set a :modified)
                              10)
                        y nil]
              [x y]))
      (= :modified @a))
    (with-local-vars [a :unmodified]
      (nil? (when-let* [x 10
                        y (do (var-set a :modified)
                              nil)]
              [x y]))
      (= :modified @a))))

(set-auto-assertion-block if-let* 1)

(deftest* if-let*-test
  (= 11 (if-let* [x (+ 5 6)] x 0))
  (= [30 150] (if-let* [a (+ 10 20)
                        b (* a 5)]
                [a b]
                [0 0]))
  (= [5 8 20] (if-let* [base 5
                        height 8
                        area (/ (* base height) 2)]
                [base height area]
                [0 0 0]))
  (= 30 (if-let* [] 30 10))
  (= 2 (if-let* [x nil] 1 2))
  (= [0 0] (if-let* [a 1 b nil] [a b] [0 0]))
  (= [1 1] (if-let* [a nil b nil] [a b] [1 1]))
  (= [2 2] (if-let* [a nil b 2] [a b] [2 2]))
  (= [1 2] (let [a 10 b 20]
             (if-let* [a 1 b (* 2 a)]
               [a b]
               [0 0])))
  (if-let* [x 10 y 20]
    (= 30 (+ x y))
    (is nil "Unexpected."))
  (testing "short circuit"
    (with-local-vars [a :unmodified]
      (= [10 20] (if-let* [x 10
                           y (do (var-set a :modified)
                                 20)]
                   [x y]
                   [1 2]))
      (= :modified @a))
    (with-local-vars [a :unmodified]
      (= [1 2] (if-let* [x nil
                         y (do (var-set a :modified)
                               20)]
                 [x y]
                 [1 2]))
      (= :unmodified @a))
    (with-local-vars [a :unmodified]
      (= [1 2] (if-let* [x (do (var-set a :modified)
                               10)
                         y nil]
                 [x y]
                 [1 2]))
      (= :modified @a))
    (with-local-vars [a :unmodified]
      (= [1 2] (if-let* [x 10
                         y (do (var-set a :modified)
                               nil)]
                 [x y]
                 [1 2]))
      (= :modified @a)))
  (testing "binding"
    (let [x 10 y 20 z 30]
      (= [:then 1 2 3] (if-let* [x 1 y 2 z 3]
                         [:then x y z]
                         [:else x y z]))
      (= [:else 10 20 30] (if-let* [x nil y nil z nil]
                            [:then x y z]
                            [:else x y z]))
      (= [:else 10 20 30] (if-let* [x 1 y 2 z nil]
                            [:then x y z]
                            [:else x y z]))
      (= [:else 10 20 30] (if-let* [x 1 y nil z 3]
                            [:then x y z]
                            [:else x y z]))
      (= [:else 10 20 30] (if-let* [x nil y 2 z 3]
                            [:then x y z]
                            [:else x y z])))))

;; (run-tests)
