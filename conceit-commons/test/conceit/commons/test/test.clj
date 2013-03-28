(ns conceit.commons.test.test
  (use conceit.commons.test
       clojure.test))

(def ^{:dynamic true} dynamic-var)

(deftest* deftest*-test-1
  (= 10 (* 2 5))
  (= "foobar" (str "foo" "bar"))
  (= true (= 10 10))
  (= false (= 10 20))
  (is (= 10 (* 2 5)))
  (is (= "foobar" (str "foo" "bar")))
  (== 20 (* 10 2))
  (< 10 20)
  (> 30 15)
  (<= 5 30)
  ;; 10
  (>= 100 80)
  (not (string? 123))
  (re-seq #"aaa$" "bbbaaa")
  (and (= 2 (+ 1 1)) (= 3 (+ 1 2)))
  (or (= 2 (+ 1 1)) (= 3 (+ 1 1)))
  (not-empty [1 2 3])
  (empty? [])
  (nil? nil)
  (map? {})
  (vector? [])
  ;; 20
  (.isEmpty "")
  (.isNaN Double/NaN)
  (Character/isDigit \3)
  (Double/isNaN Double/NaN)
  (.equals "Foo" "Foo")
  (.equals 10 10)
  ;; 26
  (let [x 10]
    (= x 10)
    (= 20 (* x 2)))
  (when true
    (= 2 (+ 1 1))
    (= 3 (+ 1 2)))
  (when-not false
    (= 4 (* 2 2))
    (= "foobar" (str "foo" "bar")))
  (when-let [x 10]
    (= x 10)
    (= 20 (* x 2)))
  (when-first [x [1 2 3]]
    (= x 1)
    (= 5 (* 5 x)))
  ;; 36
  (if true
    (= 5 (+ 3 2))
    (= 6 (+ 3 2))) ;; will not be evaluated
  (if false
    (= 6 (+ 3 4)) ;; will not be evaluated
    (= 7 (+ 3 4)))
  (if-let [x 10]
    (= x 10)
    (= 20 (+ 10 9))) ;; will not be evaluated
  (if-let [x nil]
    (= x 10) ;; will not be evaluated
    (= 20 (* 2 10)))
  (if-not false
    (= 10 (* 2 5))
    (= 9 (* 2 5))) ;; will not be evaluated
  (do
    (string? "foobar")
    (not-empty "foobar")
    (map? {:a 10 :b 20})
    (.isNaN Double/NaN)
    (.equals "foobar" "foobar")
    (Character/isDigit \6))
  (doseq [n [1 2 3]]
    (number? n))
  ;; 50
  (dotimes [n 3]
    (not (neg? n)))
  (binding [dynamic-var 20]
    (= dynamic-var 20)
    (= 40 (* dynamic-var 2)))
  (with-redefs [str 20]
    (= 20 str)
    (= 40 (* 2 str)))
  (with-local-vars [a 10 b 20]
    (= 10 @a)
    (= 20 @b)
    (var-set a 40)
    (= 40 @a)
    (var-set b 50)
    (= 50 @b))
  ;; 61
  (with-open [s (java.io.ByteArrayInputStream. (.getBytes "foo"))]
    (= (int \f) (.read s))
    (= (int \o) (.read s)))
  (let [x 10]
    (let [y 20]
      (= 30 (+ x y))))
  (thrown? Exception (Integer/parseInt "aaa"))
  (testing "test with testing"
    (= 10 (* 2 5))
    (= "abc" (str \a \b \c)))
  true
  (let [x 10]
    x)
  ;; 69
  )

(defn odd-p [x]
  (odd? x))

(defn even-p [x]
  (even? x))

(set-auto-assertion-predicate odd-p)
(set-auto-assertion-predicate even-p)

(deftest* set-auto-assertion-predicate-test
  (odd-p 3)
  (even-p 10)
  (do (odd-p 7)
      (even-p 14))
  (let [a 11]
    (odd-p a)
    (even-p (* 2 a)))
  ;; 6
  )

(defmacro my-do [& body]
  `(do ~@body))

(defmacro my-let [bindings & body]
  `(let [~@bindings] ~@body))

(defmacro with-x-y [x-val y-val & body]
  `(let [~'x ~x-val ~'y ~y-val] ~@body))

(set-auto-assertion-block my-do 0)
(set-auto-assertion-block my-let 1)
(set-auto-assertion-block with-x-y 2)

(deftest* set-auto-assertion-block-test
  (my-do
   (= 10 (* 2 5))
   (nil? nil)
   (.isEmpty ""))
  (my-let [x 10]
          (= 10 x)
          (nil? nil)
          (.isNaN Double/NaN))
  (with-x-y 2 4
    (= 2 x)
    (= 4 y)
    (nil? nil)
    (Character/isDigit \7))
  ;; 10
  )

(deftest sample-test
  (is (= 10 (* 2 5)))
  (is (= 20 (* 2 10))))

;; (run-test #'sample-test)

;; (run-tests)
